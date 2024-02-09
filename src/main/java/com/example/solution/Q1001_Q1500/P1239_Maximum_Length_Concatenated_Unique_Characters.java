package com.example.solution.Q1001_Q1500;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P1239_Maximum_Length_Concatenated_Unique_Characters {
    /**
     * Reference:
     * https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/?envType=daily-question&envId=2024-01-19
     */
    public int maxLength(List<String> arr) {
        // Results initialized as a Set to prevent duplicates
        Set<Integer> results = new HashSet<>(Arrays.asList(0));
        int best = 0;

        // Check each string in arr and find the best length
        for (String word : arr) {
            best = Math.max(best, addWord(word, results));
        }

        return best;
    }

    private int addWord(String word, Set<Integer> results) {
        // Initialize an int to use as a character set
        int charBitset = 0;
        int best = 0;
        for (char c : word.toCharArray()) {
            // Define character mask for currrent char
            int mask = 1 << c - 'a';

            // Bitwise AND check using character mask
            // to see if char already found and if so, exit
            if ((charBitset & mask) > 0) {
                return 0;
            }

            // Mark char as seen in charBitSet
            charBitset += mask;
        }

        // If the initial bitset is already a known result,
        // then any possible new results will have already been found
        if (results.contains(charBitset + (word.length() << 26))) {
            return 0;
        }

        // Iterate through previous results only
        Set<Integer> newResults = new HashSet<>();
        for (Integer res : results) {
            // If the two bitsets overlap, skip to the next result
            if ((res & charBitset) > 0) {
                continue;
            }

            // Build the new entry with bit manipulation
            int newResLen = (res >> 26) + word.length();
            int newCharBitSet = (charBitset + res) & ((1 << 26) - 1);

            // Merge the two into one, add it to results,
            // and keep track of the longest so far
            newResults.add((newResLen << 26) + newCharBitSet);
            best = Math.max(best, newResLen);
        }

        results.addAll(newResults);
        return best;
    }
}