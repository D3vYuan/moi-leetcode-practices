package com.example.solution.Q001_Q500;

import java.util.HashMap;
import java.util.Map;

public class P409_Longest_Palindrome {
    /**
     * Reference:
     * https://leetcode.com/problems/longest-palindrome/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a map frequencyMap to store the frequency of each character.
     * 2. Count the frequency of each character in s.
     * 3. Initialize variables:
     * a. res to store the length of the longest palindrome.
     * b. hasOddFrequency flag to check whether a character with odd frequency
     * exists.
     * 4. Loop through the frequencies freq of each character:
     * a. If freq is even, add it to res.
     * b. If the freq is odd, add freq-1 to res and set hasOddFrequency to true.
     * 5. If hasOddFrequency is true, return res+1, otherwise, return res.
     */

    public int longestPalindrome(String s) {
        // Map to store frequency of occurrence of each character
        Map<Character, Integer> frequencyMap = new HashMap<>();
        // Count frequencies
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        int res = 0;
        boolean hasOddFrequency = false;
        for (int freq : frequencyMap.values()) {
            // Check is the frequency is even
            if ((freq % 2) == 0) {
                res += freq;
            } else {
                // If the frequency is odd, one occurrence of the
                // character will remain without a match
                res += freq - 1;
                hasOddFrequency = true;
            }
        }
        // If hasOddFrequency is true, we have at least one unmatched
        // character to make the center of an odd length palindrome.
        if (hasOddFrequency)
            return res + 1;

        return res;
    }
}
