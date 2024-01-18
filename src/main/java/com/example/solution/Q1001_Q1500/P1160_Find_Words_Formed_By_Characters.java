package com.example.solution.Q1001_Q1500;

import java.util.HashMap;
import java.util.Map;

public class P1160_Find_Words_Formed_By_Characters {
    /**
     * Reference:
     * https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy:
     * 1. Create a hash map counts that records the frequency of every character in
     * chars.
     * 2. Initialize the answer ans = 0.
     * 3. Iterate over each word in words:
     * a. Create a hash map wordCount that records the frequency of every character
     * in words.
     * b. Set good = true.
     * c. Iterate over each key c in wordCount. Let freq = wordCount[c].
     * c1. If counts[c] < freq, set good = false and break from the loop.
     * d. If good = true, add the length of word to ans.
     * 4. Return ans.
     */
    public int countCharacters(String[] words, String chars) {
        Map<Character, Integer> counts = new HashMap();
        for (Character c : chars.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        int ans = 0;
        for (String word : words) {
            Map<Character, Integer> wordCount = new HashMap();
            for (Character c : word.toCharArray()) {
                wordCount.put(c, wordCount.getOrDefault(c, 0) + 1);
            }

            boolean good = true;
            for (Character c : wordCount.keySet()) {
                if (counts.getOrDefault(c, 0) < wordCount.get(c)) {
                    good = false;
                    break;
                }
            }

            if (good) {
                ans += word.length();
            }
        }

        return ans;
    }
}
