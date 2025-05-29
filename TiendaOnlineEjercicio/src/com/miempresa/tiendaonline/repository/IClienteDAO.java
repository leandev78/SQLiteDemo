package com.miempresa.tiendaonline.repository;

import java.sql.SQLException;
import java.util.List;
import com.miempresa.tiendaonline.model.Cliente;

public interface IClienteDAO {
	
	  int grabar(Cliente c) throws SQLException;
	  int actualizar(Cliente c) throws SQLException;
	  int eliminar(Cliente c) throws SQLException;
	  List<Cliente> leer() throws SQLException;
}
