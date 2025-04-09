package code.demo.enunciado1;

import java.util.ArrayList;
import java.util.List;

/**
Enunciado 1: Sistema de Empleados en una Empresa
Una empresa necesita un sistema para gestionar a sus empleados. 
Existen diferentes tipos de empleados: Administrativos, Técnicos y Gerentes. 
Cada uno calcula su salario de forma distinta.
Se solicita:
•	Crear una clase abstracta Empleado que contenga atributos comunes como nombre, DNI y un método abstracto calcularSalario().
•	Utilizar polimorfismo para que cada subclase (por ejemplo, Tecnico, Administrativo, Gerente) implemente su propio cálculo de salario.
•	Incluir una constante static final que indique el valor del sueldo base común a todos.
•	Utilizar una variable static para llevar la cuenta de cuántos empleados han sido creados. 
*/


public class Principal {

	public static void main(String[] args) {

		List<Empleado> empleados = new ArrayList<>();
		
		empleados.add(new Tecnico("123456879"));
		empleados.add(new Administrativo("28987456"));
		empleados.add(new Gerente("98745651"));
		
		
		System.out.println("Lista de empleados / Sueldos asignados...");
		
		for (Empleado e: empleados) {
			e.calcularSalario();
		}		

	}

}
