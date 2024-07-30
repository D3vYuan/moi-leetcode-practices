package com.example.solution.Q2501_Q3000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class P2579_Number_Of_Beautiful_Subsets {
    /**
     * Reference:
     * https://leetcode.com/problems/the-number-of-beautiful-subsets/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. beautifulSubsets Method:
     * Initialize totalCount to 1.
     * Initialize a map called freqMap to track the frequency of elements based on
     * their remainder when divided by k.
     * Calculate frequencies for each element in nums and update freqMap.
     * Iterate over each remainder group in freqMap.
     * Convert the frequency map of each remainder group into an array of pairs
     * (subsets) containing the element and its frequency.
     * Initialize an array called counts with size equal to the number of subsets,
     * filled with -1 for memoization purposes.
     * Call the countBeautifulSubsets method with parameters subsets,
     * subsets.size(), k, 0, and counts.
     * Multipy totalCount with the result of countBeautifulSubsets for each
     * remainder group.
     * Return totalCount - 1.
     * 
     * 2. countBeautifulSubsets Method:
     * It takes five parameters: subsets (the array of pairs containing element
     * frequencies), numSubsets (the number of subsets), difference (the given
     * difference), i (the index of the current subset being considered), and counts
     * (an array to store counts of subsets for memoization).
     * Base case: If i is equal to numSubsets, return 1 (representing a subset of
     * size 1).
     * If the count for the current subset has already been calculated (stored in
     * counts[i]), return it.
     * Calculate subsets where the current subset is not taken by recursively
     * calling countBeautifulSubsets with i + 1.
     * Calculate subsets where the current subset is taken by multiplying (1 <<
     * subsets[i].second) - 1 (which represents all possible combinations of taking
     * elements from the current subset).
     * If the next number has a difference of 'difference', calculate subsets
     * accordingly by recursively calling countBeautifulSubsets; otherwise, move to
     * the next subset.
     * Store the calculated count in counts[i] for memoization.
     * Return the sum of subsets where the current subset is taken and not taken.
     */

    public int beautifulSubsets(int[] nums, int k) {
        int totalCount = 1;
        Map<Integer, Map<Integer, Integer>> freqMap = new TreeMap<>();

        // Calculate frequencies based on remainder
        for (int num : nums) {
            int remainder = num % k;
            Map<Integer, Integer> fr = freqMap.getOrDefault(
                    remainder,
                    new TreeMap<>());
            fr.put(num, fr.getOrDefault(num, 0) + 1);
            freqMap.put(remainder, fr);
        }

        // Calculate subsets for each remainder group
        for (Map.Entry<Integer, Map<Integer, Integer>> entry : freqMap.entrySet()) {
            List<Pair<Integer, Integer>> subsets = new ArrayList<>();
            for (Map.Entry<Integer, Integer> subset : entry
                    .getValue()
                    .entrySet()) {
                subsets.add(new ImmutablePair<>(subset.getKey(), subset.getValue()));
            }
            int[] counts = new int[subsets.size()]; // Store counts of subsets for memoization
            Arrays.fill(counts, -1);
            totalCount *= countBeautifulSubsets(subsets, subsets.size(), k, 0, counts);
        }
        return totalCount - 1;
    }

    private int countBeautifulSubsets(
            List<Pair<Integer, Integer>> subsets,
            int numSubsets,
            int difference,
            int i,
            int[] counts) {
        // Base case: Return 1 for a subset of size 1
        if (i == numSubsets) {
            return 1;
        }

        // If the count is already calculated, return it
        if (counts[i] != -1) {
            return counts[i];
        }

        // Calculate subsets where the current subset is not taken
        int skip = countBeautifulSubsets(
                subsets,
                numSubsets,
                difference,
                i + 1,
                counts);

        // Calculate subsets where the current subset is taken
        int take = (1 << subsets.get(i).getValue()) - 1; // take the current subset

        // If the next number has a difference of 'difference',
        // calculate subsets accordingly
        if (i + 1 < numSubsets &&
                subsets.get(i + 1).getKey() - subsets.get(i).getKey() == difference) {
            take *= countBeautifulSubsets(
                    subsets,
                    numSubsets,
                    difference,
                    i + 2,
                    counts);
        } else {
            take *= countBeautifulSubsets(
                    subsets,
                    numSubsets,
                    difference,
                    i + 1,
                    counts);
        }

        return counts[i] = skip + take; // Store and return total count of subsets
    }
}
