package code.demo.enunciado2;

public class Colectivo extends Viajes implements Transporte {

	private double tarifa = 386;
	
	Colectivo(){}

	@Override
	public double calcularTarifa() {
		
		double resultado = this.tarifa + Transporte.TARIFAMINIMA;
		return resultado;		
		
	}

	@Override
	public void mostrarViajes() {
		System.out.println("Tarifa del Colectivo: " + this.calcularTarifa() );
		System.out.println("Leyendo contador desde objeto Colectivo..." + Viajes.viajes + "\n");	
	}
	
	
	
}
