package code.demo.tp2;

import java.util.ArrayList;
import java.util.List;

import code.demo.enunciado2.Viajes;

/**
Enunciado 2: Sistema de Transporte Público
Se quiere modelar un sistema de transporte que incluya colectivos, trenes y subtes. 
Todos los medios de transporte deben implementar una interfaz Transporte que contenga los métodos 
	iniciarViaje(), detenerViaje().
Se solicita:
•	Implementar la interfaz Transporte en clases concretas (Colectivo, Tren, Subte).
•	Crear un sistema que use polimorfismo para llamar a calcularTarifa() sin importar el tipo de transporte.
•	Usar una constante static final para establecer un valor mínimo de tarifa permitido.
•	Incluir una variable static que indique el total de viajes realizados desde cualquier transporte. 
•	Incluir un metodo abstracto calcularTarifa() y aplicar polimorfismo.
 */




public class Main {

	public static void main(String[] args) {
		
		
		List<Viaje> viajes = new ArrayList<>();
						
		viajes.add(new Tren("Tigre", "Retiro", 25));
		viajes.add(new Tren("Retito", "Córdoba Capital", 56));
		
		viajes.add(new Subte("Línea A", "San Pedrito","Plaza de Mayo", 10, false));
		viajes.add(new Subte("Línea B", "Juan Manuel de Rosas","Leandro N. Alem", 25, false));
		viajes.add(new Subte("Línea C", "Constitución","Retiro", 12, false));
		
		viajes.add(new Colectivo("Constitución", "Tigre", 9, "60"));
		viajes.add(new Colectivo("La Boca", "Olivos", 9, "152"));
		viajes.add(new Colectivo("Chacarita", "Barrancas de Belgrano", 9, "39"));
		viajes.add(new Colectivo("Constitución", "Vicente López", 9, "59"));
		
		
		for (Viaje t: viajes){
			t.iniciarViaje();
			t.calcularTarifa();
			t.detenerViaje();			
		}

	}

}
