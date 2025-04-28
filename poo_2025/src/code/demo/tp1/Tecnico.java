package code.demo.tp1;

public class Tecnico extends Empleado {

	private double sueldo = 45000;
	
	Tecnico(String n, String d){
		super(n,d);
	}

	@Override
	double calcularSalario() {
		return Empleado.SUELDOBASICO + this.sueldo;
	}
	
	
	
}
