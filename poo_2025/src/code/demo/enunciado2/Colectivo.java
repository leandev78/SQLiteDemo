package code.demo.enunciado2;

public class Colectivo implements Transporte {

	private double tarifa = 386;
	
	Colectivo(){}

	@Override
	public void calcularTarifa() {
		
		double resultado = this.tarifa + Transporte.TARIFAMINIMA;
		
		System.out.println("Tarifa del Colectivo: " + resultado );
		
	}
	
	
	
}
