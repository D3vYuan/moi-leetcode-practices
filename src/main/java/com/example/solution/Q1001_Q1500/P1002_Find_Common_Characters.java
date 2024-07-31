package com.example.solution.Q1001_Q1500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P1002_Find_Common_Characters {
    /**
     * Reference: https://leetcode.com/problems/find-common-characters/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialization:
     * a. Create a list commonCharacterCounts of size 26, initialized to 0.
     * b. Create a list currentCharacterCounts of size 26, initialized to 0.
     * c. Create an empty list result.
     * 2. Iterate through words from left to right:
     * a. For the first string in words:
     *      For each character in the first string, increment the corresponding position in commonCharacterCounts.
     * b. For each subsequent word in words:
     *      Reset currentCharacterCounts to 0.
     *      For each character in the current word, increment the corresponding position in currentCharacterCounts.
     *      For each letter from 'a' to 'z':
     *          Update commonCharacterCounts at that letter to be the minimum of its current value and the value in currentCharacterCounts.
     * 3. Collect common characters:
     * a. For each letter from 'a' to 'z':
     *      For the number of times indicated by commonCharacterCounts at that letter, append the character corresponding to the letter to result.
     * 4. Return the result list.
     */

    public List<String> commonChars(String[] words) {
        int wordsSize = words.length;
        int[] commonCharacterCounts = new int[26];
        int[] currentCharacterCounts = new int[26];
        List<String> result = new ArrayList<>();

        // Initialize commonCharacterCounts with the characters from the first
        // word
        for (char ch : words[0].toCharArray()) {
            commonCharacterCounts[ch - 'a']++;
        }

        for (int i = 1; i < wordsSize; i++) {
            Arrays.fill(currentCharacterCounts, 0);

            // Count characters in the current word
            for (char ch : words[i].toCharArray()) {
                currentCharacterCounts[ch - 'a']++;
            }

            // Update the common character counts to keep the minimum counts
            for (int letter = 0; letter < 26; letter++) {
                commonCharacterCounts[letter] = Math.min(
                    commonCharacterCounts[letter],
                    currentCharacterCounts[letter]
                );
            }
        }

        // Collect the common characters based on the final counts
        for (int letter = 0; letter < 26; letter++) {
            for (
                int commonCount = 0;
                commonCount < commonCharacterCounts[letter];
                commonCount++
            ) {
                result.add(String.valueOf((char) (letter + 'a')));
            }
        }

        return result;
    }
}
