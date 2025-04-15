package code.demo.enunciado4;

import java.util.ArrayList;
import java.util.List;

// Se desarrollará un sistema para administrar distintos tipos de usuarios en una aplicación web: 
// Administrador, Editor y Visitante. Cada tipo de usuario tiene distintos permisos y funcionalidades.
//
// Se solicita desarrollar una solución orientada a objetos con los siguientes requerimientos:
//
// Requerimientos:
//
// 1.Crear una interfaz Usuario que defina los siguientes métodos:
//
// login()
// verContenido()
// gestionarContenido()
//
// 2.Diseñar una clase abstracta UsuarioBase que implemente la interfaz Usuario y provea:
//   a.Una implementación común para el método login() (por ejemplo, validación de credenciales simulada).
//   b.Un atributo nombreUsuario, protegido, y su constructor correspondiente.
//   c.Un método abstracto rol() que deba ser implementado por cada tipo de usuario para indicar las acciones permitidas ("Ver", "Crear", "Editar", "Borrar").
//   d.Una variable estática totalSesionesActivas para llevar el conteo global de usuarios logueados.
//   e.Una constante static final llamada NOMBRE_SISTEMA con el nombre de la aplicación (por ejemplo: "MiAppWeb").
// 3.Crear las clases Administrador, Editor y Visitante que extiendan UsuarioBase y redefinan los métodos verContenido() y gestionarContenido() según sus permisos:
//   a.El Visitante solo puede ver contenido.
//   b.El Editor puede ver y gestionar contenido.
//   c.El Administrador tiene control total.
// 4.Crear una clase principal con una lista de usuarios (List<Usuario>) que simule la 
//     interacción polimórfica de los diferentes tipos de usuario con el sistema.

public class Principal {

	public static void main(String[] args) {
		
		
		List<Usuario> usuarios = new ArrayList<>();
				
		usuarios.add(new Administrador("Lorera"));
		usuarios.add(new Editor("Federico"));
		usuarios.add(new Editor("Micaela"));	
		usuarios.add(new Visitante("Sabrina"));

		
	    // Interacción polimórfica
        for (Usuario u : usuarios) {
            u.login();
            u.verContenido();
            u.gestionarContenido();           
            System.out.println("----");
        }

        // Mostrar total de sesiones activas
        System.out.println("Total de sesiones activas: " + UsuarioBase.totalSesionesActivas + " en " + UsuarioBase.NOMBRE_SISTEMA);
		

	}

}
