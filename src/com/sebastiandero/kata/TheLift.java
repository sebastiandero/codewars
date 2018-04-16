package com.sebastiandero.kata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TheLift {
    private static int[][] queues;

    public static int[] theLift(final int[][] queues, final int capacity) {
        TheLift.queues = queues;

        Lift lift = new Lift(capacity);

        while (!allArrived(lift)) {
            lift.move();
        }
        return lift.floorHistory.stream().mapToInt(i -> i).toArray();
    }

    private static boolean allArrived(Lift lift) {
        for (int i = 0; i < queues.length; i++) {
            for (int j = 0; j < queues[i].length; j++) {
                if (queues[i][j] != i) {
                    return false;
                }
            }
        }
        return lift.passengers.size() == 0 && lift.floor == 0;
    }

    private static void push(int person, int floor) {
        List<Integer> people = Arrays.stream(queues[floor]).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        people.add(person);
        queues[floor] = people.stream().mapToInt(i -> i).toArray();
    }

    private static int pop(int floor) {
        List<Integer> people = Arrays.stream(queues[floor]).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        int person;
        if (people.size() == 0) {
            person = -1;
            people = new ArrayList<>();
        } else if (people.size() == 1) {
            person = people.get(0);
            people = new ArrayList<>();
        } else {
            person = people.get(0);
            people = people.subList(1, people.size());
        }
        queues[floor] = people.stream().mapToInt(i -> i).toArray();
        return person;
    }

    private static class Lift {
        private final int capacity;
        private List<Integer> passengers;
        private List<Integer> floorHistory;
        private int floor;
        private boolean travelingUp = true;

        Lift(int capacity) {
            this.capacity = capacity;
            passengers = new ArrayList<>();
            floorHistory = new ArrayList<>();
            changeFloor(0);
        }

        void move() {
            goToNextFloor();
            deliverPassengers();
            pickupPassengers();
        }

        private void deliverPassengers() {
            List<Integer> arrivedPassengers = passengers.stream().filter(destination -> floor == destination).collect(Collectors.toList());
            passengers.removeAll(arrivedPassengers);
            for (Integer passenger : arrivedPassengers) {
                push(passenger, floor);
            }
        }

        private void pickupPassengers() {
            if (capacity > passengers.size()) {
                while (capacity > passengers.size() && queues[floor].length != 0 && anyGoingSameWay(floor)) {
                    passengers.add(pop(floor));
                }
            }
        }

        private void goToNextFloor() {
            for (int i = 0; i < queues.length; i++) {
                int possibleFloor = i;
                if (passengers.stream().anyMatch(dest -> dest == possibleFloor)) {
                    changeFloor(i);
                    return;
                }
                if (anyGoingSameWay(i)) {
                    changeFloor(i);
                    return;
                }
            }
            changeFloor(0);
        }

        private boolean anyGoingSameWay(int floor) {
            for (int j = 0; j < queues[floor].length; j++) {
                if (travelingUp) {
                    if (queues[floor][j] > floor) {
                        return true;
                    }
                } else {
                    if (queues[floor][j] < floor) {
                        return true;
                    }
                }
            }
            return false;
        }

        private void changeFloor(int floor) {
            this.floor = floor;
            floorHistory.add(floor);
        }
    }
}
