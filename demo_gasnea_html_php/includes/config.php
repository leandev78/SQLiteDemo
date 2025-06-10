<?php

define("IDEMPRESA",16);
define("PROXY_ENABLED", FALSE);	
define("SITE_URL", "https://gasnea.ecofactura.com.ar");


/* inicio duracion de una sesion */
ini_set('session.cookie_lifetime', 600); // 10 min
ini_set('session.gc_probability', 3);
ini_set('session.gc_divisor', 100);
ini_set('session.gc_maxlifetime', 600);


/* DataBase connection info */
define("CONN_HOST", "impripost-2");
define("CONN_PORT", 1433);
define("CONN_USER", "impricold");
define("CONN_PASS", "1qaz!QAZ");
define("CONN_DB", "IMPRICOLD");


/* Define configuración para la generación de PDF. */
define("PATH_HOME", "/demo");    // cambiar a "gasnea"
define("PATH_PDF_REVERSO", "files");
define("PATH_PDF", "http://localhost:8080/ImpriViewer/MetroGas?cuenta=%1&empresa=%2&periodo=%3&download=%4");
define("FILE_PDF_REVERSO", "Factura-Reverso.pdf");
define("FILE_PDF", "GNEA-%1.pdf");


?>