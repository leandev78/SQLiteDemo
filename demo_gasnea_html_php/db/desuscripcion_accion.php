<?php

if (session_status() !== PHP_SESSION_ACTIVE) {
session_start();
}

include_once(__DIR__ . "/conexion.php");

header('Content-Type: application/json; charset=UTF-8');

// *************************************
// Control anti Hacking por captcha
// *************************************

$codeCaptcha = $_POST['codeCaptcha'] ?? '';
//if($codeCaptcha !== $_SESSION['security_code']){
//    echo json_encode([
//	  'success' => false,
//	  'message' => 'Captcha de seguridad no válido. Código ingresado: ' . $codeCaptcha . ' = ' . $_SESSION['security_code'],
//    ]);
//    exit;	
//}

// *************************************
// Control anti Hacking por fuerza bruta
// *************************************

// 1) CSRF check
$token = $_POST['csrf_token'] ?? '';
if (empty($token) || !hash_equals($_SESSION['csrf_token'] ?? '', $token)) {
    echo json_encode([
      'success' => false,
      'message' => 'Token de seguridad no válido.'
    ]);
    exit;
}

// 2) Limnitación de intentos (5 intentos / 60s)
$limit = 3;
$window = 60; // segundos
$now = time();

if (!isset($_SESSION['rate'])) {
    $_SESSION['rate'] = ['count'=>0, 'start'=>$now];
}
if ($now - $_SESSION['rate']['start'] <= $window) {
    $_SESSION['rate']['count']++;
} else {
    // reinicia ventana
    $_SESSION['rate'] = ['count'=>1, 'start'=>$now];
}

if ($_SESSION['rate']['count'] > $limit) {
    echo json_encode([
      'success' => false,
      'message' => 'Demasiados intentos. Por favor, espera un minuto e inténtalo de nuevo.'
    ]);
    exit;
}


// *************************************
// Acceso a la base de datos.
// *************************************

try {
	$pdo = new PDO("sqlsrv:server=" . CONN_HOST . "; Encrypt=true;TrustServerCertificate=true; Database=" . CONN_DB, CONN_USER, CONN_PASS);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    echo json_encode([
      'success' => false,
      'message' => 'Error de conexión a la base de datos.'
    ]);
    exit;
}

if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    echo json_encode([
      'success' => false,
      'message' => 'Método no permitido.'
    ]);
    exit;
}

// Validación de mis parametros
$reference = trim($_POST['reference'] ?? '');
$email     = trim($_POST['email']     ?? '');

if (!preg_match('/^\d{8}\/\d{2}$/', $reference)) {
    echo json_encode([
      'success' => false,
      'message' => 'Código de referencia inválido.'
    ]);
    exit;
}
if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    echo json_encode([
      'success' => false,
      'message' => 'Correo electrónico inválido.'
    ]);
    exit;
}

try {
    $stmt = $pdo->prepare("
      DELETE FROM [dbo].[SUSCRIPCIONES]
      WHERE referencia = :ref AND email = :email
    ");
	
	// Borrado lógico
	//$stmt = $pdo->prepare("
    //  UPDATE [dbo].[SUSCRIPCIONES]
    //  SET borrado_logico = 1,
	//	  fecha_borrado = GETDATE(),
	//      token = NULL     -- invalido el token para que no vuelva a usarse
    //  WHERE referencia = :ref AND email = :email
	//");
		
    $stmt->execute([
      ':ref'   => $reference,
      ':email' => $email
    ]);

    if ($stmt->rowCount() > 0) {
        echo json_encode([
          'success' => true,
          'message' => 'Tu suscripción ha sido cancelada correctamente.'
        ]);
    } else {
        echo json_encode([
          'success' => false,
          'message' => 'No encontramos ninguna suscripción con esos datos.'
        ]);
    }
} catch (PDOException $e) {
    echo json_encode([
      'success' => false,
      'message' => 'Error interno. Por favor contacta al soporte.'
    ]);
}


?>



