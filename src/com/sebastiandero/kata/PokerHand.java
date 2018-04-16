package com.sebastiandero.kata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PokerHand implements Comparable<PokerHand> {

    private int[] cards;
    private char[] suits;
    private String hand;

    public PokerHand(String hand) {
        this.cards = breakIntoIntArr(hand);
        this.hand = hand;
    }

    private int[] breakIntoIntArr(String hand) {
        String[] stringCards = hand.split(" ");
        int[] convertedCards = new int[5];
        suits = new char[5];

        for (int i = 0; i < stringCards.length; i++) {
            String stringCard = stringCards[i];
            convertedCards[i] = evaluateCardToInt(stringCard.charAt(0));
            suits[i] = stringCard.charAt(1);
        }
        Arrays.sort(convertedCards);
        return convertedCards;
    }

    private int evaluateCardToInt(char card) {
        if (Character.isDigit(card)) {
            return Integer.valueOf(String.valueOf(card));
        }
        switch (card) {
            case 'T':
                return 10;
            case 'J':
                return 11;
            case 'Q':
                return 12;
            case 'K':
                return 13;
            case 'A':
                return 14;
            default:
                return -1;
        }
    }

    @Override
    public int compareTo(PokerHand other) {
        //Straight flush
        int straightResult = compareStraight(other);
        int flushResult = compareFlush(other);
        if (straightResult != -2 && flushResult != -2 && straightResult == flushResult) {
            return straightResult * (-1);
        }
        //four of a kind
        int fourOfAKindResult = compareFourOfAKind(other);
        if (fourOfAKindResult != -2) {
            return fourOfAKindResult * (-1);
        }
        //full house
        int pairsResult = comparePairs(other);
        int threeOfAKindResult = compareThreeOfAKind(other);

        if (pairsResult != -2 && threeOfAKindResult != -2) {
            return compareFullHouse(pairsResult, threeOfAKindResult) * (-1);
        }
        //flush
        if (flushResult != -2) {
            return flushResult * (-1);
        }
        //straight
        if (straightResult != -2) {
            return straightResult * (-1);
        }
        //three of a kind
        if (threeOfAKindResult != -2) {
            return threeOfAKindResult * (-1);
        }
        //pairs
        if (pairsResult != -2) {
            return pairsResult * (-1);
        }
        //high card
        return compareHighCard(other) * (-1);
    }

    private int compareFourOfAKind(PokerHand other) {
        int f1 = getFourOfAKind();
        int f2 = other.getFourOfAKind();
        if (f1 == -1 && f2 == -1) {
            return -2;
        }
        return Integer.compare(f1, f2);
    }

    private int getFourOfAKind() {
        for (int i = cards.length - 1; i > 2; i--) {
            if (cards[i] == cards[i - 1] && cards[i] == cards[i - 2] && cards[i] == cards[i - 3]) {
                return cards[i];
            }
        }
        return -1;
    }

    private int compareFullHouse(int pairsResult, int threeOfAKindResult) {
        if (threeOfAKindResult != 0) {
            return threeOfAKindResult;
        }
        return pairsResult;
    }

    private int compareFlush(PokerHand other) {
        boolean hasFlush = suits[0] == suits[1]
                && suits[0] == suits[2]
                && suits[0] == suits[3]
                && suits[0] == suits[4];
        boolean otherHasFlush = other.suits[0] == other.suits[1]
                && other.suits[0] == other.suits[2]
                && other.suits[0] == other.suits[3]
                && other.suits[0] == other.suits[4];

        if (hasFlush && !otherHasFlush) {
            return 1;
        }
        if (!hasFlush && otherHasFlush) {
            return -1;
        }
        if (!hasFlush) {
            return -2;
        }

        for (int i = cards.length - 1; i >= 0; i--) {
            int res = Integer.compare(cards[i], other.cards[i]);
            if (res != 0) {
                return res;
            }
        }

        return 0;
    }

    private int compareStraight(PokerHand other) {
        int s1 = getStraight();
        int s2 = other.getStraight();
        if (s1 == -1 && s2 == -1) {
            return -2;
        }
        return Integer.compare(s1, s2);
    }

    private int getStraight() {
        if (cards[1] + 1 == cards[2] && cards[2] + 1 == cards[3]) {
            //normal
            if (cards[0] + 1 == cards[1] && cards[3] + 1 == cards[4]) {
                return cards[4];
            }
            //ace as 1
            if (cards[4] == 14 && cards[0] == 2) {
                return cards[3];
            }
        }
        return -1;
    }

    private int compareThreeOfAKind(PokerHand other) {
        int t1 = getThreeOfAKind();
        int t2 = other.getThreeOfAKind();
        if (t1 == -1 && t2 == -1) {
            return -2;
        }
        return Integer.compare(t1, t2);
    }

    private int getThreeOfAKind() {
        for (int i = cards.length - 1; i > 1; i--) {
            if (cards[i] == cards[i - 1] && cards[i] == cards[i - 2]) {
                return cards[i];
            }
        }
        return -1;
    }

    private int comparePairs(PokerHand other) {
        int[] pairs = getPairs();
        int[] otherPairs = other.getPairs();
        if (pairs.length != 0 || otherPairs.length != 0) {
            if (pairs.length != otherPairs.length) {
                return Integer.compare(pairs.length, otherPairs.length);
            }
            for (int i = 0; i < pairs.length; i++) {
                if (pairs[i] != otherPairs[i]) {
                    return Integer.compare(pairs[i], otherPairs[i]);
                }
            }
            return 0;
        }
        return -2;
    }

    private int[] getPairs() {
        List<Integer> pairs = new ArrayList<>();
        for (int i = cards.length - 1; i > 0; i--) {
            if (cards[i] == cards[i - 1]) {
                //look ahead and behind for three of a kind
                if ((i - 2 < 0 || cards[i] != cards[i - 2]) && (i + 1 >= cards.length || cards[i] != cards[i + 1])) {
                    pairs.add(cards[i]);
                    i--;

                    if (pairs.size() == 2) {
                        break;
                    }
                }
            }
        }
        return pairs.stream().mapToInt(i -> i).toArray();
    }

    private int compareHighCard(PokerHand o) {
        return Integer.compare(cards[4], o.cards[4]);
    }

    @Override
    public String toString() {
        return Arrays.toString(cards) + Arrays.toString(suits);
    }
}