package com.example.solution.Q2501_Q3000;

import java.util.HashMap;
import java.util.Map;

public class P2958_Length_Of_Longest_Subarray_Most_K_Frequency {
    /**
     * Reference:
     * https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize variables n to store the length of the input array nums,
     * frequency as a Counter to keep track of the frequency of elements, start to
     * mark the start index of the subarray, and chars_with_freq_over_k to count the
     * number of elements with frequency exceeding k.
     * 2. Iterate through the array nums using a sliding window approach, with start
     * and end pointers to define the current subarray.
     * 3. Increment the frequency of the element at index end in the frequency
     * Counter.
     * 4. If the frequency of the element at index end becomes equal to k + 1,
     * increment chars_with_freq_over_k to track the count of elements exceeding
     * frequency k.
     * 5. If there are elements with frequency exceeding k:
     * a. Decrement the frequency of the element at index start in the frequency
     * counter as it moves out of the current window.
     * b. If the frequency of the element at index start becomes equal to k,
     * decrement chars_with_freq_over_k as it no longer exceeds frequency k.
     * c. Increment the start pointer to move the window forward.
     * 6. Continue the process until the entire array is traversed.
     * 7. Return the length of the longest good subarray, which is calculated by
     * subtracting the start index from the total length of the array.
     */
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> frequency = new HashMap();
        int start = 0;
        int charsWithFreqOverK = 0;

        for (int end = 0; end < n; end++) {
            frequency.put(nums[end], frequency.getOrDefault(nums[end], 0) + 1);
            if (frequency.get(nums[end]) == k + 1) {
                charsWithFreqOverK++;
            }
            if (charsWithFreqOverK > 0) {
                frequency.put(nums[start], frequency.get(nums[start]) - 1);
                if (frequency.get(nums[start]) == k) {
                    charsWithFreqOverK--;
                }
                start++;
            }
        }
        return n - start;
    }
}
