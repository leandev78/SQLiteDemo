package tienda.online.com;

import java.sql.*;
import java.util.*;

import tienda.online.com.connection.Conexion;
import tienda.online.com.model.Cliente;
import tienda.online.com.model.DetalleFactura;
import tienda.online.com.model.Factura;
import tienda.online.com.model.Producto;
import tienda.online.com.model.Carrito;

public class TiendaOnlineSimple {

    public static void main(String[] args) {

        // Crear cliente
        Cliente cliente = new Cliente();
        cliente = cliente.iniciarSesion("juan.perez@mail.com", "12345678");

        if (cliente != null) {
        	
	        // Crear productos
	        Producto p1 = new Producto(1, "Notebook", "32 gb de memoria", 450000, 5);
	        Producto p2 = new Producto(2, "Mouse", "32 gb de memoria", 500, 25);
	
	        // Crear factura
	        Factura factura = new Factura(cliente);
	
	        // Agregar detalles
	        factura.agregarDetalle(new DetalleFactura(p1, 1));
	        factura.agregarDetalle(new DetalleFactura(p2, 3));
	
	        // Imprimir factura
	        factura.imprimirFactura();    
	        
        }
    	
    }

    
}


