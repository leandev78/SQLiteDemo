package code.demo.enunciado2;

public abstract class Viajes {

	protected static int viajes;
	
	Viajes(){
		viajes++;
	}
	
	public abstract void mostrarViajes();
}
