package Prueba;

import java.util.concurrent.ExecutionException;

public class Example {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int tamano = App.genRandomSize(100, 1000);

        int[] array = App.genRandomArray(tamano, 10, 1000);

        imprimirArray(array);
        System.out.println("Suma total usando hilos: " + App.parallelSum(array));
        System.out.println("Suma total sequencial: " + App.sequentialSum(array));

    }

    public static void imprimirArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
            }
            System.out.println();
        }

    }


