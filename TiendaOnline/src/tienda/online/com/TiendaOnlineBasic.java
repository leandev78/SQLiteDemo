package tienda.online.com;

import java.sql.SQLException;
import tienda.online.com.repository.ProductoDAO;

public class TiendaOnlineBasic {

	public static void main(String[] args) {

		int resp=0;
		
		ProductoDAO repositorioProducto = new ProductoDAO();
		
		
		// 1) Leer productos
		
		System.out.println("\n# METODO MOSTRAR ***************\n");
		
		repositorioProducto.leerProductos();
		
		
		// 2) Grabar un producto
		
		System.out.println("\n# METODO INSERTAR ***************\n");
		
		try {
			resp = repositorioProducto.grabarProducto("Silla Gamer", "Silla XXX XXXX", 300.999, 2);
			System.out.println("Producto insertado con ID = " + resp);
		
			repositorioProducto.leerProductos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 3) Obtengo el ultimo ID insertado (Opcional)
		int idInserted = 0;
		try {
			idInserted = repositorioProducto.obtenerMaxIdProducto();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 4) Actualizar un producto 
		
		System.out.println("\n# METODO ACTUALIZAR ***************\n");
		
		try {
			resp = repositorioProducto.actualizarProducto(
					idInserted,
			        "Silla Gamer",
			        "Silla Sillon Gamer Pc Escritorio",
			        164.823,
			        5
			    );
		} catch (SQLException e) {
			e.printStackTrace();
		}
        System.out.println("ActualizarProducto: filas afectadas = " + resp);
        repositorioProducto.leerProductos();

        
        // 5) Eliminar un producto.
    	
        System.out.println("\n# METODO BORRAR ***************\n");
        
        try {
        	
			resp = repositorioProducto.eliminarProducto( idInserted );
			System.out.println("EliminarProducto: filas afectadas = " + resp);	 
			
			repositorioProducto.leerProductos();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
               
        
	}
		
}
