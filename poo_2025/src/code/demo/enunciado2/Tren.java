package code.demo.enunciado2;

public class Tren extends Viajes implements Transporte {

	private double tarifa = 120;
	
	Tren(){}

	@Override
	public double calcularTarifa() {
		double resultado = this.tarifa + Transporte.TARIFAMINIMA;
		return resultado;
		
	}
	
	@Override
	public void mostrarViajes() {
		System.out.println("Tarifa del Tren: " + this.calcularTarifa() );
		System.out.println("Leyendo contador desde objeto Tren..." + Viajes.viajes + "\n");	
	}
	
	
}
