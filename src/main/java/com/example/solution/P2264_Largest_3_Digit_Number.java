package com.example.solution;

public class P2264_Largest_3_Digit_Number {
    private boolean isGoodInteger(String num) {
        if (num.length() != 3) {
            return false;
        }

        String[] numSplit = num.split("");
        String firstCharacter = numSplit[0];
        return firstCharacter == numSplit[1] && firstCharacter == numSplit[2];
    }

    public String largestGoodInteger(String num) {
        // Windows of 3
        String currentGoodInteger = "";
        for (int i = 0; i < num.length(); i += 3) {
            String subString = num.substring(i, Math.min(i + 3, num.length()));
            boolean isGood = isGoodInteger(subString);
            System.out.println(String.format("%s - %s", subString, isGood));
            if (isGood) {
                if (Integer.valueOf(subString) > Integer.valueOf(currentGoodInteger)) {
                    currentGoodInteger = subString;
                }
            }
        }
        return currentGoodInteger;
    }
}
