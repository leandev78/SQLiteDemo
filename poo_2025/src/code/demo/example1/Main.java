package code.demo.example1;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {		
		
		System.out.println("Codigo ejercicio 1");
		
		List<Empleado> empleados = new ArrayList<>();
		
		empleados.add(new Administrativo("26587489", "Fernando", "Arias"));
		empleados.add(new Administrativo("12345684", "Laura", "Gonzalez"));
		empleados.add(new Administrativo("85214748", "Raúl", "Santarelli"));
		
		for (Empleado e: empleados) {		
			System.out.println("Empleado " + e.apellido + " " + e.nombre + " - DNI: " + e.dni + " Sueldo a pagar: " + e.calcularSalario());
		}
		
		System.out.println("La cantidad de empleados registrados es de: " + Empleado.cantEmpleados);
	}

}
