package demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import demo.connection.ConexionMariaDB;


public class Principal {

	public static void main(String[] args) {

		
        // Obtener la conexión desde la clase ConexionMySQL
        Connection cx = ConexionMariaDB.getConnection();
        

        try {
            String query = "SELECT * FROM tipo_incidencia";
            Statement st = cx.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
            	int id = rs.getInt("tin_id");
                String tipo = rs.getString("tin_descripcion");
                System.out.println("\nNacionalidad: " + tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	ConexionMariaDB.closeConnection();
        }		
                                      
        
        
	}

}
