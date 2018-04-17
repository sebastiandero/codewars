package com.sebastiandero.kata;

import java.util.Arrays;

public class Boggle {

    private char[][] board;
    private char[] word;
    private short maxRepetition;


    public Boggle(final char[][] board, final String word) {
        this.board = board;
        this.word = word.toCharArray();


        //int[] repetitions = new int[Character.MAX_VALUE];
/*
        for (char[] row : board) {
            for (char c : row) {
                repetitions[c]++;
            }
        }
        maxRepetition = (short) Arrays.stream(repetitions).max().orElse(0);*/
        maxRepetition = 200;
    }

    public boolean check() {
        short[][][] tree = new short[word.length][maxRepetition][2];
        short[] subTreeIterator = new short[word.length + 1];
        if (word.length != 0 && maxRepetition != 0) {
            tree[0] = new short[][]{{-1, -1}};
        }
        short[] currentCoords;

        for (short i = 0; i < word.length; i++) {
            if (subTreeIterator[i] >= tree[i].length) {
                subTreeIterator[i] = 0;
                i -= 2;
                if (i < -1) {
                    return false;
                }
                subTreeIterator[i + 1]++;
                continue;
            }
            currentCoords = tree[i][subTreeIterator[i]];


            short[][] possibleCoordinates = getPossibleCoordinates(word[i]);
            short[][] nextCoordinates = getNextCoordinates(possibleCoordinates, currentCoords, tree, subTreeIterator, i);

            if (nextCoordinates.length == 0) {
                if (i == 0) {
                    return false;
                }
                i--;
                subTreeIterator[i + 1]++;
                continue;
            }
            if (i + 1 < tree.length) {
                tree[i + 1] = nextCoordinates;
            } else {
                break;
            }
        }
        return true;
    }

    private short[][] getNextCoordinates(short[][] possibleCoordinates, short[] currentCoords, short[][][] tree, short[] subTreeIterator, int currentCharacterInWord) {
        short[][] nextCoords = new short[maxRepetition][2];
        short iterator = 0;
        for (short[] pc : possibleCoordinates) {
            if (currentCoords[0] == -1 && currentCoords[1] == -1) {
                nextCoords[iterator] = pc;
                iterator++;
            } else if (pc[0] <= currentCoords[0] + 1 && currentCoords[0] - 1 <= pc[0]) {
                if (pc[1] <= currentCoords[1] + 1 && currentCoords[1] - 1 <= pc[1]) {
                    boolean alreadyUsed = false;
                    for (short i = 1; i < currentCharacterInWord; i++) {
                        if (subTreeIterator[i] < tree[i].length) {
                            short[] treeCords = tree[i][subTreeIterator[i]];
                            if (treeCords[0] == pc[0] && treeCords[1] == pc[1]) {
                                alreadyUsed = true;
                                break;
                            }
                        }
                    }
                    if (!alreadyUsed) {
                        nextCoords[iterator] = pc;
                        iterator++;
                    }
                }
            }
        }
        return Arrays.copyOfRange(nextCoords, 0, iterator);
    }

    private short[][] getPossibleCoordinates(char character) {
        short[][] ret = new short[maxRepetition][2];
        short iterator = 0;
        for (short i = 0; i < board.length; i++) {
            for (short j = 0; j < board[i].length; j++) {
                if (board[i][j] == character) {
                    ret[iterator][0] = i;
                    ret[iterator][1] = j;
                    iterator++;
                }
            }
        }
        return Arrays.copyOfRange(ret, 0, iterator);
    }
}