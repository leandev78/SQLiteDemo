package code.demo.enunciado3;

import java.util.ArrayList;
import java.util.List;

//Se requiere un sistema para representar figuras geométricas como círculos, cuadrados y triángulos. 
//Todas las figuras deben ser capaces de calcular su área y perímetro.
//Se solicita:
//•	Definir una clase abstracta Figura con métodos abstractos calcularArea() y calcularPerimetro().
//•	Implementar clases concretas como Circulo, Cuadrado y Triangulo.
//•	Usar polimorfismo para que una lista de figuras pueda recorrerse y llamar a los métodos de cálculo sin saber el tipo concreto.
//•	Definir una constante static final para el valor de π en la clase Circulo.
//•	Llevar un conteo total de figuras creadas usando una variable static.




public class Principal {

	public static void main(String[] args) {

		List<Figura> figuras = new ArrayList<>();
		
		Circulo ci = new Circulo();
		figuras.add(ci);
		Cuadrado cu = new Cuadrado();
		figuras.add(cu);
		Triangulo ti = new Triangulo();
		figuras.add(ti);
		
		
		for (Figura f: figuras) {
			System.out.println("# Detalle de Figura...");
			f.calcularArea();
			f.calcularPerimetro();
		}
		
		System.out.println("Total de figuras creadas: " + Figura.contfig);

	}

}
