package code.demo.example1;

public abstract class Empleado {

		protected String dni;
		protected String nombre;
		protected String apellido;
		protected static final double SALARIO_BASE = 300000;
		protected static int cantEmpleados=0;
		
		Empleado(String d, String n, String a ){
			this.dni = d;
			this.nombre = n;
			this.apellido = a;
			cantEmpleados++;
		}
		
		abstract double calcularSalario();
		
}
