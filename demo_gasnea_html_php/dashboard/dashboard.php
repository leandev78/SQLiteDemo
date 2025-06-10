<?php
session_start();
if (!isset($_SESSION['user'])) {
    header("Location: index.html");
    exit;
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>SUSCRIPCIONES</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h3>Listado de Suscripciones</h3>
    <div class="text-end mb-2">
        <button class="btn btn-success btn-sm" onclick="cargarFormulario()">+ Nueva Suscripción</button>
    </div>
    <div id="tabla"></div>
</div>

<!-- Modal Nueva Suscripción -->
<div class="modal fade" id="modalForm" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog"><div class="modal-content">
    <div class="modal-header"><h5 class="modal-title">Suscripción</h5></div>
    <div class="modal-body">
        <form id="formSuscripcion">
            <input type="hidden" id="id">
            <div class="mb-2"><input type="text" id="referencia" placeholder="Referencia" class="form-control" required></div>
            <div class="mb-2"><input type="email" id="email" placeholder="Email" class="form-control" required></div>
            <div class="mb-2">
                <select id="confirmado" class="form-control">
                    <option value="1">Confirmado</option>
                    <option value="0">No confirmado</option>
                </select>
            </div>
            <button class="btn btn-primary w-100" type="submit">Guardar</button>
        </form>
    </div>
  </div></div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
function cargarSuscripciones() {
    $.get("data.php", function(html) {
        $("#tabla").html(html);
    });
}
function cargarFormulario(id = '', ref = '', email = '', conf = 0) {
    $("#id").val(id); $("#referencia").val(ref); $("#email").val(email); $("#confirmado").val(conf);
    new bootstrap.Modal(document.getElementById("modalForm")).show();
}
function eliminarRegistro(id) {
    if (confirm("¿Está seguro que desea eliminar este registro?")) {
        $.post("delete.php", { id: id }, function () {
            cargarSuscripciones();
        });
    }
}
$("#formSuscripcion").on("submit", function(e){
    e.preventDefault();
    $.post("save.php", {
        id: $("#id").val(),
        referencia: $("#referencia").val(),
        email: $("#email").val(),
        confirmado: $("#confirmado").val()
    }, function() {
        bootstrap.Modal.getInstance(document.getElementById("modalForm")).hide();
        cargarSuscripciones();
    });
});
$(document).ready(() => cargarSuscripciones());
</script>
</body>
</html>