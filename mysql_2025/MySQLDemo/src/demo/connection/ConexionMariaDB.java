package demo.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionMariaDB {

    // Datos de la base de datos 	
    //private static final String URL = "jdbc:mysql://45.227.163.112:3306/dbdev";
	private static final String URL = "jdbc:mysql://desarrollo.mysite.com.ar:3306/dbdev";
    private static final String USER = "demo";
    private static final String PASS = "peperina34";
    private static Connection conn = null;
    
    // Método para obtener la conexión
    public static Connection getConnection() {
        
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Conexión establecida.");
            } catch (SQLException e) {
                System.out.println("Error de conexión: " + e.getMessage());
            }
        }
        return conn;
    }

    // Método para cerrar la conexión
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada.");
                conn = null;
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

}