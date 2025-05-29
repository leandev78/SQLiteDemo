package com.miempresa.tiendaonline.model;

public class DetalleFactura {

    private Producto producto;
    private int cantidad;

    public DetalleFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double calcularSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    public String getDetalle() {
        return cantidad + " x " + producto.getNombre() + " = $" + calcularSubtotal();
    }
    
}
