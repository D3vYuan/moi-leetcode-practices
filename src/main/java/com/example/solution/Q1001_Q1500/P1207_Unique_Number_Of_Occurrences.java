package com.example.solution.Q1001_Q1500;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P1207_Unique_Number_Of_Occurrences {
    /**
     * Reference:
     * https://leetcode.com/problems/unique-number-of-occurrences/editorial/?envType=daily-question&envId=2024-01-17
     * 
     * Strategy:
     * 1. Store the frequencies of elements in the array arr in the hash map freq.
     * 2. Iterate over the hash map freq and insert the frequencies of all unique
     * elements of array arr in the hash set freqSet.
     * 3. Return true if the size of hash set freqSet is equal to the size of hash
     * map freq, otherwise return false.
     */

    public boolean uniqueOccurrences(int[] arr) {
        // Store the frequency of elements in the unordered map.
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Store the frequency count of elements in the unordered set.
        Set<Integer> freqSet = new HashSet<>(freq.values());

        // If the set size is equal to the map size,
        // It implies frequency counts are unique.
        return freq.size() == freqSet.size();
    }
}
