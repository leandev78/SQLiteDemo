package sqlite.code.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private Connection cx = null;
	
	public Connection conectar() {
		
		try {
			Class.forName("org.sqlite.JDBC");
			this.cx=DriverManager.getConnection("jdbc:sqlite:src/sqlite/code/connection/sample.db");
			System.out.println("Conexión exitosa !!!");
		} catch (ClassNotFoundException| SQLException e) {
			e.printStackTrace();
		}
			
		return cx;
		
	}
	
	
	public void desconectar() {
		
		try {
			this.cx.close();
			System.out.println("Se desconecto de la db !!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
