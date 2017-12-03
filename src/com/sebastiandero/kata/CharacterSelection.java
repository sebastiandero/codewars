package com.sebastiandero.kata;

public class CharacterSelection {

    public static String[] superStreetFighterize(String[][] fighters, int[] position, String[] moves) {
        String[] fightersForMoves = new String[moves.length];

        for(int i = 0; i < moves.length; i++) {
            String move = moves[i];
            fightersForMoves[i] = doMove(fighters, move, position);
        }

        return fightersForMoves;
    }

    private static String doMove(String[][] fighters, String move, int[] position) {
        moveCursor(fighters, move, position);
        return getFighter(fighters, position);
    }

    private static void moveCursor(String[][] fighters, String move, int[] position) {
        switch (move.toLowerCase()) {
            case "up":
                if (position[0] - 1 > -1) {
                    if (!getFighter(fighters, new int[] {position[0] - 1, position[1]}).trim().isEmpty()) {
                        position[0]--;
                    }
                }
                break;
            case "down":
                if (position[0] + 1 < fighters.length) {
                    if (!getFighter(fighters, new int[] {position[0] + 1, position[1]}).trim().isEmpty()) {
                        position[0]++;
                    }
                }
                break;
            case "left":
                position[1]--;
                while (getFighter(fighters, position).trim().isEmpty()) {
                    position[1]--;
                    if (position[1] < 0) {
                        position[1] = fighters[0].length - 1;
                    }
                }
                break;
            case "right":
                position[1]++;
                while (getFighter(fighters, position).trim().isEmpty()) {
                    position[1]++;
                    if (position[1] >= fighters[0].length) {
                        position[1] = 0;
                    }
                }
                break;
        }
    }

    private static String getFighter(String[][] fighters, int[] position) {
        if (position[0] < 0 || position[1] < 0) {
            return "";
        }
        if (position[0] > fighters.length - 1 || position[1] > fighters[0].length - 1) {
            return "";
        }
        return fighters[position[0]][position[1]];
    }
}
