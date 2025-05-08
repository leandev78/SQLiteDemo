package tienda.online.com.model;

public abstract class Usuario {
	
    protected int id;
    protected String nombre;
    protected String apellido;
    protected String email;
    protected String password;
		
    public Usuario() {}
    
	public Usuario(int id, String nombre, String apellido, String email, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
	}

	public abstract Cliente  iniciarSesion(String email, String password);
}
