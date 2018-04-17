package com.sebastiandero.kata;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Boggle {
    private ArrayList<Coordinate> coordinatesHistory;
    private char[][] board;
    private String word;

    public Boggle(final char[][] board, final String word) {
        this.board = board;
        this.word = word;
        coordinatesHistory = new ArrayList<>();
    }

    public boolean check() {
        List<Coordinate> firstLetterCoordinates = findLetter(board, word.charAt(0));
        for (Coordinate coordinate : firstLetterCoordinates) {
            if (doNext(coordinate, 1)) {
                return true;
            }
        }
        return false;
    }

    private boolean doNext(Coordinate coordinate, int index) {
        if (coordinatesHistory.contains(coordinate)) {
            return false;
        }
        coordinatesHistory.add(coordinate);
        if (word.length() <= index) {
            return true;
        }

        char[] charArray = word.toCharArray();

        if (index < charArray.length) {
            List<Coordinate> letterCoordinates = findLetter(board, charArray[index]);
            List<Coordinate> nextPossible = getOneAway(coordinate, letterCoordinates);

            if (nextPossible.size() == 0) {
                coordinatesHistory.remove(coordinate);
                return false;
            }

            for (Coordinate c : nextPossible) {
                if (doNext(c, index + 1)) {
                    return true;
                }
            }
        }
        coordinatesHistory.remove(coordinate);
        return false;
    }

    private List<Coordinate> getOneAway(Coordinate current, List<Coordinate> letterCoordinates) {
        return letterCoordinates.stream().filter(current::bordering).collect(Collectors.toList());
    }

    private List<Coordinate> findLetter(char[][] board, char letter) {
        List<Coordinate> list = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == letter) {
                    list.add(new Coordinate(j, i));
                }
            }
        }
        return list;
    }

    private class Coordinate {
        int x;
        int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        boolean bordering(Coordinate other) {
            if (x + 1 >= other.x && other.x >= x - 1) {
                return y + 1 >= other.y && other.y >= y - 1;
            }
            return false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x &&
                    y == that.y;
        }

        @Override
        public int hashCode() {

            return Objects.hash(x, y);
        }
    }
}
