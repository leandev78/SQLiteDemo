package tienda.online.com;

import java.sql.*;
import java.util.*;

import tienda.online.com.connection.Conexion;
import tienda.online.com.model.Cliente;
import tienda.online.com.model.DetalleFactura;
import tienda.online.com.model.Factura;
import tienda.online.com.model.Producto;
import tienda.online.com.model.Carrito;

public class TiendaOnline {

    public static void main(String[] args) {

        mostrarTitulo("LISTA DE PRODUCTOS");

        List<Producto> productos = obtenerProductos();

        mostrarEncabezadoProductos();
        productos.forEach(Producto::mostrarProducto);

        mostrarTitulo("LOGIN CLIENTE");

        Cliente cliente = new Cliente();
        cliente = cliente.iniciarSesion("juan.perez@mail.com", "12345678");

        if (cliente != null) {
            mostrarTitulo("MI CARRITO DE COMPRAS");
            List<Carrito> carrito = iniciarCarrito(productos);

            mostrarTitulo("RESUMEN DE TU CARRITO");

            if (carrito.size() > 0) {
                mostrarEncabezadoCarrito();
                for (Carrito c : carrito) {
                    mostrarDetalleCarrito(c);
                }
                System.out.println("\n# Total de productos: " + carrito.size());

                mostrarTitulo("IMPRIMIR FACTURA");

                Factura factura = new Factura(cliente);
                for (Carrito c : carrito) {
                    factura.agregarDetalle(new DetalleFactura(c.getProducto(), c.getCantidad()));
                }
                factura.imprimirFactura();

                System.out.println("\n¡Gracias por tu compra, " + cliente.getNombre() + "!");
                System.out.println("Factura generada con éxito.");
            } else {
                System.out.println("(!) No se seleccionó ningún producto.");
            }
        } else {
            System.out.println("(!) Falló el inicio de sesión.");
        }
    }

    public static void mostrarTitulo(String titulo) {
        System.out.println("\n================ " + titulo + " ================\n");
    }

    public static void mostrarEncabezadoProductos() {
        System.out.printf("%-5s | %-20s | %-30s | %-8s | %-6s%n",
                          "ID", "Nombre", "Descripción", "Precio", "Stock");
        System.out.println("----------------------------------------------------------------------");
    }

    public static void mostrarEncabezadoCarrito() {
        System.out.printf("%-5s | %-20s | %-8s | %-9s | %-6s%n",
                          "Cant", "Producto", "Precio", "Subtotal", "Stock");
        System.out.println("---------------------------------------------------------------");
    }

    public static void mostrarDetalleCarrito(Carrito c) {
        Producto p = c.getProducto();
        double subtotal = c.getCantidad() * p.getPrecio();
        System.out.printf("%-5d | %-20s | $%-7.2f | $%-8.2f | %-6d%n",
                          c.getCantidad(), p.getNombre(), p.getPrecio(), subtotal, p.getEstock());
    }

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
            System.out.println("(!) No se pudo establecer conexión con la base de datos.");
        }

        return productos;
    }

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

                if (cantidad > 0) {
                    itemCarrito.add(new Carrito(productoSeleccionado, cantidad, "02-05-2025"));
                    System.out.println("Producto agregado al carrito.\n");
                } else {
                    System.out.println("(!) Cantidad inválida. Debe ser mayor que cero.\n");
                }

            } else {
                System.out.println("(!) No se encontró ningún producto con ese código.\n");
            }

            System.out.print("¿Desea agregar otro producto? (s/n): ");
            continuar = scanner.next();
        }

        scanner.close();
        return itemCarrito;
    }

    public static Producto buscarProductoPorId(List<Producto> productos, int id) {
        for (Producto p : productos) {
            if (p.getIdProducto() == id) {
                return p;
            }
        }
        return null;
    }

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
            System.out.println("(!) No se pudo establecer conexión con la base de datos.");
        }

        return clientes;
    }
}


