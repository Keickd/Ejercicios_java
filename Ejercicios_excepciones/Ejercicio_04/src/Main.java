
/*
 * 4. El siguiente programa que maneja un algoritmo de ordenación básico no funciona bien. 
Situar declaraciones en el código del programa de modo que se compruebe si este código 
funciona correctamente. Escribir el programa correcto. 
 
  void intercambio (int x, int y){ 
   int= aux; 
   
   aux=x; 
   x=y; 
   y=aux; 
} 
void ordenar (int []v, int n){ 
   int i,j; 
   for (i=0;i<n-2;i++) 
    for (j=0;j<n-2-i;j++) 
     if(v[j]>v[j+1]) 
      intercambio(v[j], v[j+1]); 
}   
static public void main(String[] ar){ 
   int z[]={14,13,8,7,6,12,11,10,9,-5,1,5}; 
  
   ordenar(z,12); 
   for (j=0;j<n-2-i;j++) 
    System.out.print(z[i]+" "); 
} 
 */

public class Main {
	
		public static void main(String[] args){ 
		   int z[]={14,13,8,7,6,12,11,10,9,-5,1,5}; 
		  
		   Ordenacion ordenar = new Ordenacion(z);
		   ordenar.ordenar(); 
		   ordenar.mostrar();
		} 
}
