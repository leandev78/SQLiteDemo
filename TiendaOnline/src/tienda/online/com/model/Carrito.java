package tienda.online.com.model;

public class Carrito {

	private Producto producto;
	private int cantidad;
	private String fechaCreacion;
	
	
	public Carrito(Producto producto, int cantidad, String fechaCreacion) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.fechaCreacion = fechaCreacion;
	}
	
	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	

}
