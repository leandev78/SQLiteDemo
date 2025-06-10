<?php

include_once("../includes/config.php"); 

function dbConnect() {
    try {
		$conn = new PDO("sqlsrv:server=".CONN_HOST."; Encrypt=true;TrustServerCertificate=true; Database=".CONN_DB, CONN_USER, CONN_PASS);
        $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    } 
	catch(PDOException $e) {
        dbDisconnect($conn);
		$json = array("status" => -1, "error" => $e->getMessage());
        $contenttype = "Content-type: ";
        $contenttype .= "application/json";
        header($contenttype);
        echo json_encode($json);		
		die();
    }
    return $conn;
}

function dbDisconnect(&$conn) {
	if (!is_null($conn))
		$conn = null;
}


?>