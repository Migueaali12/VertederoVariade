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

    public static void main(String[] args) throws RunnerException {

        Options options = new OptionsBuilder()
                .include(AppBenchmark.class.getSimpleName())
                .build();

        new Runner(options).run();

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

    public static long calcularSumaHilos(int[][] matriz) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(matriz[0].length);
        List<Future<Long>> resultadosParciales = new ArrayList<>();

        for (int j = 0; j < matriz[0].length; j++) {
            int columna = j;
            Callable<Long> tarea = new SumColumnaCallable(matriz, columna);
            Future<Long> resultadoParcial = executor.submit(tarea);
            resultadosParciales.add(resultadoParcial);
        }

        long sumaTotal = 0;
        for (Future<Long> resultadoParcial : resultadosParciales) {
            sumaTotal += resultadoParcial.get();
        }

        executor.shutdown();

        return sumaTotal;
    }

    public static long calcularSumaSecuencial(int[][] matriz) {
        long sumaTotal = 0;
        for (int j = 0; j < matriz[0].length; j++) {
            long sumaColumna = 0;
            for (int i = 0; i < matriz.length; i++) {
                sumaColumna += matriz[i][j];
            }
            sumaTotal += sumaColumna;
        }
        return sumaTotal;
    }

}
