package com.example.solution.Q1501_Q2000;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P1657_Determine_Strings_Close {
    /**
     * Reference:
     * https://leetcode.com/problems/determine-if-two-strings-are-close/?envType=daily-question&envId=2024-01-15
     * 
     * Explanation:
     * 1. Condition 1: Every character that exists in word1 must exist in word2 as
     * well, irrespective of the frequency.
     * 2. Condition 2: The frequency of all the characters is always the same. In
     * the above example for string uua, regardless of the operation, following
     * condition always holds:
     * a. There exists 1 character that occurs once (frequency = 1) and 1 character
     * that occurs twice (frequency = 2)
     * 
     * Strategy:
     * 1. Initialize hashmaps word1Map and word2Map for strings word1 and word2
     * respectively.
     * 2. Iterate over each word and build its hashmap where the key is the
     * individual character of the word and value is the frequency of that
     * character.
     * 3. To check if characters in word1 and word2 are the same, we must check if
     * the key values of hashmaps word1Map and word2Map are the same.
     * 4. Now, to check the occurrence, we must sort the values of both hashmaps in
     * increasing order and check for equality.
     */
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        Map<Character, Integer> word1Map = new HashMap<>();
        Map<Character, Integer> word2Map = new HashMap<>();
        for (char c : word1.toCharArray()) {
            word1Map.put(c, word1Map.getOrDefault(c, 0) + 1);
        }
        for (char c : word2.toCharArray()) {
            word2Map.put(c, word2Map.getOrDefault(c, 0) + 1);
        }
        if (!word1Map.keySet().equals(word2Map.keySet())) {
            return false;
        }
        List<Integer> word1FrequencyList = new ArrayList<>(word1Map.values());
        List<Integer> word2FrequencyList = new ArrayList<>(word2Map.values());
        Collections.sort(word1FrequencyList);
        Collections.sort(word2FrequencyList);
        return word1FrequencyList.equals(word2FrequencyList);
    }
}
