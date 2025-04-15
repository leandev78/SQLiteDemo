package code.demo.enunciado3;

public class Circulo extends Figura {

	private double radio = 6;
	
	Circulo(){
		Figura.contfig++;		
	}
	
	@Override
	protected void calcularArea() {
		double area = Figura.pi * (this.radio + 2);
		System.out.println("El Área del círculo es: " + area );		
		
	}

	@Override
	protected void calcularPerimetro() {
		
		double perimetro = Figura.pi * this.radio * 2;
		System.out.println("El perimetro del círculo es: " + perimetro );
		
	}

}
