package code.demo.tp2;

public abstract class Viaje implements Transportes {

	protected static final double TARIFA_MIN = 500;
	protected static int totalViajes = 0; 
	
	protected String origen;
	protected String destino;
 
	
	Viaje(String o, String d){
		this.origen = o;
		this.destino = d;
	}
	
	
	abstract void calcularTarifa();
	
}
