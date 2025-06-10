<?php
session_start();

include_once(__DIR__ . "/conexion.php");

// 1) Rate–limiting por sesión/IP (5 intentos / 60s)
$limit  = 5;
$window = 60; // segundos
$now    = time();

// Inicializa contador si no existe
if (!isset($_SESSION['confirm_rate'])) {
    $_SESSION['confirm_rate'] = ['count' => 0, 'start' => $now];
}

// Incrementa o reinicia ventana
if ($now - $_SESSION['confirm_rate']['start'] <= $window) {
    $_SESSION['confirm_rate']['count']++;
} else {
    $_SESSION['confirm_rate'] = ['count' => 1, 'start' => $now];
}

// Si supera el límite, rechaza
if ($_SESSION['confirm_rate']['count'] > $limit) {
    http_response_code(429); // Too Many Requests
    die("<h2>Demasiados intentos. Por favor espera un minuto antes de volver a intentarlo.</h2>");
}

// 2) Comprueba que venga el token y que tenga el formato esperado (ej. hexadecimales)
if (empty($_GET['token']) || !preg_match('/^[0-9a-f]{32,64}$/i', $_GET['token'])) {
    http_response_code(400);
    die("<h2>Token no válido.</h2>");
}

$token = $_GET['token'];

try {
    // 3) Conexión segura con PDO
    $pdo = new PDO(
      "sqlsrv:server=".CONN_HOST."; Encrypt=true;TrustServerCertificate=true; Database=".CONN_DB,
      CONN_USER, CONN_PASS,
      [PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION]
    );

    // 4) Busca el registro con token Y confirmado = 0
    $stmt = $pdo->prepare("
      SELECT id 
      FROM [dbo].[SUSCRIPCIONES] 
      WHERE token = :token AND confirmado = 0
    ");
    $stmt->execute([':token' => $token]);
    $row = $stmt->fetch(PDO::FETCH_ASSOC);

    if (!$row) {
        // El token no existe o ya fue usado
        http_response_code(404);
        die("<h2>Token no válido o ya utilizado.</h2>");
    }

    // 5) Actualiza confirmado = 1 y elimina (o invalida) el token
    $stmt = $pdo->prepare("
      UPDATE [dbo].[SUSCRIPCIONES]
      SET confirmado = 1, fecha_confirmacion = GETDATE(),
          token = NULL     -- invalido el token para que no vuelva a usarse
      WHERE id = :id
    ");
    $stmt->execute([':id' => $row['id']]);

    echo "<h2>¡Gracias! Tu email ha sido confirmado correctamente.</h2>";

} catch (PDOException $e) {
    // No enviar detalles del error al usuario en producción
    http_response_code(500);
    die("<h2>Error interno del servidor. Por favor inténtalo más tarde.</h2>");
}

?>
