package com.miempresa.tiendaonline.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.miempresa.tiendaonline.connection.Conexion;
import com.miempresa.tiendaonline.model.Producto;


public class ProductoDAO implements IProductoDAO {
		
	public ProductoDAO() {};
	

	/**
	 * Obtiene todos los productos de la tabla producto.
	 */	
	public List<Producto> leer() {

		List<Producto> list = new ArrayList<>();
		
		Conexion con = new Conexion();
		Connection cx = con.conectar();

		try {
			String query = "SELECT * FROM PRODUCTO";
			Statement st = cx.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				list.add(
						new Producto(
							rs.getInt("id_producto"), 
							rs.getString("nombre"), 
							rs.getString("descripcion"), 
							rs.getDouble("precio"), 
							rs.getInt("stock"))
						);
			}		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.desconectar();
		}		
		
		return list;		
		
	}	
	
	/**
	 * Obtiene el valor máximo de la columna id_producto en la tabla producto.
	 * 
	 * @return el ID más alto encontrado, o 0 si la tabla está vacía
	 * @throws SQLException si ocurre un error de acceso a datos
	 */
	public static int obtenerMaxIdProducto(){
	    String sql = "SELECT MAX(id_producto) AS ultimo FROM PRODUCTO";

		Conexion con = new Conexion();
		Connection cx = con.conectar();
		
	    // Mediante esta disposición del try-catch garantizo cierre de recursos de forma automática.
	    try {
	         Statement st = cx.createStatement();
	         ResultSet rs = st.executeQuery(sql);

	        if (rs.next()) {
	            // Si no hay registros, getInt devuelve 0 por defecto
	            return rs.getInt("ultimo");
	        } 
	    } catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.desconectar();
		}
	    
		return 0;
	}
	
	
    /**
     * Inserta un nuevo producto en la base de datos.
     * @param nombre      Nombre del producto (no nulo ni vacío)
     * @param descripcion Descripción del producto
     * @param precio      Precio ≥ 0
     * @param stock       Stock ≥ 0
     * @return número de filas afectadas (1 si se insertó)
     * @throws SQLException si ocurre un error de acceso a datos
     */
    public int grabar(Producto p) {
    	
        if (p.getNombre() == null || p.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (p.getPrecio() < 0 || p.getEstock() < 0) {
            throw new IllegalArgumentException("Precio y stock deben ser ≥ 0");
        }
        
        String sql = "INSERT INTO PRODUCTO (nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?)";

		Conexion con = new Conexion();
		Connection cx = con.conectar();
		
        try (
        		
            PreparedStatement ps = cx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getEstock());

            int filas = ps.executeUpdate();

            // Opcional: recuperar el ID generado
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int nuevoId = keys.getInt(1);
                    return nuevoId;
                }
            }

            return filas;
            
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.desconectar();
		}
        
		return 0;
    }

    /**
     * Actualiza los datos de un producto existente.
     * @param id          Identificador del producto (debe existir)
     * @param nombre      Nombre nuevo (no nulo ni vacío)
     * @param descripcion Descripción nueva
     * @param precio      Precio ≥ 0
     * @param stock       Stock ≥ 0
     * @return número de filas afectadas (1 si se actualizó)
     * @throws SQLException si ocurre un error de acceso a datos
     */
    public int actualizar(Producto p) {
        if (p.getIdProducto() <= 0) {
            throw new IllegalArgumentException("ID de producto inválido");
        }
        if (p.getNombre() == null || p.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (p.getEstock() < 0 || p.getEstock() < 0) {
            throw new IllegalArgumentException("Precio y stock deben ser ≥ 0");
        }

        String sql = "UPDATE PRODUCTO "
                   + "SET nombre = ?, descripcion = ?, precio = ?, stock = ? "
                   + "WHERE id_producto = ?";

		Conexion con = new Conexion();
		Connection cx = con.conectar();
		
        try (
        	
            PreparedStatement ps = cx.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getEstock());
            ps.setInt(5, p.getIdProducto());

            return ps.executeUpdate();
            
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.desconectar();
		}
        
		return 0;
    }

    /**
     * Elimina un producto de la base de datos (DELETE físico).
     * @param id Identificador del producto a eliminar
     * @return número de filas afectadas (1 si se eliminó)
     * @throws SQLException si ocurre un error de acceso a datos
     */
    public int eliminar(Producto producto) {
    	
        if (producto == null) {
            throw new IllegalArgumentException("Producto no puede ser null");
        }
        
        int id = producto.getIdProducto();
        if (id <= 0) {
            throw new IllegalArgumentException("ID de producto inválido");
        }

        String sql = "DELETE FROM PRODUCTO WHERE id_producto = ?";

		Conexion con = new Conexion();
		Connection cx = con.conectar();
		
        try {
            PreparedStatement ps = cx.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate();
            
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.desconectar();
		}
        
		return 0;
    }
    
    /**
     * Busca un producto por su ID en una lista.
     *
     * @param productos Lista de productos disponible
     * @param id        ID del producto a buscar
     * @return Producto encontrado o null si no existe
     */
    public Producto findById(List<Producto> productos, int id) {
        for (Producto p : productos) {
            if (p.getIdProducto() == id) {
                return p;
            }
        }
        return null;
    }
    
    /**
     * Busca un producto por nombre de producto.
     * @param nombreParcial Identificador del producto en formato String.
     * @return un producto.
     * @throws SQLException si ocurre un error de acceso a datos
     */    
	public List<Producto> findByNombre(String nombreParcial) {
		
		List<Producto> list = new ArrayList<>();

		String query = "SELECT * FROM PRODUCTO WHERE nombre LIKE ?";
		
		Conexion con = new Conexion();
		Connection cx = con.conectar();

		try {
			PreparedStatement ps = cx.prepareStatement(query);

			ps.setString(1, nombreParcial);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				list.add(
						new Producto(
							rs.getInt("id_producto"), 
							rs.getString("nombre"), 
							rs.getString("descripcion"), 
							rs.getDouble("precio"), 
							rs.getInt("stock"))
						);				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.desconectar();
		}
		return list;
	}	
	
}
