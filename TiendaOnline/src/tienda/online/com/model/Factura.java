package tienda.online.com.model;

import java.util.ArrayList;
import java.util.List;

public class Factura implements Comprable {

    private Cliente cliente;
    private List<DetalleFactura> detalles;

    public Factura(Cliente cliente) {
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
    }

    public void agregarDetalle(DetalleFactura detalle) {
        detalles.add(detalle);
    }

    @Override
    public double calcularTotal() {
        double total = 0;
        for (DetalleFactura d : detalles) {
            total += d.calcularSubtotal();
        }
        return total;
    }

    public void imprimirFactura() {
        System.out.println("Factura para: " + cliente.getNombre() + " - Direccion: " + cliente.getDireccion() + " - Telefono: " + cliente.getTelefono());
        for (DetalleFactura d : detalles) {
            System.out.println(d.getDetalle());
        }
        System.out.println("TOTAL: $" + calcularTotal());
    }
		
		
}
