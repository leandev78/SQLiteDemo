package code.demo.enunciado1;

public class Gerente extends Empleado  {

	private final double sueldo = 500000;
	private final double antiguedad = 100000;
	
	Gerente(String dni) {
		super(dni);
	}

	@Override
	public void calcularSalario() {
		
		double sueldo = this.suedoBase + this.sueldo + this.antiguedad;
		
		System.out.println(". Sueldo Empleado Gerente: " + sueldo + " con DNI: " + this.dni);
	}

}
