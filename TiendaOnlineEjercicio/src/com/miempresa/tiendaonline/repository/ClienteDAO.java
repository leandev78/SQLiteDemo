package com.miempresa.tiendaonline.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.miempresa.tiendaonline.connection.Conexion;
import com.miempresa.tiendaonline.model.Cliente;

public class ClienteDAO implements IClienteDAO {

	
	public ClienteDAO() {}
	
	@Override
	public int grabar(Cliente c) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int actualizar(Cliente c) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int eliminar(Cliente c) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Cliente> leer() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * Permite verificar si el formato de las credenciales son válidas
	 * antes de realizar cualquier operación en la base de datos.
	 * @return True o False.
	 */		
	public boolean validarCredenciales(String email, String password) {
	    // Validaciones básicas
	    if (email == null || password == null) return false;
	    if (email.trim().isEmpty() || password.trim().isEmpty()) return false;

	    // No deben contener espacios
	    if (email.contains(" ") || password.contains(" ")) return false;

	    // Patrón de caracteres peligrosos
	    String[] patronesProhibidos = {"'", "\"", ";", "--", "/*", "*/", "\\", "="};

	    for (String patron : patronesProhibidos) {
	        if (email.contains(patron) || password.contains(patron)) return false;
	    }

	    // Validación de formato de email básico (opcional, más amigable para enseñar)
	    if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) return false;

	    return true;
	}	
	
	/**
	 * Permite verificar si un usuario existe en la base de datos.
	 * 
	 * @return Un cliente con todos sus atributos.
	 * @throws SQLException si ocurre un error de acceso a datos
	 */	
	public Cliente iniciarSesion(String email, String password) {
		
	    Conexion conexion = new Conexion();
	    Connection cx = conexion.conectar();
	    Cliente cliente = null;

	    String sql = "SELECT * FROM CLIENTE WHERE email = ? AND password = ?";

	    try (PreparedStatement ps = cx.prepareStatement(sql)) {

	        // Asignamos valores a los parámetros
	        ps.setString(1, email);
	        ps.setString(2, password);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                // Creamos el cliente si las credenciales son correctas
	                cliente = new Cliente(
	                    rs.getInt("id_cliente"),
	                    rs.getString("nombre"),
	                    rs.getString("apellido"),
	                    rs.getString("email"),
	                    "", // por seguridad, no devolvemos la contraseña
	                    rs.getString("direccion"),
	                    rs.getString("telefono"),
	                    rs.getString("fecha_registro")
	                );
	            } 
	        }

	    } catch (SQLException e) {
	        System.err.println("Error en la conexión o la consulta SQL.");
	        e.printStackTrace();
	    } finally {
	        conexion.desconectar();
	    }

	    return cliente;
	}	
	
	
}
