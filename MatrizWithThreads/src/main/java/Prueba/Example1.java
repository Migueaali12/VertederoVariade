package Prueba;

import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Example1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int tamano = generarSizeAleatorio(1000, 2000);

        int[][] matriz = generarMatrizAleatoria(tamano, 1000, 5000);

        imprimirMatriz(matriz);
        System.out.println(App.calcularSumaHilos(matriz));

    }

    public static void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int generarSizeAleatorio(int min, int max) {
        Random random = new Random();
        return random.nextInt(min, max + 1) ;
    }

    public static int[][] generarMatrizAleatoria(int size, int rangoMin, int rangoMax) {
        int[][] matriz = new int[size][size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matriz[i][j] = random.nextInt(rangoMin, rangoMax + 1);
            }
        }
        return matriz;
    }

}
