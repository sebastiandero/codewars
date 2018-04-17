package com.sebastiandero.kata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Boggle {

    private char[][] board;
    private char[] word;

    public Boggle(final char[][] board, final String word) {
        this.board = board;
        this.word = word.toCharArray();
    }

    public boolean check() {
        List<List<Coordinate>> tree = new ArrayList<>(word.length);
        if (word.length != 0) {
            tree.add(Collections.singletonList(new Coordinate((short) -1, (short) -1)));
        }
        for (short i = 1; i < word.length; i++) {
            tree.add(new ArrayList<>());
        }
        short[] subTreeIterator = new short[word.length + 1];
        Coordinate currentCoords;

        for (short i = 0; i < word.length; i++) {
            if (subTreeIterator[i] >= tree.get(i).size()) {
                subTreeIterator[i] = 0;
                i -= 2;
                if (i < -1) {
                    return false;
                }
                subTreeIterator[i + 1]++;
                continue;
            }
            currentCoords = tree.get(i).get(subTreeIterator[i]);

            List<Coordinate> possibleCoordinates = getPossibleCoordinates(word[i]);
            List<Coordinate> nextCoordinates = getNextCoordinates(possibleCoordinates, currentCoords, tree, subTreeIterator, i);

            if (nextCoordinates.size() == 0) {
                if (i == 0) {
                    return false;
                }
                i--;
                subTreeIterator[i + 1]++;
                continue;
            }
            if (i + 1 < tree.size()) {
                tree.set(i + 1, nextCoordinates);
            } else {
                break;
            }
        }
        return true;
    }

    private List<Coordinate> getNextCoordinates(List<Coordinate> possibleCoordinates, Coordinate currentCoords, List<List<Coordinate>> tree, short[] subTreeIterator, short currentCharacterInWord) {
        List<Coordinate> nextCoords = new ArrayList<>(10);
        for (short i = 0; i < possibleCoordinates.size(); i++) {
            Coordinate pc = possibleCoordinates.get(i);
            if (currentCoords.x == -1 && currentCoords.y == -1) {
                nextCoords.add(pc);
            } else if (pc.x <= currentCoords.x + 1 && currentCoords.x - 1 <= pc.x && pc.y <= currentCoords.y + 1 && currentCoords.y - 1 <= pc.y) {
                if (pc.x != currentCoords.x || pc.y != currentCoords.y) {
                    if (!alreadyUsed(tree, subTreeIterator, currentCharacterInWord, pc)) {
                        nextCoords.add(pc);
                    }
                }
            }
        }
        return nextCoords;
    }

    private boolean alreadyUsed(List<List<Coordinate>> tree, short[] subTreeIterator, short currentCharacterInWord, Coordinate pc) {
        for (short i = 1; i < currentCharacterInWord; i++) {
            if (subTreeIterator[i] < tree.get(i).size()) {
                Coordinate treeCords = tree.get(i).get(subTreeIterator[i]);
                if (treeCords.x == pc.x && treeCords.y == pc.y) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<Coordinate> getPossibleCoordinates(char character) {
        List<Coordinate> ret = new ArrayList<>(5);
        for (short i = 0; i < board.length; i++) {
            for (short j = 0; j < board[i].length; j++) {
                if (board[i][j] == character) {
                    ret.add(new Coordinate(j, i));
                }
            }
        }
        return ret;
    }

    private class Coordinate {
        short x;
        short y;

        Coordinate(short x, short y) {
            this.x = x;
            this.y = y;
        }
    }
}
