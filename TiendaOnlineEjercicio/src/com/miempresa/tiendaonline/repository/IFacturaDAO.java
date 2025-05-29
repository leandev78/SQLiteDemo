package com.miempresa.tiendaonline.repository;

import java.sql.SQLException;

import com.miempresa.tiendaonline.model.Factura;

public interface IFacturaDAO {
	
	void guardar(Factura factura) throws SQLException;
	
}
