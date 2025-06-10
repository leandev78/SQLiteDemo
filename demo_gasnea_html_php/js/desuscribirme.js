function recargar() {
  // Limpia y enfoca el input del CAPTCHA, y regenera la imagen
  $("#codeCaptcha").val("");
  const rand   = 4;
  const height = 50;
  const width  = 200;

  $("#imgCaptcha").attr(
    "src",
    `../captcha/display.php?width=${width}&height=${height}&characters=${rand}&t=${Date.now()}`
  );
  $("#codeCaptcha").focus();
}

$(function() {
	
  // Carga inicial del CAPTCHA
  $("#imgCaptcha").attr("src", "../captcha/display.php");

  // Método adicional "pattern" para jQuery Validate. Lo uso para control de parametros.
  $.validator.addMethod(
    "pattern",
    function(value, element, regex) {
      return this.optional(element) || regex.test(value);
    },
    "Formato inválido."
  );

  // Validación y envío del formulario
  $("#unsubscribeForm").validate({
    rules: {
      reference: {
        required: true,
        pattern: /^\d{8}\/\d{2}$/
      },
      email: {
        required: true,
        email: true
      }
    },
    messages: {
      reference: {
        required: "Ingresa tu código de referencia.",
        pattern: "El formato debe ser 8 dígitos, barra y 2 dígitos. Ej: 00000001/02"
      },
      email: {
        required: "Ingresa tu correo electrónico.",
        email: "Ingresa un correo válido."
      }
    },
    submitHandler: function(form) {
      // Deshabilita el botón para evitar envíos dobles
      $("#btnUnsubscribe")
        .prop("disabled", true)
        .text("Procesando...");

      // Envío AJAX
      $.ajax({
        url: "../db/desuscripcion_accion.php",
        method: "POST",
        dataType: "json",
        data: $(form).serialize()
      })
        .done(function(resp) {
          if (resp.success) {
            Swal.fire({
              icon: "success",
              title: "¡Listo!",
              text: resp.message,
              confirmButtonText: "Cerrar"
            }).then(() => {
              $("#unsubscribeForm")[0].reset();
              recargar();
            });
          } else {
            Swal.fire({
              icon: "error",
              title: "No fue posible cancelar",
              text: resp.message,
              confirmButtonText: "Entendido"
            });
			recargar();
          }
        })
        .fail(function(xhr, status, err) {
          Swal.fire({
            icon: "error",
            title: "Error de conexión",
            text: "Intenta nuevamente en unos minutos.",
            confirmButtonText: "Reintentar"
          });
		  recargar();
        })
        .always(function() {
          $("#btnUnsubscribe")
            .prop("disabled", false)
            .text("Desuscribirme");
        });

      return false; // evita el submit estándar
    }
  });
});
