package code.demo.enunciado2;

public class Subte extends Viajes implements Transporte {

	private double tarifa = 350;
	
	Subte(){}

	@Override
	public double calcularTarifa() {
		
		double resultado = this.tarifa + Transporte.TARIFAMINIMA;
		return resultado;
		
	}
	
	@Override
	public void mostrarViajes() {
		System.out.println("Tarifa del Subte: " + this.calcularTarifa() );
		System.out.println("Leyendo contador desde objeto Subte..." + Viajes.viajes + "\n");	
	}
	
}
