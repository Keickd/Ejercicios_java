
public class ManejarLongitud {
	
	private String cadena;
	
	public ManejarLongitud(String cadena) throws LongitudMayor30Exception {
		this.cadena = cadena;
		comprobarLongitud();
	}
	
	public void comprobarLongitud() throws LongitudMayor30Exception{
		
		if(cadena.length() > 30)
			throw new LongitudMayor30Exception();
	}

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}
	
}
