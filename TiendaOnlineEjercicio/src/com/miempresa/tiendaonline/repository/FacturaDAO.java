package com.miempresa.tiendaonline.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.miempresa.tiendaonline.connection.Conexion;
import com.miempresa.tiendaonline.model.DetalleFactura;
import com.miempresa.tiendaonline.model.Factura;

public class FacturaDAO {

	public void guardar(Factura factura) {
		
		
	    Conexion conexion = new Conexion();
	    Connection cx = conexion.conectar();

	    try {
	        // 1. Insertar la factura
	        String sqlFactura = "INSERT INTO FACTURA (id_cliente, fecha, total) VALUES (?, CURRENT_DATE, ?)";
	        PreparedStatement psFactura = cx.prepareStatement(sqlFactura, Statement.RETURN_GENERATED_KEYS);
	        psFactura.setInt(1, factura.getCliente().getIdCliente());
	        psFactura.setDouble(2, factura.calcularTotal());
	        psFactura.executeUpdate();

	        // 2. Obtener el ID generado
	        ResultSet rs = psFactura.getGeneratedKeys();
	        if (rs.next()) {
	            factura.setIdFactura(rs.getInt(1));
	        }

	        // 3. Insertar cada detalle
	        String sqlDetalle = "INSERT INTO DETALLE_FACTURA (id_factura, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
	        PreparedStatement psDetalle = cx.prepareStatement(sqlDetalle);

	        for (DetalleFactura d : factura.getDetalles()) {
	            psDetalle.setInt(1, factura.getIdFactura());
	            psDetalle.setInt(2, d.getProducto().getIdProducto());
	            psDetalle.setInt(3, d.getCantidad());
	            psDetalle.setDouble(4, d.getProducto().getPrecio());
	            psDetalle.executeUpdate();
	        }

	        System.out.println("Factura guardada correctamente.");
	    } catch (SQLException e) {
	        System.err.println("Error al guardar la factura.");
	        e.printStackTrace();
	    } finally {
	        conexion.desconectar();
	    }
	}

	
}
