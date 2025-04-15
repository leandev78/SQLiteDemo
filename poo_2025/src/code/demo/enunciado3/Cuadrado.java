package code.demo.enunciado3;

public class Cuadrado extends Figura {

	private double lado = 3;
	
	Cuadrado(){
		Figura.contfig++;
	}
	
	@Override
	protected void calcularArea() {
		double area = 2 * this.lado;
		System.out.println("El Área del cuadrado es: " + area );		
		
	}

	@Override
	protected void calcularPerimetro() {
		
		double perimetro = 4 * this.lado;
		System.out.println("El perimetro del cuadrado es: " + perimetro );
		
	}

}
