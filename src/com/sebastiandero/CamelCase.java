package com.sebastiandero;

public class CamelCase {

    public static String toCamelCase(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", " ");
        s = s.replaceAll("\\s+", " ");

        //s = s.substring(0, 1).toLowerCase() + s.substring(1);

        for(int i = 1; i < s.length() - 1; i++) {
            if (s.substring(i - 1, i).matches(" ")) {
                s = s.substring(0, i - 1) + s.substring(i, i + 1).toUpperCase() + s.substring(i + 1);
            }
        }
        return s;
    }
}
