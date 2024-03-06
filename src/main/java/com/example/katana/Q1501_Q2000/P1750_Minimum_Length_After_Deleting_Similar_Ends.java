package com.example.katana.Q1501_Q2000;

public class P1750_Minimum_Length_After_Deleting_Similar_Ends {
    public int minimumLength(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            char currentCharacter = s.charAt(start);

            boolean endMatchFound = false;
            while (s.charAt(end) == currentCharacter && end > 0) {
                endMatchFound = true;
                System.out.println(String.format("Found: Matching %s @%d", currentCharacter, end));
                end--;
            }

            if (!endMatchFound) {
                // No match
                break;
            }

            while (endMatchFound && s.charAt(start + 1) == currentCharacter && start < end) {
                System.out.println(String.format("Found: Matching %s @%d", currentCharacter, start + 1));
                start++;
            }
            start++;
        }
        String remainingValue = start <= end ? s.substring(start, end + 1) : "";
        System.out.println(String.format("Original: %s | After: %s", s, remainingValue));
        return remainingValue.length();
    }
}