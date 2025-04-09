package sqlite.code.model;

//EMPLEADO
public class Empleado extends Persona {
 private double salario;

 public Empleado(int id, String nombre, String dni, double salario) {
     super(id, nombre, dni);
     this.salario = salario;
 }

 @Override
 public void mostrarInformacion() {
     System.out.println("Empleado: " + nombre + " | DNI: " + dni + " | Salario: $" + salario);
 }

 @Override
 public String obtenerDocumento() {
     return dni;
 }
}
