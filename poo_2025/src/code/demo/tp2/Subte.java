package code.demo.tp2;

public class Subte extends Viaje {

	private String nombre;
	private double tarifa;
	private boolean esHoraPico;
	
	
	Subte(String n, String o, String d, double t, boolean h) {
		super(o,d);
		this.nombre = n;
		this.tarifa = t;
		this.esHoraPico = h;
	}

	@Override
	public void iniciarViaje() {
		System.out.println("(SUBTE) El vehículo ha iniciado su viaje partiendo de "  + this.origen + " con destino a " + this.destino );
		
	}

	@Override
	public void detenerViaje() {
		System.out.println("(SUBTE) El vehículo ha finalizado su viaje\n" );		
	}

	@Override
	public void calcularTarifa() {
		double incremento = this.esHoraPico == true ? 0.87 : 0.1;
		double tarifa =  Viaje.TARIFA_MIN + this.tarifa + incremento;		
		System.out.println("(SUBTE) La tarifa calculada es : " + tarifa);
	}	
	
}
