package tienda.online.com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private Connection cx = null;
	
	public Connection conectar() {
		
		try {
			Class.forName("org.sqlite.JDBC");
			this.cx=DriverManager.getConnection("jdbc:sqlite:src/tienda/online/com/connection/carrito_compras.db");
			//System.out.println("# Base de datos: Conexión exitosa !!!");
		} catch (ClassNotFoundException| SQLException e) {
			e.printStackTrace();
		}
			
		return cx;
		
	}
	
	
	public void desconectar() {
		
		try {
			this.cx.close();
			//System.out.println("# Base de datos: Se desconecto exitosamente !!!\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
