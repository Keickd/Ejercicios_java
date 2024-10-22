public class Main {

    public static void intercambio(int[] v, int i, int j) {
        int aux = v[i];
        v[i] = v[j];
        v[j] = aux;
    }

    public static void ordenar(int[] v, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) 
                if (v[j] > v[j + 1]) 
                    intercambio(v, j, j + 1);
        }
    }

    public static void mostrar(int[] z) {
        for (int i = 0; i < z.length; i++) {
            System.out.print(z[i] + " ");
        }
    }

    public static void main(String[] args) {
        int z[] = {14, 13, 8, 7, 6, 12, 11, 10, 9, -5, 1, 5};
        ordenar(z, z.length);
        mostrar(z);
    }
}
