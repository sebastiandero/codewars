package com.sebastiandero.kata;

import com.sebastiandero.kata.TripleDouble;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TripleDoubleTest {

    @Test
    public void test1() {
        assertEquals(1, TripleDouble.TripleDouble(451999277L, 41177722899L));
    }

    @Test
    public void test2() {
        assertEquals(0, TripleDouble.TripleDouble(1222345L, 12345L));
    }

    @Test
    public void test3() {
        assertEquals(0, TripleDouble.TripleDouble(12345L, 12345L));
    }

    @Test
    public void test4() {
        assertEquals(1, TripleDouble.TripleDouble(666789L, 12345667L));
    }
}