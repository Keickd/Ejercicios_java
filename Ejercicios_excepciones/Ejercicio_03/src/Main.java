
/*
 * 3. Escribir un programa java, capaz de dividir un array de 10 elementos que se introducen 
desde teclado, entre 10 números aleatorios. Deberemos controlar las siguientes excepciones: 
a. Array más allá de su rango. 
b. Imposibilidad de introducir caracteres no numéricos. 
c. Posible división entre valor 0.
 */

public class Main {

	public static void main(String[] args) {

		try {
			Array array = new Array();
			array.dividir();
			array.mostrarDatos();
		} catch (ArithmeticException e) {
			System.out.println("Division by zero");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Indice fuera de limite");
		} finally {
			System.out.println("Fin de programa");
		}
		
	}

}
