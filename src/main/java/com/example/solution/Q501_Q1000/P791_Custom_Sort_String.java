package com.example.solution.Q501_Q1000;

import java.util.HashMap;
import java.util.Map;

public class P791_Custom_Sort_String {
    /**
     * Reference:
     * https://leetcode.com/problems/custom-sort-string/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a frequency table (here we use a Hashmap, but a frequency array
     * works too).
     * 2. Populate the frequency table by incrementing freq[letter] for each letter
     * in s.
     * 3. For each character of order, append to result the same frequency it
     * appears in s.
     * 4. Iterate through the frequency table to find any remaining letters of s not
     * in order, and append these letters to result.
     * 5. Return the resulting string.
     */
    public String customSortString(String order, String s) {
        // Create a frequency table
        Map<Character, Integer> freq = new HashMap<>();

        // Initialize frequencies of letters
        // freq[c] = frequency of char c in s
        int N = s.length();
        for (int i = 0; i < N; i++) {
            char letter = s.charAt(i);
            freq.put(letter, freq.getOrDefault(letter, 0) + 1);
        }

        // Iterate order string to append to result
        int K = order.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < K; i++) {
            char letter = order.charAt(i);
            while (freq.getOrDefault(letter, 0) > 0) {
                result.append(letter);
                freq.put(letter, freq.get(letter) - 1);
            }
        }

        // Iterate through freq and append remaining letters
        // This is necessary because some letters may not appear in `order`
        for (char letter : freq.keySet()) {
            int count = freq.get(letter);
            while (count > 0) {
                result.append(letter);
                count--;
            }
        }

        // Return the result
        return result.toString();
    }
}
