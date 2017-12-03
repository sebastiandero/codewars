package com.sebastiandero.kata;

class LongestConsec {

    public static String longestConsec(String[] strarr, int k) {
        String[] strings = strarr;
        int length = k;
        String firstLongestString = "";

        for(int i = 0; i <= strarr.length - k; i++) {
            String nextString = getNextConsecutiveString(strings, i, length);
            if (nextString.length() > firstLongestString.length()) {
                firstLongestString = nextString;
            }
        }

        return firstLongestString;
    }

    private static String getNextConsecutiveString(String[] strings, int lastIndex, int length) {
        StringBuilder nextString = new StringBuilder();
        for(int i = lastIndex; i < lastIndex + length; i++) {
            nextString.append(strings[i]);
        }
        return nextString.toString();
    }
}
