import java.util.InputMismatchException;
import java.util.Scanner;

public class Array {
	
	private int[] arrayUsuario, arrayAleatorio;
	
	public Array() {
		arrayUsuario = new int[10]; //Cambiar para hacer IndexOutBounds
		arrayAleatorio = new int[10];
		leerDatos();
	}
	
	public void leerDatos() throws IndexOutOfBoundsException{ //Ejemplo capturando las excepciones en el metodo y alnzando
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Te voy a pedir que introduzcas 10 numeros de uno en uno\n");
		for(int i = 0; i < 10; i++) {
			try {
				System.out.println("Introduce un numero en la pos " + i + ": ");
				arrayUsuario[i] = sc.nextInt();
				arrayAleatorio[i] = (int) (Math.random() * 10);
			} catch (InputMismatchException e) {
				System.out.println("No has introducido un numero\n");
				sc.nextLine();
				i--;
			} 
		}
	}
	
	public void dividir() throws ArithmeticException{ //Ejemplo lanzando la excepcion ahcia arriba
		for(int i = 0; i < arrayUsuario.length;i++) {
			arrayUsuario[i] = arrayUsuario[i] / arrayAleatorio[i];
		}
	}
	
	public void mostrarDatos() {
		System.out.print("\nLos datos son: [ ");
		for(int i = 0; i < arrayUsuario.length - 1; i++) {
			System.out.print(arrayUsuario[i] + " ");
		}
		System.out.println(arrayUsuario[arrayUsuario.length - 1] + " ]");
	}

	public int[] getArrayUsuario() {
		return arrayUsuario;
	}

	public void setArrayUsuario(int[] arrayUsuario) {
		this.arrayUsuario = arrayUsuario;
	}

	public int[] getArrayAleatorio() {
		return arrayAleatorio;
	}

	public void setArrayAleatorio(int[] arrayAleatorio) {
		this.arrayAleatorio = arrayAleatorio;
	}
	
}
