package com.miempresa.tiendaonline.model;

import java.util.ArrayList;
import java.util.List;

public class Factura implements IComprable {

	private int idFactura;
	private Cliente cliente;
	private List<DetalleFactura> detalles;

	public Factura(Cliente cliente) {
		this.cliente = cliente;
		this.detalles = new ArrayList<>();
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<DetalleFactura> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleFactura> detalles) {
		this.detalles = detalles;
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
		System.out.println("Factura para: " + cliente.getNombre() + " - Direccion: " + cliente.getDireccion()
				+ " - Telefono: " + cliente.getTelefono());
		for (DetalleFactura d : detalles) {
			System.out.println(d.getDetalle());
		}
		System.out.println("TOTAL: $" + calcularTotal());
	}

}
