package tienda.online.com.repository;

import java.util.List;

import tienda.online.com.model.Producto;

public class ProductosTest {

	public static void main(String[] args) {
		
		ProductoDAO repositorio = new ProductoDAO();
		
		List<Producto> lp = repositorio.listarTodos();
		
		for(Producto p: lp) {
			System.out.println(p.getIdProducto() + " | " + p.getNombre() );
		}
		

	}

}
