package tienda.online.com.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tienda.online.com.connection.Conexion;
import tienda.online.com.model.Producto;

public class ProductoDAO implements IProductoDAO {

	/**
	 * Obtiene todos los productos de la tabla producto.
	 */	
	public List<Producto> listarTodos() {

		List<Producto> list = new ArrayList<>();
		
		Conexion con = new Conexion();
		Connection cx = con.conectar();

		try {
			String query = "SELECT * FROM producto";

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
	public static int obtenerMaxIdProducto() throws SQLException {
	    String sql = "SELECT MAX(id_producto) AS ultimo FROM producto";

	    // try-with-resources para garantizar cierre de recursos
	    try (Connection cx = new Conexion().conectar();
	         Statement st = cx.createStatement();
	         ResultSet rs = st.executeQuery(sql)) {

	        if (rs.next()) {
	            // Si no hay registros, getInt devuelve 0 por defecto
	            return rs.getInt("ultimo");
	        } else {
	            return 0;
	        }
	    }
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
    public int grabar(Producto p)
            throws SQLException {
        if (p.getNombre() == null || p.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (p.getPrecio() < 0 || p.getEstock() < 0) {
            throw new IllegalArgumentException("Precio y stock deben ser ≥ 0");
        }

        String sql = "INSERT INTO producto (nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?)";

        try (
        		
        	Connection cx = new Conexion().conectar();
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
        }
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
    public int actualizar(Producto p)
            throws SQLException {
        if (p.getIdProducto() <= 0) {
            throw new IllegalArgumentException("ID de producto inválido");
        }
        if (p.getNombre() == null || p.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (p.getEstock() < 0 || p.getEstock() < 0) {
            throw new IllegalArgumentException("Precio y stock deben ser ≥ 0");
        }

        String sql = "UPDATE producto "
                   + "SET nombre = ?, descripcion = ?, precio = ?, stock = ? "
                   + "WHERE id_producto = ?";

        try (
        	
        	Connection cx = new Conexion().conectar();
            PreparedStatement ps = cx.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getEstock());
            ps.setInt(5, p.getIdProducto());

            return ps.executeUpdate();
        }
    }

    /**
     * Elimina un producto de la base de datos (DELETE físico).
     * @param id Identificador del producto a eliminar
     * @return número de filas afectadas (1 si se eliminó)
     * @throws SQLException si ocurre un error de acceso a datos
     */
    public int eliminar(Producto producto) throws SQLException {
    	
        if (producto == null) {
            throw new IllegalArgumentException("Producto no puede ser null");
        }
        
        int id = producto.getIdProducto();
        if (id <= 0) {
            throw new IllegalArgumentException("ID de producto inválido");
        }

        String sql = "DELETE FROM producto WHERE id_producto = ?";

        try (Connection cx = new Conexion().conectar();
             PreparedStatement ps = cx.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }


}
