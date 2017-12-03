package com.sebastiandero.kata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SumByFactorsTest {

    @Test
    public void testOne() {
        int[] lst = new int[] {12, 15};
        assertEquals("(2 12)(3 27)(5 15)",
                     SumByFactors.sumOfDivided(lst));
    }

    @Test
    public void testTwo() {
        int[] lst = new int[] {15, 30, -45};
        assertEquals("(2 30)(3 0)(5 0)", SumByFactors.sumOfDivided(lst));
    }
}