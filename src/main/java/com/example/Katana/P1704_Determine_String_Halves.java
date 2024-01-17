package com.example.katana;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P1704_Determine_String_Halves {
    Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    private int countStringVowels(String s) {
        int count = 0;
        char[] sCharacters = s.toCharArray();
        for (int i = 0; i < sCharacters.length; i++) {
            char currentCharacter = sCharacters[i];
            if (vowels.contains(currentCharacter)) {
                count += 1;
            }
        }
        return count;
    }

    public boolean halvesAreAlike(String s) {
        // Map<String, Integer> firstHalfCount = new HashMap<>();
        // Map<String, Integer> secondHalfCount = new HashMap<>();

        String firstHalf = s.substring(0, s.length() / 2);
        String secondHalf = s.substring(s.length() / 2);

        int firstHalfSum = countStringVowels(firstHalf);
        int secondHalfSum = countStringVowels(secondHalf);

        // int firstHalfSum = firstHalfCount.values().stream().reduce(0, (a, b) -> a +
        // b);
        // int secondHalfSum = secondHalfCount.values().stream().reduce(0, (a, b) -> a +
        // b);

        System.out.println(
                String.format("Comparing: %s (%d) vs %s (%d)", firstHalf, firstHalfSum, secondHalf, secondHalfSum));

        return firstHalfSum == secondHalfSum;
    }
}
