package com.example.solution.Q1501_Q2000;

import java.util.HashMap;
import java.util.Map;

public class P1915_Number_Of_Wonderful_Substrings {
    /**
     * Reference:
     * https://leetcode.com/problems/number-of-wonderful-substrings/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Create a frequency table or map. Add the mask 000 to account for the empty
     * prefix.
     * 2. Initialize a mask int variable to 000.
     * 3. For each character in word, flip the corresponding bit in mask.
     * 4. Add the frequency of mask to the answer.
     * 5. Increment the value associated with key mask by one.
     * 6. Iterate through each possible character that appears an odd number of
     * times, and add the frequency of mask ^ (1 << odd_c), where ^ is the XOR
     * function.
     * 7. Return the result when all letters are processed.
     */
    public long wonderfulSubstrings(String word) {
        int N = word.length();

        // Create the frequency map
        // Key = bitmask, Value = frequency of bitmask key
        Map<Integer, Integer> freq = new HashMap<>();

        // The empty prefix can be the smaller prefix, which is handled like this
        freq.put(0, 1);

        int mask = 0;
        long res = 0L;
        for (int i = 0; i < N; i++) {
            char c = word.charAt(i);
            int bit = c - 'a';

            // Flip the parity of the c-th bit in the running prefix mask
            mask ^= (1 << bit);

            // Count smaller prefixes that create substrings with no odd occurring letters
            res += freq.getOrDefault(mask, 0);

            // Increment value associated with mask by 1
            freq.put(mask, freq.getOrDefault(mask, 0) + 1);

            // Loop through every possible letter that can appear an odd number of times in
            // a substring
            for (int odd_c = 0; odd_c < 10; odd_c++) {
                res += freq.getOrDefault(mask ^ (1 << odd_c), 0);
            }
        }
        return res;
    }
}
