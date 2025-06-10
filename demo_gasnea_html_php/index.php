<?php
if (session_status() !== PHP_SESSION_ACTIVE) {
	session_start();
	$_SESSION[]="";
}
$authenticated = isset($_SESSION['authenticated']) && $_SESSION['authenticated'] === 'OK';
?>

<!DOCTYPE HTML>
<html class="no-js">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>GASNEA</title>
  <link href="images/favicon2.ico" rel="shortcut icon" type="image/x-icon" />

  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="format-detection" content="telephone=no">
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />

  <!-- CSS -->
  <link rel="stylesheet" href="css/bootstrap.css">
  <link rel="stylesheet" href="css/bootstrap-theme.css">
  <link rel="stylesheet" href="css/normalize.css">  
  <link rel="stylesheet" href="css/font-awesome.min.css">
  <link rel="stylesheet" href="css/magnific-popup.min.css">
  <link rel="stylesheet" href="css/custom.css"><!--iconos whatsapp/desplazamiento-->
  <link rel="stylesheet" href="css/style.css"> 
  <link rel="stylesheet" href="css/layout.css">  
  <link rel="stylesheet" href="css/detalle.css"> 
  <link rel="stylesheet" href="css/estilos_home.css"> 
  

  <script src="https://www.google.com/recaptcha/api.js" async defer></script>	

</head>

	<body class="home header-style2">

	  <div class="body">

		<?php include('pages/header.php'); ?>
		

		<div id="main-containers">
		
		  <div id="section-home" style="<?= $authenticated ? 'display:none;' : '' ?>">
		  
			<?php include('pages/home.php'); ?> 
		  
		  </div>
		  
		  <div id="section-details" style="<?= $authenticated ? '' : 'display:none;' ?>">
		  
			<?php include('pages/detalle.php'); ?>
			
		  </div>
		  
		</div>

		<?php include('pages/footer.php'); ?>

	  </div>

	
	  <!-- JS -->
	  <script src="js/jquery-2.1.3.min.js"></script>
	  <script src="js/bootstrap.js"></script>
	  <script src="js/ui-plugins.js"></script> <!-- necesario para visualizar el captcha-->
	  <script src="js/helper-plugins.js"></script> <!-- función helper para validar email -->
	  <script src="js/jquery.magnific-popup.min.js"></script>
	  <script src="js/init.js"></script> <!-- necesario para los link de Whatsapp laterales -->
	  <script src="js/modernizr.js"></script>
	  <script src="js/main.js"></script> <!-- nuevo, aca pongo todo -->
	  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</body>

</html>