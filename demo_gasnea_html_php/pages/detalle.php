<?php
if (session_status() !== PHP_SESSION_ACTIVE) {
	session_start();
	if (!isset($_SESSION['authenticated']) || $_SESSION['authenticated'] !== 'SI') {
		http_response_code(403);
		exit('Acceso no autorizado');
	}
}

include_once("includes/functions.php");
?>

<div id="section-details">
  <div class="container my-4">

	<div id="loading" style="display:none; text-align:center; margin:20px;">
	  <div class="spinner"></div>
	  <p>Cargando...</p>
	</div>


    <div class="text-center mb-4">
      <h1 class="mt-3">Detalle de tus facturas</h1>
	  <p class="subtitulo">Tu factura se encuentra disponible.</p>
    </div>

    <!-- Paso 3 -->
    <div class="mb-4">
      <!-- <div class="step-title mb-2">PASO 3</div>-->
      <hr style="border: 1px solid #8BC34A; opacity: 0.8;">
    </div>

    <!-- Período Facturado -->
    <h2><i class="fa fa-file"></i> Período Facturado</h2>

	<div id="factura-tabla" class="table-responsive">
		<table class="facturas">
		  <thead>
			<tr>
			  <th>Formulario</th>
			  <th>Período</th>
			  <th>Comprobante</th>
			  <th>Vencimiento</th>
			  <th>Acciones</th>
			</tr>
		  </thead>
		  <tbody>
		  <!-- Contenido dinámico -->
		  </tbody>
		</table>
	</div>

    <!-- Historial de Facturas -->
	
    <h2><i class="fa fa-file-text"></i> Historial de Facturas</h2>
	
	<div class="table-responsive">
	  <table  id="detalle-tabla" class="facturas">
		<thead>
		  <tr>
			<th>Período</th>
			<th>Tipo</th>
			<th>Comprobante</th>
			<th>Vencimiento</th>
			<th>Acciones</th>
		  </tr>
		</thead>
		<tbody>
			<!-- Contenido dinámico -->
		</tbody>
	  </table>
	</div>	
	    
	
	
    <!-- Botón de salida -->
    <div class="text-center">
      <button type="button" id="btnSalir" class="btn btn-secondary azul"><i class="fa fa-arrow-left me-2" aria-hidden="true"></i> Volver</button>
    </div>
	
	<br/>
	
	<hr style="border: 1px solid #8BC34A; opacity: 0.8;">
		
  </div>
    

<div class="container mensajes">
	<p class="text-info">Información importante</p>
	<div class="alert alert-warning">
	  <h5><i class="fa fa-exclamation-triangle"></i> Sobre la descarga</h5>
	  <ul class="mb-0">
		<li>Las descargas pueden incluir dos liquidaciones o dos facturas.</li>
		<li>Se visualizarán una a continuación de la otra.</li>
	  </ul>
	</div>

	<div class="alert alert-info">
	  <h5><i class="fa fa-info-circle"></i> Sobre el uso de la factura</h5>
	  <ul class="mb-0">
		<li>Podrás acceder a ver, imprimir o guardar una copia de la última factura enviada a tu domicilio. El acceso a la factura es exclusiva del titular del suministro.</li> 
		<li>La impresión de la copia de tu factura es válida para pagarla en cualquiera de las entidades autorizadas de cobro.</li>  
	  </ul>
	</div>
	
	<!-- Medios de pago -->
	<div class="alert alert-success">
	  <h5><i class="fa fa-credit-card text-money"></i> Sobre medios de pago</h5>
	  <ul class="mb-0">
		<li>Para conocer los medios de pago hacé <a href="https://www.gasnea.com.ar/clientes_formas_lugares_de_pago.php" class="btn btn-success"> click aquí</a></li> 
	  </ul>
	</div>	

	<br/><br/>
	<hr style="border: 1px solid #8BC34A; opacity: 0.8;">
	
</div>



<!-- Formulario de adhesión 
<div id="seccion-formulario">
	<div class="d-flex justify-content-center align-items-center form-espaciado" >
	  <div class="container form-container">
		<div class="card p-4 formulario">
		  <h3 class="mb-3" style="text-align: center;"><i class="fa fa-envelope fa-lg" style="color:#b1b3b5;"></i> Adhesión a Factura Digital</h3>
		  <p>Si desea recibir sus facturas en formato digital, puede completar el siguiente formulario con su dirección de correo electrónico.</p>
		  <form name="miFacturaDigital" id="miFacturaDigital" method="post" class="row g-3 align-items-center">
			<div class="col-md-12">
			  <label for="emailAdhesion" class="form-label">Correo electrónico</label>
			  <input type="email" name="email" id="email" class="form-control" id="emailAdhesion" placeholder="ejemplo@correo.com" required>
			  <button id="btnSuscribirme" class="btn btn-secondary w-100 btn-suscribirme mt-3">Suscribirme</button>
			</div>
		  </form>
		  <div id="mensaje" style="display:none; margin-top: 10px; padding: 10px; border-radius: 5px;"></div>
		</div>
	  </div>
	</div>  
</div> -->
  
  
</div>


<br/>