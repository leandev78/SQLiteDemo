package sqlite.code.model;

//CLIENTE
public class Cliente extends Persona {
 private int puntosFidelidad;

 public Cliente(int id, String nombre, String dni, int puntosFidelidad) {
     super(id,nombre, dni);
     this.puntosFidelidad = puntosFidelidad;
 }

 @Override
 public void mostrarInformacion() {
     System.out.println("Cliente: " + nombre + " | DNI: " + dni + " | Puntos: " + puntosFidelidad);
 }

 @Override
 public String obtenerDocumento() {
     return dni;
 }
}
