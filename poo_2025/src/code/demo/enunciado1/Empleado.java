package code.demo.enunciado1;

public abstract class Empleado {

	protected String dni;
	
	protected static final double suedoBase = 300000;
	
	protected static int contEmpleados = 0;
	
	Empleado(String dni){
		this.dni = dni;
	}
	
	public abstract void calcularSalario();
	
}
