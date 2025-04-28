package code.demo.tp1;

public class Administrativo extends Empleado {

	private double sueldo = 560000;
	
	Administrativo(String n, String d){
		super(n,d);
	}

	@Override
	double calcularSalario() {
		return Empleado.SUELDOBASICO + this.sueldo;
	}
	
}
