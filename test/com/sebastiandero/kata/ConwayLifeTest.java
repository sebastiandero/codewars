package com.sebastiandero.kata;

import org.junit.Assert;
import org.junit.Test;

public class ConwayLifeTest {
    @Test
    public void testGliders() {
        int[][][] gliders = {
                {{1, 0, 0}, {0, 1, 1}, {1, 1, 0}},
                {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}}
        };

        int[][] res = ConwayLife.getGeneration(gliders[0], 1);
        Assert.assertArrayEquals(res, gliders[1]);
    }

    @Test
    public void twoGliders() {
        /*
        ###...#.
        #......#
        .#...###
        * */
        int[][][] gliders = {
                {{1, 1, 1, 0, 0, 0, 1, 0}, {1, 0, 0, 0, 0, 0, 0, 1}, {0, 1, 0, 0, 0, 1, 1, 1}},
                {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}}
        };

        int[][] res = ConwayLife.getGeneration(gliders[0], 10);
        Assert.assertArrayEquals(res, gliders[1]);
    }
}
