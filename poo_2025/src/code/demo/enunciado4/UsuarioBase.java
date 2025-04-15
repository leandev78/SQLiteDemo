package code.demo.enunciado4;


public abstract class UsuarioBase implements Usuario {
	
    protected String nombreUsuario;
    protected static int totalSesionesActivas = 0;
    public static final String NOMBRE_SISTEMA = "MiAppWeb";

    public UsuarioBase(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public void login() {
        System.out.println(nombreUsuario + " inició sesión en " + NOMBRE_SISTEMA);
        totalSesionesActivas++;
    }

    public abstract String rol();
	
	
}
