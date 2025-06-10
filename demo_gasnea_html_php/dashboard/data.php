<?php
include_once("db.php");


try {
	$pdo = dbConnect();

	$stmt = $pdo->query("SELECT id, referencia, email, confirmado, fecha_confirmacion FROM [dbo].[SUSCRIPCIONES] ORDER BY id DESC");
	echo '<table class="table table-bordered table-striped">';
	echo '<thead class="table-primary"><tr><th>ID</th><th>Referencia</th><th>Email</th><th>Confirmado</th><th>Fecha</th><th>Acciones</th></tr></thead>';
	while ($r = $stmt->fetch()) {
		echo "<tr>
			<td>{$r['id']}</td>
			<td>{$r['referencia']}</td>
			<td>{$r['email']}</td>
			<td>{$r['confirmado']}</td>
			<td>{$r['fecha_confirmacion']}</td>
			<td>
				<button class='btn btn-sm btn-primary' onclick=\"cargarFormulario('{$r['id']}', '{$r['referencia']}', '{$r['email']}', '{$r['confirmado']}')\">Editar</button>
				<button class='btn btn-sm btn-danger' onclick=\"eliminarRegistro({$r['id']})\">Eliminar</button>
			</td>
		</tr>";
	}
	echo '</table>';

} 
catch(PDOException $e) {
	echo "Error en base de datos: " . $e->getMessage();
	exit;
}
finally {
	if (isset($pdo)) $pdo = null;
}

?>