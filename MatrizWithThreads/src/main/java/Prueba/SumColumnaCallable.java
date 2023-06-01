package Prueba;

import java.util.concurrent.Callable;

public class SumColumnaCallable implements Callable<Long> {
    private int[][] matriz;
    private int columna;

    public SumColumnaCallable(int[][] matriz, int columna) {
        this.matriz = matriz;
        this.columna = columna;
    }

    @Override
    public Long call() {
        long suma = 0;
        for (int i = 0; i < matriz.length; i++) {
            suma += matriz[i][columna];
        }
        return suma;
    }

}
