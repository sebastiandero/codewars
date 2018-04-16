package com.sebastiandero.kata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Snail {

    public static void main(String[] args) {
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(Arrays.toString(snail(array)));
    }

    public static int[] snail(int[][] array) {
        boolean isEven = array.length % 2 == 0;

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; isEven ? i < array.length / 2 : i <= Math.ceil(array.length / 2); i++) {
            list.addAll(forOffset(array, i));
        }

        if(list.isEmpty()){
            return new int[0];
        }

        return list.stream().mapToInt(i -> i).toArray();
    }

    private static List<Integer> forOffset(int[][] array, int offset) {
        ArrayList<Integer> list = new ArrayList<>();

        int x = offset;
        int y = offset;
        list.add(array[y][x]);

        while (y != offset + 1 || x != offset) {
            if (x < array[y].length - 1 - offset && y == offset) {
                x++;
            } else if (x == array[y].length - 1 - offset && y < array.length - 1 - offset) {
                y++;
            } else if (y == array.length - 1 - offset && x > offset) {
                x--;
            } else if (x == offset && list.size() > 1) {
                y--;
            } else {
                break;
            }
            list.add(array[y][x]);
        }

        return list;
    }
}
