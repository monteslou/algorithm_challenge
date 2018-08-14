package com.company.Supermarket;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class SupermarketTest {
    @Test
    public void SupermarketChallenge(){
        Integer [] size = new Integer[]{7, 3, 5};
        int c = 8;
        Integer [] expected = new Integer[]{0, 1, 1};

        Supermarket s = new Supermarket();
        assertArrayEquals("", s.Supermarket(size, c), expected);

    }

    @Test
    public void SupermarketOrderListAsc(){
        Integer [] size = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int c = 16;
        Integer [] expected = new Integer[]{1, 1, 1, 1, 0, 1, 0, 0, 0, 0};

        Supermarket s = new Supermarket();
        assertArrayEquals("", s.Supermarket(size, c), expected);
    }

    @Test
    public void SupermarketOrderListDes(){
        Integer [] size = new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int c = 16;
        Integer [] expected = new Integer[]{0, 0, 0, 0, 1, 0, 1, 1, 1, 1};

        Supermarket s = new Supermarket();
        assertArrayEquals("", s.Supermarket(size, c), expected);

    }

    @Test
    public void SupermarketSameSizeForEachItem(){
        Integer [] size = new Integer[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        int c = 40;
        Integer [] expected = new Integer[]{1, 1, 1, 1, 0, 0, 0, 0, 0, 0};

        Supermarket s = new Supermarket();
        assertArrayEquals("", s.Supermarket(size, c), expected);

    }

}