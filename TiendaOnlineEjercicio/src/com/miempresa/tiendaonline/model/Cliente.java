package com.miempresa.tiendaonline.model;



public class Cliente extends Usuario {

    private String direccion;
    private String telefono;
    private String fechaRegistro;
   
    public Cliente() {}  // ¿Que pasaria si en Usuario no existiera un constructor vacio?
    
	public Cliente(int id, String nombre, String apellido, String email, String password, String direccion, String telefono, String fechaRegistro){
		super(id,nombre,apellido,email,password);
		this.direccion = direccion;
		this.telefono = telefono;
		this.fechaRegistro = fechaRegistro;
	}

	public int getIdCliente() {
		return this.id;
	}
	
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	@Override
	public String mostrarDatosPersonales() {
		return this.id + " | " 	+ this.nombre + " | " + this.apellido + " | " + this.direccion + " | "	+ this.email;
	}
	
	
	
}
