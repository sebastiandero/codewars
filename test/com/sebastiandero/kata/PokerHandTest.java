package com.sebastiandero.kata;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void randomizedTest() {
        List<String> hands = new ArrayList<>();
        hands.add("JH AH TH KH QH"); // royal flush
        hands.add("JH 9H TH KH QH"); // straight flush
        hands.add("5C 6C 3C 7C 4C"); // straight flush
        hands.add("2D 6D 3D 4D 5D"); // straight flush
        hands.add("2C 3C AC 4C 5C"); // straight flush
        hands.add("JC KH JS JD JH"); // 4 of a kind
        hands.add("JC 7H JS JD JH"); // 4 of a kind
        hands.add("JC 6H JS JD JH"); // 4 of a kind
        hands.add("KH KC 3S 3H 3D"); // full house
        hands.add("2H 2C 3S 3H 3D"); // full house
        hands.add("3D 2H 3H 2C 2D"); // full house
        hands.add("JH 8H AH KH QH"); // flush
        hands.add("4C 5C 9C 8C KC"); // flush
        hands.add("3S 8S 9S 5S KS"); // flush
        hands.add("8C 9C 5C 3C TC"); // flush
        hands.add("QC KH TS JS AH"); // straight
        hands.add("JS QS 9H TS KH"); // straight
        hands.add("6S 8S 7S 5H 9H"); // straight
        hands.add("3C 5C 4C 2C 6H"); // straight
        hands.add("2C 3H AS 4S 5H"); // straight
        hands.add("AC KH QH AH AS"); // 3 of a kind
        hands.add("7C 7S KH 2H 7H"); // 3 of a kind
        hands.add("7C 7S 3S 7H 5S"); // 3 of a kind
        hands.add("AS 3C KH AD KC"); // 2 pairs
        hands.add("3C KH 5D 5S KC"); // 2 pairs
        hands.add("5S 5D 2C KH KC"); // 2 pairs
        hands.add("3H 4C 4H 3S 2H"); // 2 pairs
        hands.add("AH 8S AH KC JH"); // pair
        hands.add("KD 4S KD 3H 8S"); // pair
        hands.add("KC 4H KS 2H 8D"); // pair
        hands.add("QH 8H KD JH 8S"); // pair
        hands.add("8C 4S KH JS 4D"); // pair
        hands.add("KS 8D 4D 9S 4S"); // pair
        hands.add("KD 6S 9D TH AD");
        hands.add("TS KS 5S 9S AC");
        hands.add("JH 8S TH AH QH");
        hands.add("TC 8C 2S JH 6C");
        hands.add("2D 6D 9D TH 7D");
        hands.add("9D 8H 2C 6S 7H");
        hands.add("4S 3H 2C 7S 5H");

        Random random = new Random();
        ArrayList<PokerHand> expected = new ArrayList<>();
        int i=0;
        for (String hand : hands) {
            if (i==5) expected.add(new PokerHand("AS AC AH KS AS"));
            else if (i==23) expected.add(new PokerHand("6C 6S 3S 6H 5S"));
            else expected.add(new PokerHand(hand));
            i++;
        }
        for (int j = 0; i < 25000; i++) {
            ArrayList<PokerHand> actual = createRandomOrderedList(random, expected);
            assertTrue(differences(expected, actual) > 30);
            Collections.sort(actual);
            assertEquals(expected, actual);
            assertEquals("Expect no differences in the sort order between the expected and actual list of " + expected.size() + " items.", 0, differences(expected, actual));
        }
    }

    private ArrayList<PokerHand> createRandomOrderedList(Random random, ArrayList<PokerHand> expected) {
        ArrayList<PokerHand> actual = new ArrayList<>();
        for (PokerHand pokerHand : expected) {
            int j = random.nextInt(actual.size() + 1);
            actual.add(j, pokerHand);
        }
        return actual;
    }

    private int differences(ArrayList<PokerHand> expected, ArrayList<PokerHand> actual) {
        Iterator a = actual.iterator();
        int count = 0;
        for (PokerHand e : expected) {
            count += e.equals(a.next()) ? 0 : 1;
        }
        return count;
    }
}
