package com.example.solution.Q1001_Q1500;

import java.util.Comparator;
import java.util.PriorityQueue;

public class P1438_Longest_Continuous_Subarray {
    /**
     * Reference:
     * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialization:
     * a. Initialize two heaps, maxHeap and minHeap.
     * b. Initialize left to 0 to represent the start of the sliding window.
     * c. Initialize maxLength to 0 to store the length of the longest valid
     * subarray.
     * 2. Iterate through the array nums from left to right using a variable right:
     * a. For each element nums[right]:
     * Add nums[right] and its index to both maxHeap and minHeap:
     * Check if the current window exceeds the limit:
     * While the absolute difference between the maximum value in maxHeap and the
     * minimum value in minHeap is greater than limit:
     * Move the left pointer to the right to exclude the element with the smaller
     * index between the smallest and largest values:
     * Set left to the index of the element with the smaller index between maxHeap
     * and minHeap, plus 1.
     * Remove elements from the heaps that are outside the current window:
     * While the index of the top element in maxHeap is less than left:
     * Remove the top element from maxHeap.
     * While the index of the top element in minHeap is less than left:
     * Remove the top element from minHeap.
     * Update maxLength:
     * Set maxLength to the maximum of maxLength and the length of the current
     * window, (right - left + 1).
     * 3. Return maxLength which stores the length of the longest valid subarray.
     */

    public int longestSubarray(int[] nums, int limit) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> b[0] - a[0]);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(a -> a[0]));

        int left = 0, maxLength = 0;

        for (int right = 0; right < nums.length; ++right) {
            maxHeap.offer(new int[] { nums[right], right });
            minHeap.offer(new int[] { nums[right], right });

            // Check if the absolute difference between the maximum and minimum values in
            // the current window exceeds the limit
            while (maxHeap.peek()[0] - minHeap.peek()[0] > limit) {
                // Move the left pointer to the right until the condition is satisfied.
                // This ensures we remove the element causing the violation
                left = Math.min(maxHeap.peek()[1], minHeap.peek()[1]) + 1;

                // Remove elements from the heaps that are outside the current window
                while (maxHeap.peek()[1] < left) {
                    maxHeap.poll();
                }
                while (minHeap.peek()[1] < left) {
                    minHeap.poll();
                }
            }

            // Update maxLength with the length of the current valid window
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
