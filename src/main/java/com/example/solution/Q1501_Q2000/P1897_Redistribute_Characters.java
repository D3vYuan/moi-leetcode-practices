package com.example.solution.Q1501_Q2000;

import java.util.HashMap;
import java.util.Map;

public class P1897_Redistribute_Characters {
    /**
     * Reference:
     * https://leetcode.com/problems/redistribute-characters-to-make-all-strings-equal/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation:
     * 1. We will start by collecting all the letters available for us to use. We
     * create a hash map counts, where counts[letter] tells us how many times letter
     * appears in the input. We iterate over every word in words, and for each word
     * we iterate over every character c and increment counts[c].
     * 2. Once we have calculated counts, we analyze each letter's frequency. Let's
     * say that the length of words is n. If a given letter has a frequency of val,
     * we need to allocate val / n copies to each string. This is only possible if
     * val / n is an integer, i.e. val is divisible by n. We can check if val is
     * divisible by n by taking the modulus. If val % n = 0, then val is divisible
     * by n.
     * 3. If a letter's frequency is divisible by n, we know we can allocate an
     * equal number of copies of this letter to every string.
     * 4. If every letter's frequency can be evenly allocated, we are guaranteed to
     * make equal strings and the overall task is possible.
     * 5. If ANY letter's frequency cannot be evenly allocated, the task is
     * impossible.
     * 
     * Strategy:
     * 1. Create a hash map counts.
     * 2. Iterate over each string word in words:
     * a. Iterate over each character c in word:
     * a1. Increment counts[c].
     * 3. Set n = words.length.
     * 4. Iterate over each value val of counts:
     * a. If val % n != 0, return false.
     * 5. Return true.
     */
    public boolean makeEqual(String[] words) {
        Map<Character, Integer> counts = new HashMap();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                counts.put(c, counts.getOrDefault(c, 0) + 1);
            }
        }

        int n = words.length;
        for (Character c : counts.keySet()) {
            int val = counts.get(c);
            if (val % n != 0) {
                return false;
            }
        }

        return true;
    }
}
