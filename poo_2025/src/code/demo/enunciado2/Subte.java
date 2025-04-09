package code.demo.enunciado2;

public class Subte implements Transporte {

	private double tarifa = 350;
	
	Subte(){}

	@Override
	public void calcularTarifa() {
		
		double resultado = this.tarifa + Transporte.TARIFAMINIMA;
		
		System.out.println("Tarifa del Subte: " + resultado );
	}
	
	
}
