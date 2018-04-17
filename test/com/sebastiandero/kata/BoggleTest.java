package com.sebastiandero.kata;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class BoggleTest {

    @Test
    public void asampleTests() {
        char[][] board = {
                {'E', 'A', 'R', 'A'},
                {'N', 'L', 'E', 'C'},
                {'I', 'A', 'I', 'S'},
                {'B', 'Y', 'O', 'R'}
        };

        String[] toCheck = {"C", "EAR", "EARS", "BAILER", "RSCAREIOYBAILNEA", "CEREAL", "ROBES"};
        boolean[] expected = {true, true, false, true, true, false, false};

        for (int i = 0; i < toCheck.length; i++) {
            //System.out.println(String.format("Check word %s, expected %s and got %s", toCheck[i], expected[i], new Boggle(deepCopy(board), toCheck[i]).check()));
            assertEquals(expected[i], new Boggle(deepCopy(board), toCheck[i]).check());
        }
    }

    @Test
    public void bgrid5x5Test() {
        char[][] board = {
                {'T', 'T', 'M', 'D', 'A'},
                {'G', 'Y', 'I', 'N', 'N'},
                {'P', 'A', 'L', 'C', 'E'},
                {'I', 'A', 'U', 'L', 'G'},
                {'A', 'M', 'I', 'N', 'A'}
        };
        String[] toCheck = {"T", "TT", "YTG", "ANIMA"};
        boolean[] expected = {true, true, true, true};
        for (int i = 0; i < toCheck.length; i++) {
            //System.out.println(String.format("Check word %s, expected %s and got %s", toCheck[i], expected[i], new Boggle(deepCopy(board), toCheck[i]).check()));
            assertEquals(expected[i], new Boggle(deepCopy(board), toCheck[i]).check());
        }
    }

    @Test
    public void cperformanceTest() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 2000; i++) {
            asampleTests();
            bgrid5x5Test();
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
    }

    private char[][] deepCopy(char[][] arr) {
        return Arrays.stream(arr)
                .map(a -> Arrays.copyOf(a, a.length))
                .toArray(char[][]::new);
    }
}
