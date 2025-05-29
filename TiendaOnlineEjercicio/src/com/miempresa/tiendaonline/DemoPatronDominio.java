package com.miempresa.tiendaonline;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.miempresa.tiendaonline.model.*;
import com.miempresa.tiendaonline.repository.*;

public class DemoPatronDominio {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteDAO clienteService = new ClienteDAO();
        ProductoDAO productoService = new ProductoDAO();

        String email = "";
        String password = "";
        boolean validado = false;

        // Obtener la fecha actual formateada
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // === VALIDAR CREDENCIALES ===
        while (!validado) {
            System.out.print("\nIngrese e-mail: ");
            email = scanner.nextLine();

            System.out.print("Ingrese contraseña: ");
            password = scanner.nextLine();

            validado = clienteService.validarCredenciales(email, password);

            if (!validado) {
                System.out.println("❌ Usuario o contraseña inválidos. Intente nuevamente.");
            }
        }

        System.out.println("* Credenciales válidas. Iniciando sesión...\n");

        // === INICIAR SESIÓN ===
        Cliente cliente = clienteService.iniciarSesion(email, password);

        if (cliente == null) {
            System.out.println("* Error: No se pudo iniciar sesión.");
            return;
        }

        banner("Bienvenido/a: " + cliente.getNombre() + " " + cliente.getApellido());

        // === MOSTRAR PRODUCTOS DISPONIBLES ===
        List<Producto> productos = productoService.leer();

        banner("Lista de productos disponibles");
        for (Producto p : productos) {
            System.out.println(p);
        }

        // === AGREGAR PRODUCTOS AL CARRITO ===
        List<Carrito> carrito = new ArrayList<>();
        String continuar = "s";

        while (continuar.equalsIgnoreCase("s")) {
            System.out.print("\nIngrese el código del producto: ");
            int codigo = scanner.nextInt();

            Producto producto = productoService.findById(productos, codigo);

            if (producto != null) {
                System.out.print("Ingrese la cantidad: ");
                int cantidad = scanner.nextInt();

                if (cantidad > 0) {
                    carrito.add(new Carrito(producto, cantidad, fecha));
                    System.out.println("* Producto agregado al carrito.");
                } else {
                    System.out.println("* La cantidad debe ser mayor a cero.");
                }
            } else {
                System.out.println("* Producto no encontrado.");
            }

            System.out.print("\n¿Desea agregar otro producto? (s/n): ");
            continuar = scanner.next();
        }

        // === MOSTRAR RESUMEN DE CARRITO ===
        banner("Resumen del carrito (" + carrito.size() + " ítems)");
        for (Carrito item : carrito) {
            System.out.println(" - " + item.getProducto());
        }

        // === GENERAR FACTURA ===
        banner("Generar Factura");

        Factura factura = new Factura(cliente);

        for (Carrito item : carrito) {
            factura.agregarDetalle(new DetalleFactura(item.getProducto(), item.getCantidad()));
        }

        factura.imprimirFactura();

        
        // === PERSISTENCIA DE FACTURA ===

        String opcion = "";
        boolean respuestaValida = false;

        while (!respuestaValida) {
            System.out.print("\n¿Desea grabar la factura? (s/n): ");
            opcion = scanner.next().trim().toLowerCase();

            if (opcion.equals("s")) {
                
            	FacturaDAO facturaService = new FacturaDAO();
            	
            	facturaService.guardar(factura);    //<-- realizar la presistencia !!!       
   
            	
//            	¿Qué debe hacer factura.guardar()?
//            	1) Insertar una fila en la tabla FACTURA (con cliente, fecha, total...).
//
//            	2) Obtener el id_factura generado por la base.
//
//            	3) Recorrer detalles y hacer un INSERT en DETALLE_FACTURA usando ese id_factura.            	
//            	

            	
                System.out.println("Factura grabada correctamente.");
                respuestaValida = true;
            } else if (opcion.equals("n")) {
                System.out.println("Operación cancelada por el usuario.");
                respuestaValida = true;
            } else {
                System.out.println("Opción inválida. Por favor, ingrese 's' o 'n'.");
            }
        }
        

        scanner.close();
    }

    
    
    
    /** Muestra un título formateado */
    private static void banner(String title) {
        System.out.println("\n=== " + title + " ===\n");
    }
}
