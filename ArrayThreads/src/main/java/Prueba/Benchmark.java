package Prueba;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Fork(value = 2)
public class Benchmark {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        static int tamano = App.genRandomSize(100_000, 6_000_000);
        static int[] array = App.genRandomArray(tamano, 100, 500_001);

    }

    @org.openjdk.jmh.annotations.Benchmark
    public void sumThreadBenchmark() throws ExecutionException, InterruptedException {
        long sumaTotal = App.parallelSum(BenchmarkState.array);
        //System.out.println("Suma utilizando hilos: " + sumaTotal);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void sumSeqentialBenchmark() {
        long sumaTotal = App.sequentialSum(BenchmarkState.array);
        //System.out.println("Suma de forma secuencial: " + sumaTotal);
    }





}
