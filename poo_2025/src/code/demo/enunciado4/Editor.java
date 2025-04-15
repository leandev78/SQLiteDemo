package code.demo.enunciado4;

public class Editor extends UsuarioBase {
	
    public Editor(String nombreUsuario) {
        super(nombreUsuario);
    }

    @Override
    public void verContenido() {
        System.out.println(nombreUsuario + " está viendo el contenido (como Editor).");
    }

    @Override
    public void gestionarContenido() {
        System.out.println(nombreUsuario + " está editando el contenido.");
        System.out.println("Acciones permitidas: " + this.rol());
    }

    @Override
    public String rol() {
    	return "[Ver|Crear|Editar]";
    }
}

