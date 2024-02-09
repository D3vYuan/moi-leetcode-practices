package com.example.solution.Q001_Q500;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P451_Characters_By_Frequency {
    /**
     * Reference:
     * https://leetcode.com/problems/sort-characters-by-frequency/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. count how many times each character occurs in the String; the keys are
     * characters and the values are frequencies.
     * 2. Next, extract a copy of the keys from the HashMap and sort them by
     * frequency using a Comparator that looks at the HashMap values to make its
     * decisions.
     * 3. initialise a new StringBuilder and then iterate over the list of sorted
     * characters (sorted by frequency). Look up the values in the HashMap to know
     * how many of each character to append to the StringBuilder.
     */
    public String frequencySort(String s) {
        // Count up the occurances.
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        // Make a list of the keys, sorted by frequency.
        List<Character> characters = new ArrayList<>(counts.keySet());
        Collections.sort(characters, (a, b) -> counts.get(b) - counts.get(a));

        // Convert the counts into a string with a sb.
        StringBuilder sb = new StringBuilder();
        for (char c : characters) {
            int copies = counts.get(c);
            for (int i = 0; i < copies; i++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
