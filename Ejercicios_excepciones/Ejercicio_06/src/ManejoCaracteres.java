import java.util.Scanner;

public class ManejoCaracteres {

	String cadena;
	public ManejoCaracteres() {
		introducirDatos();
	}
	
	public void introducirDatos() {
		System.out.println("Introduce una cadena de caracteres con valores alfabeticos de longitud maxima 30");
		Scanner sc = new Scanner(System.in);
		cadena = sc.nextLine();
		
		try {
			ManejarLongitud manejaLongitud = new ManejarLongitud(cadena);
			ManejarTipoCaracteres manejarTipoCaracteres = new ManejarTipoCaracteres(cadena);
			System.out.println("Todo bien, la cadena es " + cadena);
		} catch (LongitudMayor30Exception e) {
			System.out.println("Error, cadena con longitud mayor que 30");
		} catch (CharNoAlfabeticoException e) {
			System.out.println("Error, contiene caracteres no alfabéticos");
		} catch (Exception e) {
			System.out.println("Error");
		}
	
	}

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}
	
	
}
