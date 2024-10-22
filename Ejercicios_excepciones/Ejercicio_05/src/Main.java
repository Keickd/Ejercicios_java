import java.util.InputMismatchException;
import java.util.Scanner;


/*
 * 5. Escribir el código de un programa en el cual se defina un bloque try y dos manejadores catch, de 
forma que cuando  se genere la excepción que es capturada por el catch descrito, pero que 
posteriormente a mostrarnos la excepción nos vuelva al código hasta que se introduzcan los 
datos sin errores. 
Escribir un programa en el que se genere la excepción del ejercicio anterior y se capture. 
 */

public class Main {

	public static void main(String[] args) {
		
		boolean error = false;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Te voy a decir que introduzcas dos numeros para dividir\n");
		do {
			try {
				System.out.println("Introduce un numero entero");
				int num1 = sc.nextInt();
				System.out.println("Introduce el segundo numero");
				int num2 = sc.nextInt();
				System.out.println("La division de " + num1 + " / " + num2 + " = "+ num1 / num2);
			} catch (InputMismatchException e) {
				System.out.println("No has introducido un numero\n");
				error = true;
				sc.nextLine();
			} catch (ArithmeticException e) {
				System.out.println("Division entre 0, no valido");
				error = true;
				sc.nextLine();
			}
			//error = false;
		}while(error);
	}

}
