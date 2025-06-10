<?php

if (session_status() === PHP_SESSION_ACTIVE) {
    $_SESSION['authenticated'] = "";
}

?>

  <!-- Cabezote al estilo Gas NEA -->
  <header class="cabezal">
    <div class="container-cabezal">
      <h1>Consulta de Facturas</h1>
      <p>Ingrese su número de suministro y valide el CAPTCHA para continuar</p>
    </div>
  </header>

  <!-- Contenido principal -->
  <main class="wrapper-formulario">
	<div class="tarjeta-form">
	  <div class="contenido-tarjeta">
		<!-- Sección de la imagen de factura -->
		<div class="imagen-factura">
		  <img src="/images/factura_placeholder.jpg" alt="Imagen de Factura" />
		</div>

		<!-- Formulario: input, reCAPTCHA y botón -->
		<div>
		  <form name="miFactura" id="miFactura" method="post"  autocomplete="off">
			<label for="suministro">Número de suministro</label>
			<input
			  type="text"
			  id="numSuministro"
			  name="numSuministro"
			  placeholder="Ej: 123456789"
			  required
			/>

			<div class="captcha-container">
			  <div class="g-recaptcha" data-sitekey="6LdH9lcrAAAAACvnEUGYhC6BvUt5JLatpmJtwps0"></div>
			</div>

			<button type="submit">Buscar Factura</button>
		  </form>
		</div>
	  </div>
	</div>
  </main>


