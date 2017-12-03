package com.sebastiandero.kata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumByFactors {

    public static String sumOfDivided(int[] numbers) {
        List<Integer> primes = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        int max = Arrays.stream(numbers).max().orElse(-1);
        fillPrimes(max, primes);

        for(int prime : primes) {
            boolean sumChanged = false;
            int sum = 0;
            for(int number : numbers) {
                if (number % prime == 0) {
                    sum += number;
                    sumChanged = true;
                }
            }
            if (sumChanged) {
                result.append("(").append(prime).append(" ").append(sum).append(")");
            }
        }

        return result.toString();
    }

    private static void fillPrimes(int max, List<Integer> primes) {
        for(int i = 2; i < max + 1; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
    }

    private static boolean isPrime(int i) {
        for(int j = 2; j < i; j++) {
            if (i % j == 0) {
                return false;
            }
        }
        return true;
    }
}
