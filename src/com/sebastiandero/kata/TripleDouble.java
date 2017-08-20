package com.sebastiandero.kata;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TripleDouble {

    public static int TripleDouble(long num1, long num2) {
        Pattern pattern1 = Pattern.compile("(000|111|222|333|444|555|666|777|888|999)");
        Pattern pattern2 = Pattern.compile("(00|11|22|33|44|55|66|77|88|99)");
        Matcher matcher1 = pattern1.matcher(String.valueOf(num1));
        Matcher matcher2 = pattern2.matcher(String.valueOf(num2));

        while (matcher1.find()) {
            String num1Match = matcher1.group(1);
            while (matcher2.find()) {
                String num2Match = matcher2.group(1);
                if (num1Match.charAt(0) == num2Match.charAt(0)) {
                    return 1;
                }
            }
        }

        return 0;
    }
}