package sqlite.code.model;

//CLASE ABSTRACTA
public abstract class Persona implements Identificable {
	
 protected int id;	
 protected String nombre;
 protected String dni;
 
 public static final String NACIONALIDAD = "Argentina";
 public static int totalPersonas = 0;

 public Persona(int id, String nombre, String dni) {
	 this.id = id;
     this.nombre = nombre;
     this.dni = dni;
     this.totalPersonas++;
 }

 public abstract void mostrarInformacion();
 
 
 //Nota:
 // Si la interfaz "Identificable" es implementada en la clase "Persona", ¿porque no estamos implementando su metodo "obtenerDocumento()" aquí?
 // Cuando una clase abstracta implementa una interfaz, tiene dos opciones:
 // 1. Implementar los métodos de la interfaz directamente en la clase abstracta.
 // 2. Dejar que las subclases hijas concreten esos métodos.
 
 
}
