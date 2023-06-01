package Prueba;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.*;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Fork(value = 2)
public class AppBenchmark {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

         static int tamano = App.generarSizeAleatorio(1000, 10_000);
         static int [][] matriz = App.generarMatrizAleatoria(tamano, 100, 500_001);

    }

    @Benchmark
    public void sumaHilosBenchmark() throws ExecutionException, InterruptedException {
        long sumaTotal = App.calcularSumaHilos(BenchmarkState.matriz);
        //System.out.println("Suma utilizando hilos: " + sumaTotal);
    }

    @Benchmark
    public void sumaSecuencialBenchmark() {
        long sumaTotal = App.calcularSumaSecuencial(BenchmarkState.matriz);
        //System.out.println("Suma de forma secuencial: " + sumaTotal);
    }
}
