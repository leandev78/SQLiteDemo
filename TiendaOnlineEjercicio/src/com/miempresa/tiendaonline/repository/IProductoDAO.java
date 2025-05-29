package com.miempresa.tiendaonline.repository;

import java.sql.SQLException;
import java.util.List;

import com.miempresa.tiendaonline.model.Producto;


public interface IProductoDAO {
	  int grabar(Producto p) throws SQLException;
	  int actualizar(Producto p) throws SQLException;
	  int eliminar(Producto p) throws SQLException;
	  List<Producto> leer() throws SQLException;
}
