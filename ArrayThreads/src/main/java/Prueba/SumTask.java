package Prueba;

import java.util.concurrent.Callable;

class SumTask implements Callable<Long> {
    private final int[] array;

    public SumTask(int[] array) {
        this.array = array;
    }

    @Override
    public Long call() {
        long sum = 0;
        for (int num : array) {
            sum += num;
        }
        return sum;
    }

}
