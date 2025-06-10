<?php
require 'db.php';
$id = $_POST['id'];
$referencia = $_POST['referencia'];
$email = $_POST['email'];
$confirmado = $_POST['confirmado'];
if ($id) {
    $stmt = $pdo->prepare("UPDATE SUSCRIPCIONES SET referencia=?, email=?, confirmado=? WHERE id=?");
    $stmt->execute([$referencia, $email, $confirmado, $id]);
} else {
    $stmt = $pdo->prepare("INSERT INTO SUSCRIPCIONES (referencia, email, confirmado, fecha_confirmacion) VALUES (?, ?, ?, GETDATE())");
    $stmt->execute([$referencia, $email, $confirmado]);
}
?>