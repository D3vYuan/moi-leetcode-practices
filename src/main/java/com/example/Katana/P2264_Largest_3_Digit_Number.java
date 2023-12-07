package com.example.katana;

public class P2264_Largest_3_Digit_Number {
    private boolean isGoodInteger(String num) {
        if (num.length() != 3) {
            return false;
        }

        String[] numSplit = num.split("");
        System.out.println(String.format("1: %s | 2: %s | 3: %s", numSplit[0], numSplit[1], numSplit[2]));
        String firstCharacter = numSplit[0];
        return firstCharacter.equals(numSplit[1]) && firstCharacter.equals(numSplit[2]);
    }

    public String largestGoodInteger(String num) {
        // Windows of 3
        String currentGoodInteger = "";
        for (int i = 0; i < num.length(); i ++) {
            String subString = num.substring(i, Math.min(i + 3, num.length()));
            boolean isGood = isGoodInteger(subString);
            System.out.println(String.format("%s - %s", subString, isGood));
            if (isGood) {
                if (currentGoodInteger.length() == 0) {
                    currentGoodInteger = subString;
                } else if (Integer.valueOf(subString) > Integer.valueOf(currentGoodInteger)) {
                    currentGoodInteger = subString;
                }
            }
        }
        return currentGoodInteger;
    }
}
