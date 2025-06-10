// 10 minutos = 600 000 ms
const SESSION_TIMEOUT_MS = 5 * 60 * 1000;
let sessionTimer = null;


function encodea(encoded) {
	let decoded = '';
	for (let i = 0; i < encoded.length; i++) {
		let b = encoded.charCodeAt(i);
		let a = b ^ 123; // mismo número usado en PHP
		decoded += String.fromCharCode(a);
	}
	return decoded;
}

function manejarDocumento(cuenta, periodo, ladoSeleccionado, accion) {

	let lado = (ladoSeleccionado === "frente") ? "0" : "1";
	let modo = (accion === "ver") ? "1" : "0";
	let codigoAccion = "";
	let url = `includes/descarga.php?cuenta=${encodea(cuenta)}&periodo=${encodea(periodo)}&modo=${modo}&lado=${lado}&accion=${codigoAccion}`;

	window.open(url, '_blank');
}

function startSessionTimeout() {
	if (sessionTimer) clearTimeout(sessionTimer);

	sessionTimer = setTimeout(function() {
		// Cierro la sesión en el servidor
		fetch('../logout.php', {
				credentials: 'same-origin'
			})
			.then(() => {
				// Limpio el localStorage
				localStorage.removeItem("vistaActual");
				localStorage.removeItem("nroSuministro");

				// Muestro el alerta y al cerrarla, recargo la pagina
				return Swal.fire({
					title: "Sesión expirada por inactividad",
					text: "Por seguridad, te pedimos que inicies sesión de nuevo para continuar.",
					icon: "info",
					confirmButtonText: "OK",
					allowOutsideClick: false
				});
			})
			.then(() => {
				// Una vez que el usuario hace clic en "OK", recargamos
				window.location.href = '/index.php';
			});
	}, SESSION_TIMEOUT_MS);
}


function ajustarFooter() {
	var path = window.location.pathname;
	if (!/index.php/.test(path)) {
		$('#footer-top-margin').css('margin-top', '50px');
	}
}

function mostrarSeccion(vista) {
	if (vista === "detalle") {
		$("#section-home").hide();
		$("#section-details").show();
	} else {
		$("#section-home").show();
		$("#section-details").hide();
	}
}

function getDetallesFacturas(numSuministro) {
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url: 'db/getDetallesFacturas.php',
		data: {
			nroSuministro: numSuministro
		},
		success: function(response) {
			if (response.status === 0) {
				let html = "";
				response.data.forEach(function(item) {
					html += `<tr>
				<td data-label="Formulario">FRENTE</td>
				<td data-label="Período">${item.PERIODO_LIQUIDACION}</td>
				<td data-label="Comprobante">${item.REFERENCIA_COMPLETA}</td>
				<td data-label="Vencimiento">${item.FECHAVENC}</td>
				<td data-label="Acciones">
				  <button onclick="manejarDocumento('${item.CUENTA}','${item.periodo}','frente','ver')" 
					class="btn btn-primary btn-sm">
					<i class="fa fa-print"></i>&nbsp; Ver / Imprimir
				  </button>
				  <button onclick="manejarDocumento('${item.CUENTA}','${item.periodo}','frente','descargar')" 
					class="btn btn-success btn-sm">
					<i class="fa fa-cloud-download"></i>&nbsp; Descargar
				  </button>
				</td>
			  </tr>`;
					html += `<tr>
				<td data-label="Formulario">DORSO</td>
				<td data-label="Período">${item.PERIODO_LIQUIDACION}</td>
				<td data-label="Comprobante">${item.REFERENCIA_COMPLETA}</td>
				<td data-label="Vencimiento">${item.FECHAVENC}</td>
				<td data-label="Acciones">
				  <button onclick="manejarDocumento('${item.CUENTA}','${item.periodo}','dorso','ver')" 
					class="btn btn-primary btn-sm">
					<i class="fa fa-print"></i>&nbsp; Ver / Imprimir
				  </button>
				  <button onclick="manejarDocumento('${item.CUENTA}','${item.periodo}','dorso','descargar')" 
					class="btn btn-success btn-sm">
					<i class="fa fa-cloud-download"></i>&nbsp; Descargar
				  </button>
				</td>
			  </tr>`;
				});
				$("#factura-tabla tbody").html(html);

			} else {
				Swal.fire({
					icon: 'error',
					title: 'No se pudieron cargar las facturas',
					text: response.error || 'Hubo un problema al procesar tus datos. Por favor, inténtalo nuevamente más tarde.',
					confirmButtonText: 'Aceptar'
				});
			}
		},
		error: function(xhr, status, error) {
			Swal.fire({
				icon: 'error',
				title: 'Error de conexión',
				html: `
			  No fue posible comunicarse con el servidor.<br>
			  <strong>Estado:</strong> ${status}<br>
			  <strong>Detalles:</strong> ${error}
			`,
				confirmButtonText: 'Reintentar'
			}).then(() => {
				// opción: volver a intentar la carga
				$("#loading").show();
				Promise.all([
					getDetallesFacturas(numreferencia),
					getDetallesFacturasHistoricas(numreferencia)
				]).finally(() => {
					$("#loading").hide();
				});
			});
		}
	});
}

/*
Enta función determina si, dentro de un mismo PERIODO_LIQUIDACION, existe más de una factura (registro) con distintas fechas de emisión (FECHA_EMISION).
En ese caso:
Se asigna LIQ1 a la factura con fecha de emisión más reciente
Y LIQ2 a la que tenga una fecha de emisión anterior dentro de ese mismo período.
Si solo hay una factura para un PERIODO_LIQUIDACION, se la marca siempre como LIQ1.	
*/
function getDetallesFacturasHistoricas(numSuministro) {
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url: 'db/getDetallesFacturasHistoricas.php',
		data: {
			nroSuministro: numSuministro
		},
		success: function(response) {
			if (response.status === 0) {
				let html = '';
				const facturas = response.data;

				facturas.forEach((c1) => {
					// calcular tipo LIQ1 o LIQ2
					let tipo = 'LIQ1';
					const p1 = parseInt(c1.PERIODO_LIQUIDACION.replace(/\//g, ''), 10);
					const f1 = parseInt(c1.FECHA_EMISION.replace(/\//g, ''), 10);

					for (let c2 of facturas) {
						const p2 = parseInt(c2.PERIODO_LIQUIDACION.replace(/\//g, ''), 10);
						const f2 = parseInt(c2.FECHA_EMISION.replace(/\//g, ''), 10);
						if (p1 === p2 && f1 !== f2) {
							tipo = (f1 > f2) ? 'LIQ1' : 'LIQ2';
							break;
						}
					}

					html += `
				<tr>
				  <td data-label="Período"><strong>${c1.PERIODO_LIQUIDACION}</strong></td>
				  <td data-label="Tipo">
					<span class="badge ${tipo === 'LIQ1' ? 'bg-info' : 'bg-secondary'}">${tipo}</span>
				  </td>
				  <td data-label="Comprobante">${c1.REFERENCIA_COMPLETA}</td>
				  <td data-label="Vencimiento">${c1.FECHAVENC}</td>
				  <td data-label="Acciones">
					<button onclick="manejarDocumento('${c1.CUENTA}','${c1.PERIODO}','frente','ver')" 
					  id="btnVerFactura-${c1.PERIODO}${c1.CICLO}${c1.CODIGOBARRAS1}" 
					  class="btn btn-primary btn-sm">
					  <i class="fa fa-print"></i>&nbsp; Ver / Imprimir
					</button>
					<button onclick="manejarDocumento('${c1.CUENTA}','${c1.PERIODO}','frente','descargar')" 
					  id="btnDescargarFactura-${c1.PERIODO}${c1.CICLO}${c1.CODIGOBARRAS1}" 
					  class="btn btn-success btn-sm">
					  <i class="fa fa-cloud-download"></i>&nbsp; Descargar
					</button>
				  </td>
				</tr>`;
				});

				// Inyectar las filas en el tbody de la tabla
				$("#detalle-tabla tbody").html(html);

			} else {
				// Error lógico del servidor
				Swal.fire({
					icon: 'error',
					title: 'No se encontró historial',
					text: response.error || 'No fue posible cargar el historial de facturas. Intenta nuevamente más tarde.',
					confirmButtonText: 'Aceptar'
				});
			}
		},
		error: function(xhr, status, error) {
			// Error de comunicación
			Swal.fire({
				icon: 'error',
				title: 'Error de conexión',
				html: `
			  No pudimos obtener el historial de facturas.<br>
			  <strong>Estado:</strong> ${status}<br>
			  <strong>Detalle:</strong> ${error}
			`,
				confirmButtonText: 'Reintentar'
			}).then(() => {
				// Reintentar la llamada
				getDetallesFacturasHistoricas(numSuministro);
			});
		}
	});
}

function mostrarMensaje(mensaje, tipo) {
	var color = (tipo === "success") ? "#d4edda" : "#f8d7da";
	var border = (tipo === "success") ? "#c3e6cb" : "#f5c6cb";
	var textColor = (tipo === "success") ? "#155724" : "#721c24";

	$("#mensaje")
		.css({
			"background-color": color,
			"border": "1px solid " + border,
			"color": textColor
		})
		.text(mensaje)
		.fadeIn();
		
	// Ocultar automáticamente después de 4 segundos
	setTimeout(function() {
		$("#mensaje").fadeOut();
	}, 4000);		
}

function resetearBoton() {
	isProcessing = false;
	$("#btnSuscribirme").prop("disabled", false).text("Suscribirme");
}

// Definimos la llamada para validar el CAPTCHA
function validarCaptcha() {
	return $.ajax({
		url: 'includes/validarCaptcha.php',
		method: 'POST',
		dataType: 'json',
		data: $("#miFactura").serialize() // Se enviará el token de reCAPTCHA y el numreferencia
	});
}

// Definimos la llamada para chequear que el numreferencia exista en la BD
function validarSuministro(num) {
	return $.ajax({
		url: 'db/validarSuministro.php',
		method: 'POST',
		dataType: 'json',
		data: {
			numeroSuministro: num
		}
	});
}
	
$(document).ready(function() {

	var isProcessing = false;

	$("#miFactura").find("input, textarea, select").val('');
	
	mostrarSeccion(localStorage.getItem("vistaActual"));
	
	ajustarFooter();
	
	$('[data-toggle="popover"]').popover();

	// Recarga la informacion de las tablas tras un F5.
	var vista = localStorage.getItem("vistaActual");
	mostrarSeccion(vista);
	if (vista === "detalle") {
		var numreferencia = localStorage.getItem("nroSuministro");
		if (numreferencia) {
			$("#loading").show();
			// Llamo a mis funciones de forma asíncronas
			Promise.all([
				getDetallesFacturas(numreferencia),
				getDetallesFacturasHistoricas(numreferencia)
			]).finally(() => {
				$("#loading").hide();
			});
		}
	}

	$("#btnSalir").click(function() {
		localStorage.removeItem("vistaActual");
		localStorage.removeItem("nroSuministro");
		$.get("logout.php", function() {
			location.reload();
		});
	});

	$('#whatsapp-flotante').click(function() {
		window.open('https://api.whatsapp.com/send?phone=543446356139', '_blank');
	});


	//
	// SUSCRIPCION A FACTURA DIGITAL
	//
		
	$("#btnSuscribirme").click(function() {

		if (isProcessing) return;

		var numreferencia = localStorage.getItem("nroSuministro");

		if (numreferencia) {

			isProcessing = true;
			$("#btnSuscribirme").prop("disabled", true).text("Procesando...");
			$("#mensaje").hide().removeClass().text("");

			var email = $('#email').val();

			var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
			if (!emailRegex.test(email)) {
				mostrarMensaje("Por favor, ingresá una dirección de correo válida.", "error");
				resetearBoton();
				return;
			}

			$.ajax({
				url: 'db/suscripcion_accion.php',
				method: 'POST',
				data: {
					nref: numreferencia,
					email: email
				},
				success: function(response) {
					mostrarMensaje(response, "success");
				},
				error: function() {
					mostrarMensaje("Ocurrió un error al suscribirse.", "error");
				},
				complete: function() {
					$("#email").val("");
					resetearBoton();
				}
			});
		}
	});

	//
	// AUTENTIFICACION POR CAPTCHA
	//

	$("#miFactura").on("submit", function(e) {
		e.preventDefault();

		// Obtenemos el número de suministro (numreferencia) desde el input
		const numreferencia = $("#numSuministro").val().trim();

		if (!numreferencia) {
			Swal.fire({
				title: "Dato faltante",
				text: "Por favor, ingresá el número de suministro.",
				icon: "warning",
				confirmButtonText: "Entendido"
			});
			return;
		}

		// Mostramos el loader
		$("#loading").show();

		// Primer paso: validamos el CAPTCHA
		validarCaptcha()
			.then(function(captchaResponse) {
				if (captchaResponse.status !== 0) {
					// CAPTCHA inválido				
					Swal.fire({
						title: "Atención",
						text: "Debés marcar el checkbox “No soy un robot” antes de continuar.",
						icon: "info",
						confirmButtonText: "Entendido"
					});
					grecaptcha.reset();
					throw { // Necesario para forzar la terminación de la promesa.
						tipo: 'aviso',
						mensaje: "Debés marcar el checkbox “No soy un robot” antes de continuar.."
					};
				} else {
					// Si el captcha es correcto entonces verificamos el suministro en BD:
					return validarSuministro(numreferencia);
				}
			})
			.then(function(suministroResponse) {

				if (!suministroResponse.status) {
					// El número de suministro no existe
					Swal.fire({
						title: "Datos incorrectos",
						text: "El número de suministro no es válido. Verificá e ingresalo correctamente.",
						icon: "warning",
						confirmButtonText: "Entendido"
					});
					 grecaptcha.reset();
					throw { // Necesario para forzar la terminación de la promesa.
						tipo: 'error',
						mensaje: "El número de suministro no es válido. Verificá e ingresalo correctamente."
					};
				} else {
					// Si tanto el CAPTCHA como suministro son correctos entonces muestro la sección detalles
					localStorage.setItem("nroSuministro", numreferencia);
					localStorage.setItem("vistaActual", "detalle");

					// Animación de transición entre secciones
					return new Promise(function(resolve) {
						$("#section-home").fadeOut(300, function() {
							$("#section-details").fadeIn(300, function() {
								// Iniciamos timeout de logout automático
								startSessionTimeout();
								resolve();
							});
						});
					});
				}
			})
			.then(function() {
				// Una vez que la animación terminó, invocamos las funciones asíncronas para traer datos
				return Promise.all([
					getDetallesFacturas(numreferencia),
					getDetallesFacturasHistoricas(numreferencia)
				]);
			})
			.then(function() {
				// Una vez que getDetalles... y getDetallesHistoricas terminan, oculto loader
				$("#loading").hide();
			})
			.fail(function(error) {

				$("#loading").hide();

				if (error.tipo === 'captcha') {
					Swal.fire({
						title: "Código de seguridad no válido",
						text: error.mensaje,
						icon: "warning",
						confirmButtonText: "Entendido"
					});
				} else if (error.tipo === 'suministro') {
					Swal.fire({
						title: "Número de suministro no encontrado",
						text: error.mensaje,
						icon: "warning",
						confirmButtonText: "Entendido"
					});
				} else {
					Swal.fire({
						title: "Oops...",
						text: "Algo salió mal. Por favor, intentá nuevamente en unos minutos.",
						icon: "error",
						confirmButtonText: "Entendido"
					});
					console.error("Error no controlado en submit-miFactura:", error);
				}
			});
	});

});