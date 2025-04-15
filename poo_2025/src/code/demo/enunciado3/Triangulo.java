package code.demo.enunciado3;

public class Triangulo extends Figura {

	private double base = 2;
	private double altura = 3;
	
	Triangulo(){
		Figura.contfig++;
	}
	
	@Override
	protected void calcularArea() {
		double area = this.base * this.altura;
		System.out.println("El Área del triángulo es: " + area );
		
	}

	@Override
	protected void calcularPerimetro() {
		double perimetro = this.base + this.altura + this.altura ;
		System.out.println("El perimetro del triángulo es: " + perimetro );
		
	}

}
