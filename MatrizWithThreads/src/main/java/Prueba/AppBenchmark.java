package Prueba;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Fork(value = 2)
public class AppBenchmark {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        public static int [][] matriz = App.matriz;

    }

    @Benchmark
    public void sumaHilosBenchmark() throws ExecutionException, InterruptedException {
        int sumaTotal = App.calcularSumaHilos(BenchmarkState.matriz);
        System.out.println("Suma utilizando hilos: " + sumaTotal);
    }

    @Benchmark
    public void sumaSecuencialBenchmark() {
        int sumaTotal = App.calcularSumaSecuencial(BenchmarkState.matriz);
        System.out.println("Suma de forma secuencial: " + sumaTotal);
    }
}
