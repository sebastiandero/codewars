package com.sebastiandero.kata;

import java.util.*;
import java.util.stream.Collectors;

public class BattleShips {

    private static Ship[] ships;

    public static Map<String, Double> damagedOrSunk(final int[][] board, final int[][] attacks) {
        ships = new Ship[3];
        // Your code here...

        fillWithShips(board);
        simulateAttacks(board, attacks);

        //build return list
        List<Ship> shipsList = Arrays.stream(ships).filter(Objects::nonNull).collect(Collectors.toList());

        Map<String, Double> returnMap = new HashMap<>();
        returnMap.put("sunk", (double) shipsList.stream().filter(Ship::isSunk).count());
        returnMap.put("damaged", (double) shipsList.stream().filter(ship -> !ship.isSunk()).filter(Ship::isTouched).count());
        returnMap.put("notTouched", (double) shipsList.stream().filter(ship -> !ship.isTouched()).count());
        returnMap.put("points", shipsList.stream().mapToDouble(Ship::getScore).sum());

        return returnMap;
    }

    private static void simulateAttacks(int[][] board, int[][] attacks) {
        for (int[] attack : attacks) {
            int y = board.length - (attack[1]);
            int boat = board[y][attack[0] - 1];
            if (boat != 0) {
                ships[boat - 1].hitShip();
            }
        }
    }

    private static void fillWithShips(int[][] board) {
        for (int i = 0; i < 3; i++) {
            int count = 0;
            for (int[] boardRow : board) {
                for (int field : boardRow) {
                    if (field == i + 1) {
                        count++;
                    }
                }
            }
            if (count != 0) {
                ships[i] = new Ship(count);
            }
        }
    }

    private static class Ship {
        //1 represents a hit, 0 no hit
        int hits;
        int size;

        Ship(int size) {
            this.size = size;
        }

        void hitShip() {
            hits++;
        }

        private boolean isSunk() {
            return hits == size;
        }

        private boolean isTouched() {
            return hits != 0;
        }

        private double getScore() {
            if (isSunk()) {
                return 1;
            }
            if (isTouched()) {
                return 0.5;
            }
            return -1;
        }
    }
}
