package com.example.solution.Q1501_Q2000;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P1636_Sort_Array_By_Increasing_Frequency {
    /**
     * Reference: https://leetcode.com/problems/sort-array-by-increasing-frequency/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize an unordered map freq to store the frequency of each integer in the input array nums.
     * 2. Traverse through each integer num in the array nums.
     * 3. Increase the count of num in the freq map using freq[num]++.
     * 4. Sort the array nums using the sort function with a custom comparator:
     * a. Compare two integers a and b based on their frequencies stored in the freq map:
     *      If freq[a] (frequency of a) equals freq[b] (frequency of b), then:
     *      Return a > b to ensure that in case of tie-in frequency, larger values come first (decreasing order).
     *      Otherwise, return freq[a] < freq[b] to sort by frequency in increasing order.
     * b. Return the sorted nums array, which now reflects the integers sorted primarily by frequency in ascending order, and by value in descending order when frequencies are tied.
     */

    public int[] frequencySort(int[] nums) {
         Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Java's Arrays.sort method doesn't directly support
        // sorting primitive arrays (int[]) with a lambda comparator.
        // We can convert the primitive int into Integer objects
        // to get around this limitation.
        Integer[] numsObj = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsObj[i] = nums[i];
        }

        Arrays.sort(numsObj, (a, b) -> {
            if (freq.get(a).equals(freq.get(b))) {
                return Integer.compare(b, a);
            }
            return Integer.compare(freq.get(a), freq.get(b));
        });

        // Convert numsObj back to a primitive array to match
        // return type.
        for (int i = 0; i < nums.length; i++) {
            nums[i] = numsObj[i];
        }

        return nums;
    }
}
