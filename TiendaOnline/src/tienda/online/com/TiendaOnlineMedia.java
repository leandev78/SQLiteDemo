package tienda.online.com;

import java.sql.*;
import java.util.*;

import tienda.online.com.connection.Conexion;
import tienda.online.com.model.Cliente;
import tienda.online.com.model.DetalleFactura;
import tienda.online.com.model.Factura;
import tienda.online.com.model.Producto;
import tienda.online.com.model.Carrito;

/**
 * Clase principal del sistema de Tienda Online.
 * Permite obtener y mostrar productos, iniciar sesión de un cliente,
 * y simular un carrito de compras seleccionando productos.
 */
public class TiendaOnlineMedia {

    public static void main(String[] args) {

        // Mostrar listado de productos
        System.out.println("\n---------------- LISTA DE PRODUCTOS ----------------\n");
        List<Producto> productos = obtenerProductos();
        productos.forEach(Producto::mostrarProducto);

        // Proceso de login
        System.out.println("\n---------------- LOGIN CLIENTE ----------------\n");
        Cliente cliente = new Cliente();
        cliente = cliente.iniciarSesion("juan.perez@mail.com", "12345678");

        // Si el login fue exitoso
        if (cliente != null) {
            System.out.println("\n---------------- Mi CARRITO DE COMPRAS -------------\n");
            List<Carrito> carrito = iniciarCarrito(productos);

            // Mostrar resumen del carrito
            System.out.println("\n# Tienes " + carrito.size() + " producto(s) en tu carrito de compras:");
            //carrito.forEach(Producto::mostrarProducto);
            for (Carrito c: carrito) {
            	c.getProducto().mostrarProducto();
            }
            
            
            System.out.println("\n---------------- IMPRIMIR FACTURA -------------\n");
            // Crear factura
            Factura factura = new Factura(cliente);
            
            // Agregar detalles
            for (Carrito c: carrito) {
            	factura.agregarDetalle(new DetalleFactura(c.getProducto(), c.getCantidad()));             	
            }            
            
            factura.imprimirFactura();
            
        }
              
        
    }

    
    
    /**
     * Obtiene todos los productos de la base de datos.
     *
     * @return Lista de productos cargados desde la base de datos.
     */
    public static List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        Conexion con = new Conexion();
        Connection cx = con.conectar();

        if (cx != null) {
            String query = "SELECT * FROM producto";

            try (Statement st = cx.createStatement();
                 ResultSet rs = st.executeQuery(query)) {

                while (rs.next()) {
                    Producto producto = new Producto(
                            rs.getInt("id_producto"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getDouble("precio"),
                            rs.getInt("stock")
                    );
                    productos.add(producto);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                con.desconectar();
            }
        } else {
            System.out.println("No se pudo establecer conexión con la base de datos.");
        }

        return productos;
    }

    /**
     * Simula la selección de productos para un carrito de compras.
     *
     * @param productos Lista de productos disponible
     * @return Lista de productos seleccionados (carrito)
     */
    public static List<Carrito> iniciarCarrito(List<Producto> productos) {
        List<Carrito> itemCarrito = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String continuar = "s";

        while (continuar.equalsIgnoreCase("s")) {
            System.out.print("\nIngrese el código del producto que desea agregar: ");
            int codigo = scanner.nextInt();

            Producto productoSeleccionado = buscarProductoPorId(productos, codigo);

            if (productoSeleccionado != null) {
                System.out.print("Ingrese la cantidad: ");
                int cantidad = scanner.nextInt();

                // Validamos cantidad positiva
                if (cantidad > 0) {
                    itemCarrito.add(new Carrito(productoSeleccionado, cantidad, "02-05-2025"));
                    System.out.println("Producto agregado al carrito.");
                } else {
                    System.out.println("Cantidad inválida. Debe ser mayor que cero.");
                }

            } else {
                System.out.println("No se encontró ningún producto con ese código.");
            }

            System.out.print("\n¿Desea agregar otro producto? (s/n): ");
            continuar = scanner.next();
        }

        scanner.close();
        return itemCarrito;
    }


    /**
     * Busca un producto por su ID en una lista.
     *
     * @param productos Lista de productos disponible
     * @param id        ID del producto a buscar
     * @return Producto encontrado o null si no existe
     */
    public static Producto buscarProductoPorId(List<Producto> productos, int id) {
        for (Producto p : productos) {
            if (p.getIdProducto() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * Obtiene todos los clientes desde la base de datos.
     *
     * @return Lista de clientes
     */
    public static List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        Conexion con = new Conexion();
        Connection cx = con.conectar();

        if (cx != null) {
            String query = "SELECT * FROM cliente";

            try (Statement st = cx.createStatement();
                 ResultSet rs = st.executeQuery(query)) {

                while (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("email"),
                            "",
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("fecha_registro")
                    );
                    clientes.add(cliente);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                con.desconectar();
            }
        } else {
            System.out.println("No se pudo establecer conexión con la base de datos.");
        }

        return clientes;
    }
}

