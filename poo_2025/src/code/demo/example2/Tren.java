package code.demo.example2;


public class Tren extends Viaje implements Transporte {

	private boolean esHoraPico;
	
	Tren(String tipo, boolean esHoraPico) {
		super(tipo);
		this.esHoraPico = esHoraPico;
	}
	
	public double calcularHorarioPico() {
		return ( this.esHoraPico ? 1500 : 1000 );
	}
	
	public void infoTren() {
		this.iniciarViaje();
		this.detenerViaje();
	}
	
	
	@Override
	public double calcularTarifa() {
		double horapico = this.calcularHorarioPico();
		return Viaje.TARIFA_BASE + horapico;
	}

	@Override
	public void iniciarViaje() {
		System.out.println("El " + this.tipo + " comenzó su viaje...");
		
	}

	@Override
	public void detenerViaje() {
		System.out.println("El " + this.tipo + " finalizo el viaje...");		
	}
	
	
	
	
}
