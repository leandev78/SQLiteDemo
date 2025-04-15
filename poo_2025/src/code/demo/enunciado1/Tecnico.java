package code.demo.enunciado1;

public class Tecnico extends Empleado {

	private final double sueldo = 150000;
	
	Tecnico(String dni) {
		super(dni);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void calcularSalario() {

		double sueldo = Empleado.suedoBase + this.sueldo;
		
		System.out.println(". Sueldo Empleado Técnico: " + sueldo + " con DNI: " + this.dni);
		
	}

}
