<?php
// Configuración de cabeceras HTTP para evitar cacheo
header("Expires: Mon, 26 Jul 1997 05:00:00 GMT");
header("Cache-Control: no-cache");
header("Pragma: no-cache");

// Para ver los errores
//ini_set('display_errors', 1);
//ini_set('display_startup_errors', 1);
//error_reporting(E_ALL);

include_once(__DIR__ . "/config.php");
include_once(__DIR__ . "/functions.php");


if (session_status() !== PHP_SESSION_ACTIVE) {
    session_start();
}

/**
 * Función para decodificar una cadena codificada con XOR 123
 */
function decodeXOR($encoded)
{
    $decoded = '';
    for ($i = 0; $i < strlen($encoded); $i++) {
        $decoded .= chr(ord($encoded[$i]) ^ 123);
    }
    return $decoded;
}

/**
 * Verifica si los parámetros requeridos están presentes
 */
function areParamsValid($params)
{		
    foreach ($params as $param) {
        if ($param == "") {
            return false;
        }
    }
	
    return isset($_SESSION['authenticated']) && $_SESSION['authenticated'] !== "";
}

/**
 * Envía el PDF correspondiente
 */
function sendPDF($pdf_url, $pdf_file, $viewType)
{
    header('X-Sendfile: ' . $pdf_url);
    header('Content-Type: application/pdf');
    header('Content-Transfer-Encoding: binary');

    if ($viewType === 'descarga') {
        header('Content-Disposition: attachment; filename="' . $pdf_file . '"');
    } else {
        header('Content-Disposition: inline; filename="' . $pdf_file . '"');
        header('Accept-Ranges: bytes');
    }
}

function areAccountPeriodValid($cuenta, $periodo)
{
    if (!preg_match('/^\d{8}\/\d{2}$/', $cuenta)) {
        return false;
    }

    if (!preg_match('/^(0[1-9]|1[0-2])-\d{4}$/', $periodo)) {
        return false;
    }

    return true;
}


/**
 * Entrada principal
 */
$numRefEncoded = $_GET["cuenta"] ?? "";
$periodEncoded = $_GET["periodo"] ?? "";
$viewMode = $_GET["modo"] ?? ""; // 0: descarga, 1: visualiza
$side = $_GET["lado"] ?? "";     // 0: frente, 1: dorso
$order = $_GET["e"] ?? "";       // para control de orden

$frenteDorso = "";
$descargaVisualiza = "";

if (!areParamsValid([$numRefEncoded, $periodEncoded, $viewMode, $side])) {
    echo "Error: la sesión ha expirado o faltan parámetros. Por favor, ingrese nuevamente. Gracias.";	
} else {
	
    // Decodificación de parámetros
    $numReferencia = decodeXOR($numRefEncoded);
    $periodo = decodeXOR($periodEncoded);
	
	if (!areAccountPeriodValid($numReferencia, $periodo)) {
		echo "Error: los parámetros proporcionados no tienen el formato esperado.";
		exit();
	}

    $params = [
        "cuenta" => $numReferencia,
        "periodo" => $periodo,
        "empresa" => IDEMPRESA,
        "download" => "N"
    ];

    if ($side === "1") {
        // Dorso
		$path = str_replace('includes', '', __DIR__);
        $pdf_url = $path . "/" . PATH_PDF_REVERSO . "/" . FILE_PDF_REVERSO;
        $pdf_file = FILE_PDF_REVERSO;
        $frenteDorso = "dorso";
    } else {
        // Frente
		$pdf_url = substr(PATH_PDF, 0, strpos(PATH_PDF,"?")); // toma string izquierdo url hasta "?" de PATH_PDF
		$pdf_file = str_replace("%1", $numReferencia, FILE_PDF);
		$pdf_file = str_replace("/", "-", $pdf_file);
        $frenteDorso = "frente";		
    }

    $descargaVisualiza = $viewMode === "0" ? "descarga" : "visualiza";
	
    sendPDF($pdf_url, $pdf_file, $descargaVisualiza);

    if ($frenteDorso === "dorso") {
        @readfile($pdf_url);
    } else {
        echo httpPost($pdf_url, $params);
    }

}


exit();


?>