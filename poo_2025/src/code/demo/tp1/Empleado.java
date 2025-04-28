package code.demo.tp1;

public abstract class Empleado {

	protected String nombre;
	protected String dni;
	protected static final double SUELDOBASICO = 500000;
	protected static int cantEmpleados=0;
	
	Empleado(String n, String d){
		this.nombre = n;
		this.dni = d;
		this.cantEmpleados++;
	}
	
	abstract double calcularSalario(); 
	
}
