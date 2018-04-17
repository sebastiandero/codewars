package com.sebastiandero.kata;

import java.util.*;
import java.util.stream.Collectors;

public class BattleShips {

    private static Ship[] ships;

    public static Map<String, Double> damagedOrSunk(final int[][] board, final int[][] attacks) {
        ships = new Ship[3];
        // Your code here...
        List<Ship> shipsList = Arrays.stream(ships).filter(Objects::nonNull).collect(Collectors.toList());

        double sunk = shipsList.stream().filter(Ship::isSunk).count();
        double damaged = shipsList.stream().filter(Ship::isTouched).count();
        double notTouched = shipsList.stream().filter(Ship::notTouched).count();
        double points = shipsList.stream().mapToDouble(Ship::getScore).sum();

        Map<String, Double> returnMap = new HashMap<>();
        returnMap.put("sunk", sunk);
        returnMap.put("damaged", damaged);
        returnMap.put("notTouched", notTouched);
        returnMap.put("points", points);

        return returnMap;
    }

    private static class Ship {
        //1 represents a hit, 0 no hit
        int hits;
        int size;

        public Ship(int size) {
            this.size = size;
        }

        public void hitShip() {
            hits++;
        }

        private boolean isSunk() {
            return hits == size;
        }

        private boolean notTouched() {
            return hits == 0;
        }

        private boolean isTouched() {
            return !notTouched();
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
