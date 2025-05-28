package tienda.online.com.repository;

import java.sql.SQLException;
import java.util.List;

import tienda.online.com.model.Producto;

public interface IProductoDAO {
	  int grabar(Producto p) throws SQLException;
	  int actualizar(Producto p) throws SQLException;
	  int eliminar(Producto p) throws SQLException;
	  List<Producto> listarTodos() throws SQLException;
}
