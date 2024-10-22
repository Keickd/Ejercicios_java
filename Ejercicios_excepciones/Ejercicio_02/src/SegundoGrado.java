
public class SegundoGrado {
	public double[] calcularEcuacionSegundoGrado(double a, double b, double c) throws ArithmeticException, AIsZeroException, NotANumberException{
		double res[] = new double[2]; 
		
		if(a != 0) {
			double discriminante = Math.sqrt(Math.pow(b, 2) - 4 * a * c);
			if(discriminante >= 0) {
				res[0] = (- b + discriminante) / 2 * a;
				res[1] = (- b - discriminante) / 2 * a;
				return res;
			}
			throw new NotANumberException();
		}
		
		throw new AIsZeroException();
			
	}
}
