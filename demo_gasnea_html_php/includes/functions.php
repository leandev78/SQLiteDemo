<?php


function httpPost($url,$params)  // http://hayageek.com/php-curl-post-get/
{
	$postData = '';
	//create name value pairs seperated by &
	foreach($params as $k => $v) { 
	  $postData .= $k . '='.$v.'&'; 
	}
	$postData = rtrim($postData, '&');
	$ch = curl_init();  
	curl_setopt($ch,CURLOPT_URL,$url);
	curl_setopt($ch,CURLOPT_RETURNTRANSFER,true);
	curl_setopt($ch,CURLOPT_HEADER, false); 
	curl_setopt($ch, CURLOPT_POST, count((array)$postData));
	curl_setopt($ch, CURLOPT_POSTFIELDS, $postData);    
	$output=curl_exec($ch);
	curl_close($ch);
	return $output;
}

function httpGet($url)  // http://hayageek.com/php-curl-post-get/
{
	$ch = curl_init();  
	curl_setopt($ch,CURLOPT_URL,$url);
	curl_setopt($ch,CURLOPT_RETURNTRANSFER,true);
	//  curl_setopt($ch,CURLOPT_HEADER, false); 
	$output=curl_exec($ch);
	curl_close($ch);
	return $output;
}

// encodea-desencodea texto
function encodea($encoded)   // <-- encoded string from the request
{
	$decoded = "";
	for( $i = 0; $i < strlen($encoded); $i++ ) {
		$b = ord($encoded[$i]);
		$a = $b ^ 123;  // <-- must be same number used to encode the character
		$decoded .= chr($a);
	}
	return $decoded;
}

// devuelve si un dato es nulo o no
function esDatoNulo ($pvalor) {
	$es = false;
	if ($pvalor=="" || strtolower($pvalor)=="null" || $pvalor=="-")
		$es = true;
	return $es;
}

// arma string parametros para link a factura
function armaLinkFactura ($cuenta, $periodo, $pv, $pf, $pe) {
	$linkfactura = "descargar.php?nro=".encodea($cuenta)."&per=".encodea($periodo)."&v=".$pv."&f=".$pf."&e=".$pe;
	return $linkfactura;	
}

// arma string parametros para link a factura (mismo anterior pero difiere en PERIODO (mayuscula) porque el SP lo trae así
function armaLinkFacturaHistorial (&$obj_arr, $pv, $pf, $pe) {
	global $page;
	$linkfactura = $page.".php?nro=".encodea($obj_arr["CUENTA"])."&per=".encodea($obj_arr["PERIODO"])."&v=".$pv."&f=".$pf."&e=".$pe;
	return $linkfactura;	
}

// arma string parametros para link a factura (mismo anterior pero difiere en PERIODO (mayuscula) porque el SP lo trae así
function armaLinkFacturaHistorialV2 ($cuenta, $periodo, $pv, $pf, $pe) {
	global $page;
	$linkfactura = $page.".php?nro=".encodea($cuenta)."&per=".encodea($periodo)."&v=".$pv."&f=".$pf."&e=".$pe;
	return $linkfactura;	
}

?>
