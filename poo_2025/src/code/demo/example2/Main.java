package code.demo.example2;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		System.out.println("Codigo ejercicio 2");
		
		List<Viaje> viajes = new ArrayList<>();
				
		viajes.add(new Tren("Tren 1", false));
		viajes.add(new Tren("Tren 2", true));
		viajes.add(new Tren("Tren 3", true));
		viajes.add(new Tren("Tren 4", false));
		
		for(Viaje v: viajes) {			
			v.infoTren();
			System.out.println("Tarifa $: " + v.calcularTarifa());
		}
		
		

	}

}
