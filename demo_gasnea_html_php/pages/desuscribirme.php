<?php

session_start();
$_SESSION[]="";


// Generar CSRF token si no existe
if (empty($_SESSION['csrf_token'])) {
    $_SESSION['csrf_token'] = bin2hex(random_bytes(32));
}

$csrf = $_SESSION['csrf_token'];
?>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Cancelar suscripción</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- Bootstrap CSS (opcional) -->
  <link href="../css/bootstrap.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
  <script src="../js/jquery-2.1.3.min.js"></script>
  <script src="../js/jquery.validate.min.js"></script>
  <script src="../js/desuscribirme.js"></script>

  <style>
	body {
	  padding-top: 60px;
	}  
    .form-container {
      max-width: 400px; margin: 3rem auto; padding: 2rem;
      background: #fff; border-radius: .5rem; box-shadow: 0 0 10px rgba(0,0,0,.1);
    }
    label.error {
      color: #dc3545; font-size: .9em; margin-top: .25rem;
    }
	#btnUnsubscribe {
	  margin: 1.5rem 0;
	}	
  </style>
</head>
<body class="bg-light">


	<header>
        <nav id="main-menu" class="navbar navbar-default navbar-fixed-top" role="banner" style="min-height:37px; height:35px;">
            <div class="container">
                <div class="navbar-header"></div>
            </div>
        </nav>
		<a class="navbar-brand" href="https://www.gasnea.com.ar/home.php"><img src="../images/logogas.png" style="width:20%; margin-top:-25px;margin-left: 125px;" alt="logo"></a>
    </header>
	
	<div class="form-container">
		<h2 class="text-center mb-4">Cancelar suscripción</h2>

		<form id="unsubscribeForm" novalidate>
		
		  <input type="hidden" name="csrf_token" value="<?php echo htmlspecialchars($csrf) ?>">
		   <!-- referencia -->
		  <div class="mb-3">
			<label for="reference" class="form-label">Código de referencia</label>
			<input type="text" class="form-control" id="reference" name="reference"
				   placeholder="00000001/02" required>
		  </div>
		   <!-- email -->
		  <div class="mb-3">
			<label for="email" class="form-label">Correo electrónico</label>
			<input type="email" class="form-control" id="email" name="email"
				   placeholder="ejemplo@correo.com" required>
		  </div>
		  <br/>
		  <!-- captcha 
		  <div class="mb-3">Ingres&aacute; los <strong>n&uacute;meros </strong><br/>que se ven en el <strong>recuadro</strong>: <br/>
			   <input name="codeCaptcha" id="codeCaptcha" type="text" maxlength="4"/>
	      </div>									
	      <div class="mb-3">
				<img id="imgCaptcha" alt="captcha"><br/>
				<a class="recargar" href="javascript: recargar();"><span class="btnRecarga">Recargar imagen</span></a>
	      </div>			  
		  <br/>-->
		  <div class="d-grid mt-4">
			<button type="submit" id="btnUnsubscribe" class="btn btn-danger">Desuscribirme</button>
		  </div>
		  
		</form>
	</div>

</body>
</html>
