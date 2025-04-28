package code.demo.tp1;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		
		List<Empleado> empleados = new ArrayList<>();
		
		empleados.add(new Administrativo("Julian", "1233212321"));
		empleados.add(new Tecnico("Laura", "434545665"));
		empleados.add(new Tecnico("Fernando", "345454345"));
		empleados.add(new Administrativo("Paula", "765546456"));
		empleados.add(new Administrativo("Leandro", "456456456"));
		empleados.add(new Administrativo("Facundo", "67788997"));
		
		for(Empleado e: empleados) {
			System.out.println(" El Salario de " + e.nombre + " con dni: " + e.dni + " es " +   e.calcularSalario() );
		}
		
		

	}

}
