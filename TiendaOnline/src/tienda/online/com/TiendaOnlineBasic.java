package tienda.online.com;

import java.sql.SQLException;
import tienda.online.com.repository.ProductoDAOSimple;

public class TiendaOnlineBasic {

	public static void main(String[] args) {

		int resp=0;
		
		ProductoDAOSimple repositorioProducto = new ProductoDAOSimple();
		
		
		// 1) Leer productos
		
		System.out.println("\n# METODO MOSTRAR ***************\n");
		
		repositorioProducto.listarTodos();
		
		
		// 2) Grabar un producto
		
		System.out.println("\n# METODO INSERTAR ***************\n");
		
		try {
			resp = repositorioProducto.grabar("Silla Gamer", "Silla XXX XXXX", 300.999, 2);
			System.out.println("Producto insertado con ID = " + resp);
		
			repositorioProducto.listarTodos();
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
			resp = repositorioProducto.actualizar(
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
        repositorioProducto.listarTodos();

        
        // 5) Eliminar un producto.
    	
        System.out.println("\n# METODO BORRAR ***************\n");
        
        try {
        	
			resp = repositorioProducto.eliminar( idInserted );
			System.out.println("EliminarProducto: filas afectadas = " + resp);	 
			
			repositorioProducto.listarTodos();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
               
        
	}
		
}
