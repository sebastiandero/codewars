package com.sebastiandero;

public class TenMinWalk {
    public static boolean isValid(char[] walk) {
        int xOffset = 0;
        int yOffset = 0;

        for (char direction : walk) {
            switch (direction) {
                case 'n':
                    yOffset++;
                    break;
                case 'e':
                    xOffset++;
                    break;
                case 's':
                    yOffset--;
                    break;
                case 'w':
                    xOffset--;
                    break;
            }
        }

        return walk.length == 10 && xOffset == 0 && yOffset == 0;
    }
}