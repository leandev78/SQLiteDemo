<?php

include_once(__DIR__ . "/conexion.php");

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    
	$email = filter_var($_POST['email'], FILTER_VALIDATE_EMAIL);
	$nroreferencia =  filter_var($_POST['nref']);
	
    if (!$email) {
        die("El email ingresado no es válido.");
    }
	
    if (!$nroreferencia) {
        die("El número de referencia no es válido.");
    }	
	
	// Genero un token unico
    $token = bin2hex(random_bytes(16));
	
	
	// ****************************************************
	// REGISTRO EN BASE DE DATOS
	// ****************************************************
	
	// Topes maximos
	$maxReferenciasPorEmail = 10;
	$maxEmailsPorReferencia = 10;
	
	try {
		$pdo = new PDO("sqlsrv:server=".CONN_HOST."; Encrypt=true;TrustServerCertificate=true; Database=".CONN_DB, CONN_USER, CONN_PASS);
		$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

		// Me fijo si ya estaba registrado el email.
		$stmt = $pdo->prepare("SELECT COUNT(*) as cantidad, confirmado FROM [dbo].[SUSCRIPCIONES] WHERE referencia = ? AND email = ? AND borrado_logico != 1 GROUP BY confirmado");
		$stmt->execute([$nroreferencia, $email]);
		$result = $stmt->fetch();

		if ($result['cantidad'] > 0) {
			if ($result['confirmado'] == 0) {
				echo "Te enviamos un email con un enlace de confirmación. Si no lo encontrás, revisá tu bandeja de spam o correo no deseado.";
			} else{
				echo "Ya estás suscripto con este correo para este número de referencia.";
			}
			exit;
		}
		
		// Verifico cuantas referencias tiene asociado este email
		$stmt = $pdo->prepare("SELECT COUNT(DISTINCT referencia) as cantidad FROM [dbo].[SUSCRIPCIONES] WHERE email = ? AND borrado_logico != 1");
		$stmt->execute([$email]);
		$result = $stmt->fetch();

		if ($result['cantidad'] >= $maxReferenciasPorEmail) {
			echo "Este correo ya supera la cantidad máxima permitida de referencias asociadas.";
			exit;
		}

		// Verifico cuantos emails hay asociados a esta referencia
		$stmt = $pdo->prepare("SELECT COUNT(DISTINCT email) as cantidad FROM [dbo].[SUSCRIPCIONES] WHERE referencia = ? AND borrado_logico != 1");
		$stmt->execute([$nroreferencia]);
		$result = $stmt->fetch();

		if ($result['cantidad'] >= $maxEmailsPorReferencia) {
			echo "Este número de referencia ya tiene la cantidad máxima permitida de correos asociados.";
			exit;
		}

		// Si todo esta OK, entonces lo suscribo pero con estado "confirmado = 0".
		$stmt = $pdo->prepare("INSERT INTO [dbo].[SUSCRIPCIONES] (referencia, email, token, confirmado, borrado_logico, fecha_confirmacion, fecha_borrado) VALUES (?, ?, ?, 0, 0,  NULL, NULL)");
		$stmt->execute([$nroreferencia, $email, $token]);

		echo "Suscripción realizada con éxito.";		
		
	}
	catch(PDOException $e) {
		echo "Error en base de datos: " . $e->getMessage();
		exit;
	}
	finally {
		if (isset($pdo)) $pdo = null;
	}

		
	// ****************************************************
	// ENVIO DE EMAIL DE CONFIRMACION
	// ****************************************************


	// Entorno de la aplicacion.
	$path = "https://demo.ecofactura.com.ar";
		
	
	// Envio de email de confirmacion mediante servicio de Doppler.
	$accountId = '8886';
	$apikey = '07h7czcyzxgbxfvgxmzjeprako0srm';
	$baseUrl = "https://api.dopplerrelay.com";
	$url = "$baseUrl/accounts/$accountId/messages";

	$html  = '<div style="font-family: Arial, sans-serif; color: #333; text-align: center;">';
	$html .= '<img src="'.$path.'/images/gasnea.png" alt="Gasnea" style="max-width: 200px; margin-bottom: 20px;">';
	$html .= '<h2 style="color: #0055a6;">Confirmación de adhesión a Factura Digital</h2>';
	$html .= '<p>Recibimos tu solicitud para adherirte al servicio de Factura Digital.</p>';
	$html .= '<p>Para completar el proceso, hacé clic en el siguiente enlace:</p>';
	$html .= '<br/><a href="'.$path.'/db/confirmarSuscripcion_accion.php?token='.$token.'" style="background-color: #0055a6; color: #fff; padding: 10px 20px; text-decoration: none; border-radius: 5px;"><strong>Confirmar Adhesión</strong></a>';
	$html .= '<br/><br/><br/>';
	$html .= '<p style="margin-top:20px;">Recordá que, si en algún momento preferís dejar de recibir la factura digital por correo, podés gestionar la baja desde el enlace que vas a encontrar al final de cada mensaje.</p>';	
	$html .= '<br/>';
	$html .= '<p style="margin-top:20px;">Si no realizaste esta solicitud, podés ignorar este mensaje.</p>';	
	$html .= '<hr style="margin: 30px 0; border: none; border-top: 1px solid #ccc;">';
	$html .= '<p style="font-size: 13px; color: #777; text-align: center;"><strong>Nota:</strong> Este es un correo generado automáticamente. Por favor, no respondas a este mensaje.</p>';
	$html .= '</div>';

	$data = array(
		'from_name' => 'GasNea',
		'from_email' => 'no-contestar@ecofactura.com.ar',
		'recipients' => array(
			array(
				'type' => 'to',
				'email' => $email,
				'name' => 'Test Recipient'
			)
		),
		'subject' => 'GASNEA - Confirme su suscripción a Factura Digital',
		'html' => $html

	);

	$options = array(
		'http' => array(
			'header' => "Authorization: token $apikey\r\nContent-type: application/json\r\n",
			'method' => 'POST',
			'content' => json_encode($data),
			'ignore_errors' => true
		)
	);

	$context  = stream_context_create($options);

	$result = file_get_contents($url, false, $context);
	$headers = $http_response_header;

	$matches = array();
	preg_match('#HTTP/\d+\.\d+ (\d+)#', $headers[0], $matches);
	$statusCode = intval($matches[1]);

	if ($statusCode >= 200 && $statusCode < 300) {
		echo "\r\nTe mandamos un correo para confirmar tu adhesión. Buscalo en tu bandeja de entrada o en spam y hacé clic en el enlace para completar el proceso.\r\n";
	} else if ($statusCode >= 400) {
		echo "\r\nSe ha producido un inconveniente al intentar registrar su cuenta en el servicio de factura digital..\r\n";
		//var_dump($headers);
		//var_dump($result);
	} else  {
		echo "\r\nNo se pudo efectuar el registro en el servicio de factura digital. Le pedimos disculpas por las molestias ocasionadas\r\n";
		//var_dump($headers);
		//var_dump($result);
	}	
	
	
	
}
?>
