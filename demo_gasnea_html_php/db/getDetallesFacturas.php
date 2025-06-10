<?php
session_start();
include_once("conexion.php");

header("Content-Type: application/json");

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $numSuministro = $_POST["nroSuministro"] ?? null;

    if (!$numSuministro) {
        echo json_encode(["status" => -1, "error" => "Número de suministro no recibido."]);
        exit();
    }

    $datos = getComprobantesActuales1($numSuministro, IDEMPRESA);

    echo json_encode($datos);
    exit();
}



function getComprobantesActuales1($cuenta, $idempresa) {

	$sql = "exec [dbo].[sp_getLastComprobanteByEmpresaCuenta_gasnea] :idempresa, :cuenta";
	$cant = 0;
    try {
        $dbCon = dbConnect();
        $stmt = $dbCon->prepare($sql);  
        $stmt->bindParam("idempresa", $idempresa);
		$stmt->bindParam("cuenta", $cuenta);
        $isQueryOK = $stmt->execute();
		if ($isQueryOK) { // si OK
			$result = $stmt->fetchAll(PDO::FETCH_OBJ);
			$cant = count($result);
			if ($cant > 0) { // si hay registros
				$json = array("status" => 0, "data" => $result);
				$_SESSION['GN_ENTRAR'] = "SI";
			} 
			else { // si no hay registros
				$json = array("status" => 1, "error" => "Factura no disponible");
			}
			if (isset($result)) 
				$result = null;
		}
		else { // no ok
			$json = array("status" => -3, "error" => "Error inesperado en la consulta a la base de datos.");
		}
		$stmt->closeCursor();
		$stmt = null; 
		dbDisconnect($dbCon);
    }
    catch(PDOException $e) {
		$json = array("status" => -4, "error" => $e->getMessage());
		if (isset($stmt))
			$stmt = null;
		dbDisconnect($dbCon);
    }
	return $json;
}

?>