package com.miempresa.tiendaonline.model;

public class Producto {

	private int idProducto;	
    private String nombre;
    private String descripcion;
    private double precio;
    private int estock;


	public Producto(int id_producto, String nombre, String descripcion, double precio, int estock) {
		super();
		this.idProducto = id_producto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.estock = estock;
	}
	
	public int getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public void setId_producto(int id_producto) {
		this.idProducto = id_producto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getEstock() {
		return estock;
	}

	public void setEstock(int estock) {
		this.estock = estock;
	}

	public String toString() {
		return this.idProducto + " | " 
					+ this.nombre + " | " 
					+ this.descripcion + " | " 
					+ this.precio + " | " 
					+ this.estock;
	}

    
}
