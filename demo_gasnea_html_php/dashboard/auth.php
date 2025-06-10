<?php
session_start();

include_once("db.php");


if ($_SERVER["REQUEST_METHOD"] == "POST") {
	
	$username = $_POST['username'] ?? '';
	$password = $_POST['password'] ?? '';
	
	
	try {
		$pdo = dbConnect();
		
		$stmt = $pdo->prepare("SELECT * FROM [eco].[users] WHERE username = ? AND enabled = 1");
		$stmt->execute([$username]);
		$user = $stmt->fetch();
		
		$cleaned_password_us = preg_replace('/\s+/', '', $password);
		$cleaned_password_db = preg_replace('/\s+/', '', $user['password']);
			
		if ($user && password_verify($cleaned_password_us, $cleaned_password_db)) {
			$_SESSION['user'] = $user['username'];
			echo json_encode(["success" => true]);
		} else {
			echo json_encode(["success" => false, "message" => "Credenciales inválidas"]);
		}
		
	} 
	catch(PDOException $e) {
		echo "Error en base de datos: " . $e->getMessage();
		exit;
	}
	finally {
		if (isset($pdo)) $pdo = null;
	}	
	
	
}

	
?>