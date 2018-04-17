package com.sebastiandero.kata;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class BoggleTest {

    @Test
    public void test5x5() {
        char[][] board = {
                {'X', 'Q', 'A', 'D', 'E'},
                {'Z', 'O', 'T', 'I', 'S'},
                {'I', 'N', 'D', 'O', 'L'},
                {'Y', 'R', 'U', 'N', 'B'},
                {'F', 'A', 'E', 'H', 'K'}
        };

        String[] toCheck = {"ZOTIS", "SIT", "LOIS", "DOT", "SLOB"};
        boolean[] expected = {true, true, true, true, true};

        for (int i = 0; i < toCheck.length; i++) {
            System.out.println(String.format("Check word %s, expected %s and got %s", toCheck[i], expected[i], new Boggle(deepCopy(board), toCheck[i]).check()));
            assertEquals(expected[i], new Boggle(deepCopy(board), toCheck[i]).check());
        }
    }

    @Test
    public void test4x4() {
        char[][] board = {
                {'X', 'Q', 'A', 'E'},
                {'Z', 'O', 'T', 'S'},
                {'I', 'N', 'D', 'L'},
                {'Y', 'R', 'U', 'K'}
        };

        String[] toCheck = {"XQAESLKURYIZ", "DNOT", "NIZOTS", "STONI", "SEAT", "ONIYR", "ZONIY", "MY", "SEAN", "SETS", "TOO"};
        boolean[] expected = {true, true, true, true, true, true, true, false, false, false, false};

        for (int i = 0; i < toCheck.length; i++) {
            System.out.println(String.format("Check word %s, expected %s and got %s", toCheck[i], expected[i], new Boggle(deepCopy(board), toCheck[i]).check()));
            assertEquals(expected[i], new Boggle(deepCopy(board), toCheck[i]).check());
        }
    }

    @Test
    public void sampleTests() {
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
    public void grid5x5Test() {
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
    public void performanceTest() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 50000; i++) {
            sampleTests();
            grid5x5Test();
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
