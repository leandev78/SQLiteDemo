package code.demo.enunciado1;

public class Administrativo extends Empleado {

	private final double sueldo = 200000;
	private final double viaticos = 100000;
	
	Administrativo(String dni) {
		super(dni);
	}

	@Override
	public void calcularSalario() {

		double sueldo = Empleado.suedoBase + this.sueldo + this.viaticos;
		
		System.out.println(". Sueldo Empleado Administrativo: " + sueldo + " con DNI: " + this.dni);
		
	}

}
