
/*
 * 7. Implementar una clase Matricula de coche que va a estar formada por dos variables: letra y 
número. En el método main debe crear objetos matrícula, para ello se le pedirá al usuario que 
introduzca por teclado la letra y el número. La matrícula se considerará valida si la letra 
introducida es igual a la letra „a‟ o a la letra „b‟ y si el número tiene 4 dígitos. Si la matrícula 
introducida es correcta se creará un objeto matrícula y se mostrarán por pantalla los valores de 
sus variables de ejemplar. En caso de que la letra sea incorrecta se lanzará una excepción que 
muestre un mensaje indicando que el formato es erróneo. En el caso que el número de matrícula 
introducido no tenga cuatro dígitos se lanzará una excepción que indique “Error en la longitud de 
los números”. Para lanzar estas excepciones deberá crear una excepción. 
 */

public class Main {

	public static void main(String[] args) {
		Matricula matricula = new Matricula();
	}

}
