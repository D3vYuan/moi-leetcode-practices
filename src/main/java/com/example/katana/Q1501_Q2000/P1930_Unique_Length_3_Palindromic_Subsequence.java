package com.example.katana.Q1501_Q2000;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class P1930_Unique_Length_3_Palindromic_Subsequence {
    public int countPalindromicSubsequence(String s) {
        Set<String> characters = Arrays.stream(s.split("")).collect(Collectors.toSet());

        int count = 0;

        for (String character : characters) {
            int firstIndex = s.indexOf(character);
            int lastIndex = s.lastIndexOf(character);
            // System.out.println(String.format("Character: %s from [%d ~ %d]", character,
            // firstIndex, lastIndex));
            String value = s.substring(firstIndex, lastIndex + 1);

            if (lastIndex > firstIndex) {
                String inBetween = s.substring(firstIndex + 1, lastIndex);
                // System.out.println(String.format("Inbetween: %s from %s", inBetween, value));

                if (inBetween.length() > 0) {
                    count += Arrays.stream(inBetween.split("")).collect(Collectors.toSet()).size();
                }
            }
        }

        return count;
    }
}
