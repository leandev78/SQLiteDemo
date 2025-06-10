<?php

// Clave secreta del reCAPTCHA
$secretKey = "6LdH9lcrAAAAAPBWJ2YAj2eR3d9kfH-lOyHxyZ7g";

// Captura la respuesta del reCAPTCHA enviada por el formulario
$responseKey = $_POST['g-recaptcha-response'] ?? null;

// Captura la IP del usuario
$userIP = $_SERVER['REMOTE_ADDR'] ?? '';

// Validación: ¿el usuario completó el captcha?
if (!$responseKey) {
	$json = array("status" => -1, "mensaje" => "Por favor, completá el reCAPTCHA.");
}
else{
	// Construye la URL para validar el token con Google
	$url = "https://www.google.com/recaptcha/api/siteverify?secret=$secretKey&response=$responseKey&remoteip=$userIP";

	// Realiza la solicitud a Google (sin usar cURL)
	$response = file_get_contents($url);

	// Decodifica la respuesta JSON
	$result = json_decode($response, true);

	// Muestra el resultado para debug
	//echo "<pre>";
	//print_r($result);
	//echo "</pre>";

	// Verifica si el reCAPTCHA fue exitoso
	if (!empty($result['success']) && $result['success'] === true) {
		//echo "Validación exitosa. Bienvenido/a, " . htmlspecialchars($_POST['nombre'] ?? 'Usuario') . ".";
		$json = array("status" => 0, "mensaje" => "Comprobante encontrado.");
	} else {
		//echo "Error en la validación del reCAPTCHA.";
		$json = array("status" => -3, "mensaje" => "Código validador reCAPTCHA incorrecto. Intenta nuevamente.");
	}
}

echo json_encode($json);

?>
