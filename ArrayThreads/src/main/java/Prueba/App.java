package Prueba;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class App {

    public static void main(String[] args) throws RunnerException {

        Options options = new OptionsBuilder()
                .include(Benchmark.class.getSimpleName())
                .build();

        new Runner(options).run();

    }

    public static int genRandomSize(int min, int max) {
        Random random = new Random();
        return random.nextInt(min, max + 1) ;
    }

    public static int[] genRandomArray(int size, int rangoMin, int rangoMax) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(rangoMin, rangoMax + 1);
        }
        return array;
    }

    public static Long parallelSum(int[] array) throws InterruptedException, ExecutionException {
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(cores);

        List<Future<Long>> futures = new ArrayList<>();
        int startIndex = 0;
        int endIndex;

        for (int i = 0; i < cores; i++) {
            endIndex = startIndex + array.length / cores;
            if (i < array.length % cores) {
                endIndex++;
            }

            int[] subArray = Arrays.copyOfRange(array, startIndex, endIndex);
            startIndex = endIndex;

            Callable<Long> task = new SumTask(subArray);
            Future<Long> future = executor.submit(task);
            futures.add(future);
        }

        executor.shutdown();

        long totalSum = 0;
        for (Future<Long> future : futures) {
            totalSum += future.get();
        }

        return totalSum;
    }


    public static Long sequentialSum(int[] array) {
        long sum = 0;
        for (int num : array) {
            sum += num;
        }
        return sum;
    }

}

