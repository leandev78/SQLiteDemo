package sqlite.code;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sqlite.code.connection.Conexion;
import sqlite.code.model.Cliente;
import sqlite.code.model.Empleado;
import sqlite.code.model.Persona;

/**

Se ha creado una base de datos llamada sample.db con una tabla personas. En esta tabla se almacenan datos de clientes y empleados. 
Tu tarea es construir una aplicación Java que:
1) Lea los datos desde la base de datos.
2) Cree objetos Cliente o Empleado dependiendo del campo tipo.
3) Implemente una estructura orientada a objetos que utilice interfaz, clase abstracta, polimorfismo, y uso de atributos static y final.

*/

public class Main {

	public static void main(String[] args) {

	       Conexion con = new Conexion();
	       Connection cx = con.conectar();

	        List<Persona> personas = new ArrayList<>();

	        try {
	            String query = "SELECT * FROM personas";
	            Statement st = cx.createStatement();
	            ResultSet rs = st.executeQuery(query);

	            while (rs.next()) {
	            	int id = rs.getInt("id");
	                String tipo = rs.getString("tipo");
	                String nombre = rs.getString("nombre");
	                String dni = rs.getString("dni");

	                if ("empleado".equalsIgnoreCase(tipo)) {
	                    double salario = rs.getDouble("salario");
	                    personas.add(new Empleado(id,nombre, dni, salario));
	                } else if ("cliente".equalsIgnoreCase(tipo)) {
	                    int puntos = rs.getInt("puntosFidelidad");
	                    personas.add(new Cliente(id,nombre, dni, puntos));
	                }
	            }

	            for (Persona p : personas) {
	                p.mostrarInformacion(); // POLIMORFISMO
	            }

	            System.out.println("\nTotal de personas cargadas: " + Persona.totalPersonas);
	            System.out.println("\nNacionalidad: " + Persona.NACIONALIDAD);
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            con.desconectar();
	        }		
		
	}

}
