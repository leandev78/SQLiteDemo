package code.demo.enunciado2;

import java.util.ArrayList;
import java.util.List;

import code.demo.enunciado1.Empleado;

/**
Enunciado 2: Sistema de Transporte Público
Se quiere modelar un sistema de transporte que incluya colectivos, trenes y subtes. 
Todos los medios de transporte deben implementar una interfaz Transporte que contenga los métodos 
	iniciarViaje(), detenerViaje(), y calcularTarifa().
Se solicita:
•	Implementar la interfaz Transporte en clases concretas (Colectivo, Tren, Subte).
•	Crear un sistema que use polimorfismo para llamar a calcularTarifa() sin importar el tipo de transporte.
•	Usar una constante static final para establecer un valor mínimo de tarifa permitido.
•	Incluir una variable static que indique el total de viajes realizados desde cualquier transporte (Ojo!!!) 
 */

public class principal {

	public static void main(String[] args) {
		
		List<Viajes> viajes = new ArrayList<>();
	
		
		Colectivo colectivo = new Colectivo();			
		viajes.add(colectivo);
		
		Tren tren = new Tren();
		viajes.add(tren);
		
		Subte subte = new Subte();		
		viajes.add(subte);
		
		
		for (Viajes t: viajes){
			t.mostrarViajes();		
		}			
				
	}

}
