package com.miempresa.tiendaonline.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private Connection cx = null;
	private static final String DB_ENGINE = "sqlite"; // Cambiar entre "sqlite" y "mysql"

	public Connection conectar() {

		try {
			switch (DB_ENGINE.toLowerCase()) {
			
				case "sqlite":
					Class.forName("org.sqlite.JDBC");
					cx = DriverManager.getConnection("jdbc:sqlite:src/com/miempresa/tiendaonline/connection/carrito_compras.db");
					break;

					
				case "mysql":
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url = "jdbc:mysql://desarrollo.mysite.com.ar:3306/carrito_compras?useSSL=false&serverTimezone=UTC";
					cx = DriverManager.getConnection(url, "alumnosunpaz", "alumnosunpaz");
					break;

				default:
					throw new IllegalArgumentException("Motor de base de datos no soportado: " + DB_ENGINE);
			}
			// System.out.println("Conexión establecida con " + DB_ENGINE);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return cx;
	}

	public void desconectar() {
		try {
			if (cx != null && !cx.isClosed()) {
				cx.close();
				// System.out.println("Conexión cerrada.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
