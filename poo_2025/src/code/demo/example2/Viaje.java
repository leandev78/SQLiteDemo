package code.demo.example2;

public abstract class Viaje {

	protected static final double TARIFA_BASE = 265;	
	protected static int cantViajes = 0;
	protected String tipo;
	
	Viaje(String tipo){
		this.tipo = tipo;
	}
	
	abstract double calcularTarifa();
	abstract void infoTren();
	
}
