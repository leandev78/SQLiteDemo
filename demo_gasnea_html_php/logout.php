<?php
session_start();

// Limpio las variables de sesión
$_SESSION = [];

// Limpio las cookie eliminándola del nagegador
if (ini_get("session.use_cookies")) {
    $params = session_get_cookie_params();
    setcookie(
        session_name(),    // normalmente "PHPSESSID"
        '',                // valor vacío
        time() - 42000,    // fecha en el pasado
        $params["path"],
        $params["domain"],
        $params["secure"],
        $params["httponly"]
    );
}

// Destruye la sesión en el servidor
session_destroy();



?>