package code.demo.enunciado2;

public class Tren implements Transporte {

	private double tarifa = 120;
	
	Tren(){}

	@Override
	public void calcularTarifa() {
		double resultado = this.tarifa + Transporte.TARIFAMINIMA;
		System.out.println("Tarifa del Tren: " + resultado );
		
	}
	
	
	
}
