package com.sebastiandero;

import java.util.ArrayList;
import java.util.List;

class SumDigPow {

    static List<Long> sumDigPow(long a, long b) {
        List<Long> res = new ArrayList<>();
        for (long i = a; i <= b; i++) {
            long sum = 0;
            long current = i;

            for (int j = (int) (Math.log10(Math.abs(current)) + 1); current != 0 && j > 0; j--) {
                sum += Math.pow(current % 10, j);
                current /= 10;
            }

            if (sum == i) {
                res.add(i);
            }
        }

        return res;
    }
}
