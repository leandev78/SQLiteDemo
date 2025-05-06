package code.demo.tp2;

public class Tren extends Viaje {

	private double tarifa;
	
	Tren(String o, String d, double t) {
		super(o,d);
		this.tarifa = t;
	}

	@Override
	public void iniciarViaje() {
		System.out.println("(TREN) El vehículo ha iniciado su viaje partiendo de "  + this.origen + " con destino a " + this.destino );
		
	}

	@Override
	public void detenerViaje() {
		System.out.println("(TREN) El vehículo ha finalizado su viaje\n" );		
	}

	@Override
	public void calcularTarifa() {
		double v =  Viaje.TARIFA_MIN + this.tarifa;
		System.out.println("(TREN) La tarifa calculada es : " + v);
	}

}
