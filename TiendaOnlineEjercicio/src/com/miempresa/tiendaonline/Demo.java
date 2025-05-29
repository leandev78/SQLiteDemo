package com.miempresa.tiendaonline;

import java.sql.SQLException;
import java.util.List;

import com.miempresa.tiendaonline.model.Producto;
import com.miempresa.tiendaonline.repository.ProductoDAO;

public class Demo {

	public static void main(String[] args) {

		ProductoDAO dao = new ProductoDAO();

		// 1) Leer e imprimir todos los productos
		banner("1) Lectura inicial de productos");
		printAll(dao.leer());

		
		
		
		// 2) Crear un nuevo producto
		banner("2) Insertar un producto");
		Producto nuevo = new Producto(0, "Silla Gamer", "Silla XXX XXXX", 300.999, 2);
		int id = dao.grabar(nuevo);
		System.out.println("-> Insertado con ID = " + id);
		printAll(dao.leer());

		
		
		
		// 3) Obtener último ID (opcional)
		banner("3) Obtener último ID insertado");
		int lastId = 0;
		lastId = dao.obtenerMaxIdProducto();
		System.out.println("-> Último ID = " + lastId);

		
		
		
		// 4) Actualizar ese mismo producto
		banner("4) Actualizar producto");
		nuevo.setIdProducto(lastId);
		nuevo.setDescripcion("Silla con almohadones lumbar y cervical");
		int updated = dao.actualizar(nuevo);
		System.out.println("→ Filas afectadas al actualizar = " + updated);
		printAll(dao.leer());

		
		
		
		// 5) Eliminar el producto
		banner("5) Eliminar producto");

		int deleted = dao.eliminar(nuevo);
		System.out.println("-> Filas afectadas al eliminar = " + deleted);

		printAll(dao.leer());
	}

	/** Imprime un separador con título */
	private static void banner(String title) {
		System.out.println("\n=== " + title + " ===\n");
	}

	/** Imprime por consola todos los productos de la lista */
	private static void printAll(List<Producto> productos) {
		productos.forEach(System.out::println);
	}
}
