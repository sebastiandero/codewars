package com.sebastiandero.kata;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class PokerHandTest {

    @Test
    public void kickerTIARTest() {
        ArrayList<PokerHand> expected = new ArrayList<>();
        expected.add(new PokerHand("AH AC 5H 6H AS"));
        expected.add(new PokerHand("AH AC 4H 6H AS"));
        expected.add(new PokerHand("AH AC 4H 5H AS"));
        expected.add(new PokerHand("AH AC 3H 5H AS"));

        Random random = new Random();
        ArrayList<PokerHand> actual = createRandomOrderedList(random, expected);
        // Act
        Collections.sort(actual);

        assertEquals(expected.toString(), actual.toString());

    }

    @Test
    public void kickerFIARTest() {
        ArrayList<PokerHand> expected = new ArrayList<>();
        expected.add(new PokerHand("JS JD JC JH 8D"));
        expected.add(new PokerHand("JS JD JC JH 4D"));
        expected.add(new PokerHand("JS JD JC JH 3D"));
        expected.add(new PokerHand("JS JD JC JH 2D"));

        Random random = new Random();
        ArrayList<PokerHand> actual = createRandomOrderedList(random, expected);
        // Act
        Collections.sort(actual);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void kickerPairTest() {
        ArrayList<PokerHand> expected = new ArrayList<>();
        expected.add(new PokerHand("AH AC KH 6H 9S"));
        expected.add(new PokerHand("AH AC TH 6H 9S"));
        expected.add(new PokerHand("AH AC 4H 6H 9S"));
        expected.add(new PokerHand("AH AC 5H 6H 7S"));
        expected.add(new PokerHand("AH AC 4H 6H 7S"));
        expected.add(new PokerHand("AH AC 4H 2H 7S"));

        Random random = new Random();
        ArrayList<PokerHand> actual = createRandomOrderedList(random, expected);
        // Act
        Collections.sort(actual);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void kickerHCTest() {
        ArrayList<PokerHand> expected = new ArrayList<>();
        expected.add(new PokerHand("2S 3H 6H 7S KC"));
        expected.add(new PokerHand("2S 3H 6H 7S QC"));
        expected.add(new PokerHand("2S 3H 6H 7S JC"));
        expected.add(new PokerHand("2S 3H 6H 7S TC"));
        expected.add(new PokerHand("2S 3H 6H 7S 9C"));
        expected.add(new PokerHand("2S 3H 6H 7S 8C"));

        Random random = new Random();
        ArrayList<PokerHand> actual = createRandomOrderedList(random, expected);
        // Act
        Collections.sort(actual);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void pokerHandSortTest() {
        // Arrange
        ArrayList<PokerHand> expected = new ArrayList<>();
        expected.add(new PokerHand("KS AS TS QS JS"));
        expected.add(new PokerHand("2H 3H 4H 5H 6H"));
        expected.add(new PokerHand("AS AD AC AH JD"));
        expected.add(new PokerHand("JS JD JC JH 3D"));
        expected.add(new PokerHand("2S AH 2H AS AC"));
        expected.add(new PokerHand("AS 3S 4S 8S 2S"));
        expected.add(new PokerHand("2H 3H 5H 6H 7H"));
        expected.add(new PokerHand("2S 3H 4H 5S 6C"));
        expected.add(new PokerHand("2D AC 3H 4H 5S"));
        expected.add(new PokerHand("AH AC 5H 6H AS"));
        expected.add(new PokerHand("2S 2H 4H 5S 4C"));
        expected.add(new PokerHand("AH AC 5H 6H 7S"));
        expected.add(new PokerHand("AH AC 4H 6H 7S"));
        expected.add(new PokerHand("2S AH 4H 5S KC"));
        expected.add(new PokerHand("2S 3H 6H 7S 9C"));

        Random random = new Random();
        ArrayList<PokerHand> actual = createRandomOrderedList(random, expected);
        // Act
        Collections.sort(actual);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void pokerHandRandomTest() {
        for (int i = 0; i < 1500; i++) {
            pokerHandSortTest();
        }
        for (int i = 0; i < 1500; i++) {
            kickerFIARTest();
        }
        for (int i = 0; i < 1500; i++) {
            kickerTIARTest();
        }
        for (int i = 0; i < 1500; i++) {
            kickerPairTest();
        }
        for (int i = 0; i < 1500; i++) {
            kickerHCTest();
        }
    }

    private ArrayList<PokerHand> createRandomOrderedList(Random random, ArrayList<PokerHand> expected) {
        ArrayList<PokerHand> actual = new ArrayList<PokerHand>();
        for (PokerHand pokerHand : expected) {
            int j = random.nextInt(actual.size() + 1);
            actual.add(j, pokerHand);
        }
        return actual;
    }
}
