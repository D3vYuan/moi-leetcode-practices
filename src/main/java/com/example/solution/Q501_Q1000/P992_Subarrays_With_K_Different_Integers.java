package com.example.solution.Q501_Q1000;

import java.util.HashMap;
import java.util.Map;

public class P992_Subarrays_With_K_Different_Integers {
    /**
     * Reference:
     * https://leetcode.com/problems/subarrays-with-k-different-integers/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize an empty freqMap to store the frequency of elements in the
     * current window.
     * 2. Initialize left and totalCount to 0.
     * 3. Iterate through the nums array using the right pointer:
     * a. Increment the frequency of nums[right] in the freqMap.
     * b. While the size of freqMap (the number of distinct elements) is greater
     * than distinctK:
     * b1. Decrement the frequency of nums[left] in the freqMap.
     * b2. If the frequency of nums[left] becomes 0, remove it from the freqMap.
     * b3. Increment left to shrink the window.
     * c. Add right - left + 1 to totalCount. This counts the number of subarrays
     * ending at right with at most distinctK distinct elements.
     * 4. Return totalCount.
     */
    public int subarraysWithKDistinct(int[] nums, int k) {
        return slidingWindowAtMost(nums, k) - slidingWindowAtMost(nums, k - 1);
    }

    // Helper function to count the number of subarrays with at most k distinct
    // elements.
    private int slidingWindowAtMost(int[] nums, int distinctK) {
        // To store the occurrences of each element.
        Map<Integer, Integer> freqMap = new HashMap<>();
        int left = 0, totalCount = 0;

        // Right pointer of the sliding window iterates through the array.
        for (int right = 0; right < nums.length; right++) {
            freqMap.put(nums[right], freqMap.getOrDefault(nums[right], 0) + 1);

            // If the number of distinct elements in the window exceeds k,
            // we shrink the window from the left until we have at most k distinct elements.
            while (freqMap.size() > distinctK) {
                freqMap.put(nums[left], freqMap.get(nums[left]) - 1);
                if (freqMap.get(nums[left]) == 0) {
                    freqMap.remove(nums[left]);
                }
                left++;
            }

            // Update the total count by adding the length of the current subarray.
            totalCount += (right - left + 1);
        }
        return totalCount;
    }
}
