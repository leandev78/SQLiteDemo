package code.demo.tp1;

public class Gerente extends Empleado{

	private double sueldo = 69000;
	
	Gerente(String n, String d){
		super(n,d);
	}

	@Override
	double calcularSalario() {
		return Empleado.SUELDOBASICO + this.sueldo;
	}	
	
}
