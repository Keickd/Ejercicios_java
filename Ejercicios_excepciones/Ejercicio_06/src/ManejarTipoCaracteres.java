import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManejarTipoCaracteres {
	
	private String cadena;
	
	public ManejarTipoCaracteres(String cadena) throws CharNoAlfabeticoException {
		this.cadena = cadena;
		comprobarCaracteresAlfabeticos();
	}
	
	public void comprobarCaracteresAlfabeticos() throws CharNoAlfabeticoException{
		Pattern pattern = Pattern.compile("[^a-zA-Z]");
        Matcher matcher = pattern.matcher(cadena);
        
    	if(matcher.find())
        	throw new CharNoAlfabeticoException();
	}
}
