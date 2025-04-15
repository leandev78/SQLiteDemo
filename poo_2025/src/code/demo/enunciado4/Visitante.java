package code.demo.enunciado4;

public class Visitante extends UsuarioBase {
	
    public Visitante(String nombreUsuario) {
        super(nombreUsuario);
    }

    @Override
    public void verContenido() {
        System.out.println(nombreUsuario + " está viendo contenido limitado (como Visitante).");
    }

    @Override
    public void gestionarContenido() {
        System.out.println(nombreUsuario + " NO tiene permisos para gestionar contenido.");
        System.out.println("Acciones permitidas: " + this.rol());
    }

    @Override
    public String rol() {
    	return "[Ver]";
    }
}

