package code.demo.example1;

public class Administrativo extends Empleado {
	
	private double sueldo = 250000;
	
	Administrativo(String d, String n, String a){
		super(d,n,a);		
	}

	@Override
	double calcularSalario() {		
		return (this.sueldo + Empleado.SALARIO_BASE);
	}	
	
}
