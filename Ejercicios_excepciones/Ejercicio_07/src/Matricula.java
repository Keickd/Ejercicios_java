import java.util.InputMismatchException;
import java.util.Scanner;

public class Matricula {

	private int numero;
	private char letra;
	private boolean error;
	
	public Matricula() {
		error = false;
		introducirDatos();
		mostrarMatricula();
	}
	
	public void introducirDatos() {
		System.out.println("Introduce una matricula con formato XXXXLetra, donde X es un numero \n");
		Scanner sc = new Scanner(System.in);
		do {
			try {
				System.out.println("Introduce el numero");
				numero = sc.nextInt();
				sc.nextLine();
				System.out.println("Introudce la letra");
				letra = sc.next().charAt(0);
				
				if(numero / 1000 < 1)
					throw new ErrorEnLongitudExcepcion();
				
				if(letra != 'a' && letra != 'b') 
					throw new MatriculaNoValidaExcepcion();
				
				error = false;
			} catch (InputMismatchException e) {
				System.out.println("Tipo de dato incorrecto");
				error = true;
				sc.nextLine();
			} catch (ErrorEnLongitudExcepcion e) {
				System.out.println("Error en la longitud de los números");
				error = true;
				sc.nextLine();
			} catch (MatriculaNoValidaExcepcion e) {
				System.out.println("La matricula no es válida");
				error = true;
				sc.nextLine();
			} 
			catch (Exception e) {
				System.out.println("error");
				error = true;
				sc.nextLine();
			}
		}while(error);	
	}
	
	public void mostrarMatricula() {
		System.out.println("La matricula es " + numero + letra);
	}
	
	

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public char getLetra() {
		return letra;
	}

	public void setLetra(char letra) {
		this.letra = letra;
	}
	
	
}
