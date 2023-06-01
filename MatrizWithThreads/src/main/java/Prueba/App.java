package Prueba;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class App {

    public static int tamano = generarSizeAleatorio(1000, 10_000);
    public static int [][] matriz = generarMatrizAleatoria(tamano ,100, 500_001);

    public static void main(String[] args) throws RunnerException {

        Options options = new OptionsBuilder()
                .include(AppBenchmark.class.getSimpleName())
                .build();

        new Runner(options).run();

    }

    public static int generarSizeAleatorio(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static int[][] generarMatrizAleatoria(int size, int rangoMin, int rangoMax) {
        int[][] matriz = new int[size][size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matriz[i][j] = random.nextInt(rangoMax - rangoMin + 1) + rangoMin;
            }
        }
        return matriz;
    }

    public static int calcularSumaHilos(int[][] matriz) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(matriz[0].length);
        List<Future<Integer>> resultadosParciales = new ArrayList<>();

        for (int j = 0; j < matriz[0].length; j++) {
            int columna = j;
            Callable<Integer> tarea = new SumColumnaCallable(matriz, columna);
            Future<Integer> resultadoParcial = executor.submit(tarea);
            resultadosParciales.add(resultadoParcial);
        }

        int sumaTotal = 0;
        for (Future<Integer> resultadoParcial : resultadosParciales) {
            sumaTotal += resultadoParcial.get();
        }

        executor.shutdown();

        return sumaTotal;
    }

    public static int calcularSumaSecuencial(int[][] matriz) {
        int sumaTotal = 0;
        for (int j = 0; j < matriz[0].length; j++) {
            int sumaColumna = 0;
            for (int i = 0; i < matriz.length; i++) {
                sumaColumna += matriz[i][j];
            }
            sumaTotal += sumaColumna;
        }
        return sumaTotal;
    }

}
