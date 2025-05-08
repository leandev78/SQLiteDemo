package tienda.online.com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tienda.online.com.connection.Conexion;

public class Cliente extends Usuario {

    private String direccion;
    private String telefono;
    private String fechaRegistro;
   
    public Cliente() {}  // ¿Que pasaria si en Usuario no existiera un constructor vacio?
    
	public Cliente(int id, String nombre, String apellido, String email, String password, String direccion, String telefono, String fechaRegistro){
		super(id,nombre,apellido,email,password);
		this.direccion = direccion;
		this.telefono = telefono;
		this.fechaRegistro = fechaRegistro;
	}

	@Override
	public Cliente iniciarSesion(String miemail, String password) {
		
	       Conexion con = new Conexion();
	       Connection cx = con.conectar();

	        try {
	            String query = "SELECT * FROM cliente WHERE email = ? AND password = ?";
	            
	            PreparedStatement ps = cx.prepareStatement(query);
	            
	            // Seteamos los valores de los parámetros en orden
	            ps.setString(1, miemail);
	            ps.setString(2, password);	            
	            
	            ResultSet rs = ps.executeQuery();
	  
	            // Si hay un registro, las credenciales son correctas
	            if (rs.next()) {
	            	
	            	int id = rs.getInt("id_cliente");
	                String nombre = rs.getString("nombre");
	                String apellido = rs.getString("apellido");
	                String email = rs.getString("email");
	                String direccion = rs.getString("direccion");
	                String telefono = rs.getString("telefono");
	                String fechaRegistro = rs.getString("fecha_registro");
	                
	                System.out.println("Bienvenido/a: " + nombre + " " + apellido);
	                
	                Cliente cli = new Cliente(id, nombre, apellido, email, "",  direccion, telefono, fechaRegistro);
	                
	                return cli;
	                
	            } else {
	                System.out.println("Email o contraseña incorrectos.");
	                return null;
	            }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            con.desconectar();
	        }
	        
	        return null;
		
	}

	
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	
	
}
