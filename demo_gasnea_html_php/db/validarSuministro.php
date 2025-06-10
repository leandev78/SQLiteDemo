<?php

if (session_status() !== PHP_SESSION_ACTIVE) {
    session_start();
}

include_once("../db/conexion.php"); 

$_SESSION['authenticated'] = "";

header("Content-Type: application/json");

if ($_SERVER["REQUEST_METHOD"] !== "POST") {
    echo json_encode([
        "status" => -2,
        "error"  => "Se esperaba POST como Request_Method."
    ]);
    exit();
}

// Obtener parámetros
$numReferencia = $_POST["numeroSuministro"] ?? null;

// Validar número de referencia
if (empty($numReferencia)) {
	$json = array("status" => 0, "mensaje" => "Falta dato del numero de suministro."); 
}
else{

	// Verifico si el nro de referencia existe en la base de datos.
	try {
		$pdo = dbConnect();
		$stmt = $pdo->prepare("SELECT COUNT(CUENTA) AS cantidad FROM CICLOS WHERE CUENTA = ? AND IDEMPRESA = ?");
		$stmt->execute([$numReferencia, IDEMPRESA]);
		$result = $stmt->fetch();
		if ($result['cantidad'] > 0) {
			$_SESSION['authenticated'] = "OK";
			$json = array("status" => 1, "mensaje" => "Suministro encontrado.");
		}
		else {		
			$json = array("status" => 0, "mensaje" => "No existe el suministro.");
		}
	}
	catch(PDOException $e) {
					$json = array("status" => -1, "mensaje" => $e->getMessage());
	}		
	finally {
			dbDisconnect($pdo);
	}	

}

echo json_encode($json);

exit();
?>
