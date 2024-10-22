
public class Ordenacion {

	private int[] v;
	
	public Ordenacion(int[] v) {
		this.v = v;
	}
	
	public void intercambio(int i, int j) {
        int aux = v[i];
        v[i] = v[j];
        v[j] = aux;
    } 
	
	 public void ordenar() {
	        for (int i = 0; i < v.length - 1; i++) {
	            for (int j = 0; j < v.length - 1 - i; j++) 
	                if (v[j] > v[j + 1]) 
	                    intercambio(j, j + 1);   
	        }
	    }
		public void mostrar() {
			for (int i=0;i < v.length; i++) {
				   System.out.print(v[i]+" "); 
			}
		}
}


/*
 * 14, 13, 8, 7, 6, 12      original					
 * 13, 14, 8, 7, 6, 12		intercambio i = 0 j = 0	  la i hasta < 6 - 1 la j hasta < 6 - 1 - 0  5
 * 13, 8, 14, 7, 6, 12      intercambio i = 0 j = 1 
 * 13, 8, 7, 14, 6, 12		intercambio i = 0 j = 2
 * 13, 8, 7, 6, 14, 12		intercambio i = 0 j = 3
 * 13, 8, 7, 6, 12, 14		intercambio i = 0 j = 4
 * 
 * 8, 13, 7, 6, 12 ,14		intercambio i = 1 j = 0   la i hasta < 6 - 1 la j hasta < 6 - 1 - 1  4
 * 8, 7, 13, 6, 12, 14		intercambio i = 1 j = 1 
 * 8, 7, 6, 13, 12, 14		intercambio	i = 1 j = 2
 * 8, 7, 6, 12, 13, 14		intercambio i = 1 j = 3 
 * 
 * 7, 8, 6, 12, 13, 14		intercambio i = 2 j = 0   la i hasta < 6 - 1 la j hasta < 6 - 1 - 2  3
 * 7, 6, 8, 12, 13, 14		intercambio i = 2 j = 1
 * 7, 6, 8, 12, 13, 14					i = 2 j = 2
 * 
 * 7, 8, 6, 12, 13, 14					i = 3 j = 0	  la i hasta < 6 - 1 la j hasta < 6 - 1 - 3  2
 * 7, 6, 8, 12, 13, 14		intercambio i = 3 j = 1
 * 
 * 6, 7, 8, 12, 13, 14		intercambio i = 4 j = 0   la i hasta < 6 - 1 la j hasta < 6 - 1 - 4  1
 * 
 */

/*
Otro algoritmo podria ser tener un bucle general que repita hasta < v.lenght

y tener un bucle que detecte el valor mas bajo del array o una funcion min(), entonces lo añades a un array nuevo
y borras el valor del array original

Hasta que el array original desaparece y te queda el nuevo que lo devuelves
*/