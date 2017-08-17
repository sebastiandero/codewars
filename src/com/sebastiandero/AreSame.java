package com.sebastiandero;

import java.util.Arrays;

public class AreSame {

    public static boolean comp(int[] a, int[] b) {
        if (a == null || b == null || a.length != b.length) {
            return false;
        }
        double[] arr1 = Arrays.stream(a).mapToDouble(value -> Math.pow(value, 2)).toArray();
        double[] arr2 = Arrays.stream(b).mapToDouble(value -> (double) value).toArray();

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return Arrays.equals(arr1, arr2);
    }
}
