<?php
require 'db.php';
$id = $_POST['id'] ?? null;
if ($id) {
    $stmt = $pdo->prepare("DELETE FROM SUSCRIPCIONES WHERE id = ?");
    $stmt->execute([$id]);
}
?>