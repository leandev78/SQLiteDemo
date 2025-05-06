package code.demo.tp2;

public class Colectivo extends Viaje {
	
	private String linea;
	private double tarifa;
	
	Colectivo(String o, String d, double t, String l) {
		super(o,d);
		this.tarifa = t;
		this.linea = l;
	}

	@Override
	public void iniciarViaje() {
		System.out.println("(COLECTIVO) El vehículo línea [" + this.linea + "] ha iniciado su viaje partiendo de "  + this.origen + " con destino a " + this.destino );
		
	}

	@Override
	public void detenerViaje() {
		System.out.println("(COLECTIVO) El vehículo ha finalizado su viaje\n" );		
	}

	@Override
	public void calcularTarifa() {
		double v =  Viaje.TARIFA_MIN + this.tarifa;
		System.out.println("(COLECTIVO) La tarifa calculada es : " + v);
	}	
	
}
