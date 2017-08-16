package com.sebastiandero;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class PaginationHelperTest {

    private PaginationHelper<Character> pg;

    @Before
    public void setUp() throws Exception {
        pg = new PaginationHelper<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'), 4);
    }

    @Test
    public void itemCount() throws Exception {
        assertEquals(6, pg.itemCount());
    }

    @Test
    public void pageCount() throws Exception {
        assertEquals(2, pg.pageCount());
    }

    @Test
    public void pageItemCount() throws Exception {
        assertEquals(4, pg.pageItemCount(0));
        assertEquals(2, pg.pageItemCount(1));
        assertEquals(-1, pg.pageItemCount(2));
    }

    @Test
    public void pageIndex() throws Exception {
        assertEquals(0, pg.pageIndex(0));
        assertEquals(0, pg.pageIndex(1));
        assertEquals(0, pg.pageIndex(2));
        assertEquals(0, pg.pageIndex(3));
        assertEquals(1, pg.pageIndex(4));
        assertEquals(1, pg.pageIndex(5));
        assertEquals(-1, pg.pageIndex(6));
        assertEquals(-1, pg.pageIndex(7));
        assertEquals(0, pg.pageIndex(2));
        assertEquals(-1, pg.pageIndex(20));
        assertEquals(-1, pg.pageIndex(-10));
    }
}