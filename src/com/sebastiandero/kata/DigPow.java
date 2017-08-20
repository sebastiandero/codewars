package com.sebastiandero.kata;

public class DigPow {

    public static long digPow(int n, int p) {
        long sum = 0;
        long current = n;
        for (int j = (int) (Math.log10(Math.abs(current)) + p); current != 0 && j > 0; j--) {
            sum += Math.pow(current % 10, j);
            current /= 10;
        }

        return ((double) sum / (double) n) % 1 == 0 ? sum / n : -1;
    }

}
