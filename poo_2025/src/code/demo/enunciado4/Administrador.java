package code.demo.enunciado4;

// Pregunta interesante... Si extendemos de UsuarioBase la cual esta implementa a la interfaz Usuario, 
// ¿Por qué no tenemos definido en esta clase el metodo login()  ?


public class Administrador extends UsuarioBase {
	
	    public Administrador(String nombreUsuario) {
	        super(nombreUsuario);
	    }

	    @Override
	    public void verContenido() {
	        System.out.println(nombreUsuario + " está viendo todo el contenido (como Administrador).");
	    }

	    @Override
	    public void gestionarContenido() {
	        System.out.println(nombreUsuario + " está gestionando el contenido.");
	        System.out.println("Acciones permitidas: " + this.rol());
	    }

	    @Override
	    public String rol() {
	        return "[Ver|Crear|Editar|Borrar]";
	    }
}
