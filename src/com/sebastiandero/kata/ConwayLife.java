package com.sebastiandero.kata;

public class ConwayLife {

    private static int[][] cells;

    public static int[][] getGeneration(int[][] cells, int generations) {

        for (int i = 0; i < generations; i++) {
            cells = padArray(cells);
            ConwayLife.cells = cells;
            int[][] newCells = new int[cells.length][cells[0].length];
            for (int y = 0; y < cells.length; y++) {
                for (int x = 0; x < cells[y].length; x++) {
                    newCells[y][x] = handleCell(cells[y][x], x, y);
                }
            }
            cells = trimArray(newCells);
        }
        return cells;
    }

    private static int[][] padArray(int[][] cells) {
        int[][] newArr = new int[cells.length + 2][cells[0].length + 2];
        for (int y = 1; y < newArr.length - 1; y++) {
            System.arraycopy(cells[y - 1], 0, newArr[y], 1, newArr[y].length - 1 - 1);
        }
        return newArr;
    }


    private static int[][] trimArray(int[][] cells) {
        int trimTopCount = getTrimTopCount(cells);
        int trimBottomCount = getTrimBottomCount(cells);
        int trimLeftCount = getTrimLeftCount(cells);
        int trimRightCount = getTrimRightCount(cells);
        int newX = 0;
        int newY = 1;
        if (trimRightCount + trimLeftCount < cells[0].length) {
            newX = cells[0].length - (trimRightCount + trimLeftCount);
        }
        if (trimTopCount + trimBottomCount < cells.length) {
            newY = cells.length - (trimTopCount + trimBottomCount);
        }
        int[][] newArr = new int[newY][newX];
        for (int y = 0; y < newArr.length; y++) {
            for (int x = 0; x < newArr[y].length; x++) {
                newArr[y][x] = cells[y + trimTopCount][x + trimLeftCount];
            }
        }
        return newArr;
    }

    private static int getTrimRightCount(int[][] cells) {
        int trimRightCount = 0;
        for (int x = cells[0].length - 1; x >= 0; x--) {
            boolean allDead = true;
            for (int y = cells.length - 1; y >= 0; y--) {
                if (cells[y][x] == 1) {
                    allDead = false;
                    break;
                }
            }

            if (allDead) {
                trimRightCount++;
            } else {
                break;
            }
        }
        return trimRightCount;
    }

    private static int getTrimLeftCount(int[][] cells) {
        int trimLeftCount = 0;
        for (int x = 0; x < cells[0].length; x++) {
            boolean allDead = true;
            for (int[] row : cells) {
                if (row[x] == 1) {
                    allDead = false;
                    break;
                }
            }
            if (allDead) {
                trimLeftCount++;
            } else {
                break;
            }
        }
        return trimLeftCount;
    }

    private static int getTrimBottomCount(int[][] cells) {
        int trimBottomCount = 0;
        for (int y = cells.length - 1; y >= 0; y--) {
            if (allColumnsDead(cells[y])) {
                trimBottomCount++;
            } else {
                break;
            }
        }
        return trimBottomCount;
    }

    private static int getTrimTopCount(int[][] cells) {
        int trimTopCount = 0;
        for (int[] row : cells) {
            if (allColumnsDead(row)) {
                trimTopCount++;
            } else {
                break;
            }
        }
        return trimTopCount;
    }

    private static boolean allColumnsDead(int[] row) {
        boolean allDead = true;
        for (int cell : row) {
            if (cell == 1) {
                allDead = false;
                break;
            }
        }
        return allDead;
    }

    private static int handleCell(int state, int x, int y) {
        byte liveNeighbors = getLiveNeighbors(x, y);
        if (state == 0 && liveNeighbors == 3) {
            return 1;
        }
        if (state == 1) {
            if (liveNeighbors < 2) {
                return 0;
            }
            if (liveNeighbors > 3) {
                return 0;
            }
        }
        return state;
    }

    private static byte getLiveNeighbors(int xCenter, int yCenter) {
        byte alive = 0;
        for (int y = yCenter - 1; y <= yCenter + 1; y++) {
            for (int x = xCenter - 1; x <= xCenter + 1; x++) {
                if (y == yCenter && x == xCenter) {
                    continue;
                }
                if (y >= 0 && y < cells.length && x >= 0 && x < cells[y].length) {
                    alive += cells[y][x];
                }
            }
        }
        return alive;
    }

    private static String asString(int[][] cells) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                int cell = cells[y][x];
                if (cell == 0) {
                    stringBuilder.append(".");
                } else {
                    stringBuilder.append("#");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
