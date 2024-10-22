
/*
 * 2. Resolver la ecuación de segundo grado ax2 + bx +c = 0. Sabiendo que si a = 0 no se puede 
hacer y si el discriminante es negativo (b2 < 4ac)  tampoco. 
 */

public class Main {
	
	public static void main(String[] args) {
		
		
		try {
			SegundoGrado sg = new SegundoGrado();
			//double[] res = sg.calcularEcuacionSegundoGrado(1, 6, 1); //Bueno
			
			//double[] res = sg.calcularEcuacionSegundoGrado(0, 2, 1); //a = 0
			
			//double[] res = sg.calcularEcuacionSegundoGrado(4, 2, 2); //discrimnate negativo
			
			double[] res = sg.calcularEcuacionSegundoGrado(1, 1, 1); 
			
			System.out.println("Los resultados son " + res[0] + " y " + res[1] + ".");
		} catch (ArithmeticException e) {
			System.out.println("No se puede dividir entre cero, revise el valor de a.");
		} catch (AIsZeroException e) {
			System.out.println("El valor a es cero.");
		} catch (NotANumberException e) {
			System.out.println("El discriminate es negativo.");
		} catch (Exception e) {
			System.out.println("Error general.");
		}
	
	}

}
