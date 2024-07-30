package com.example.solution.Q501_Q1000;

import java.util.Deque;
import java.util.LinkedList;

public class P995_Minimum_Number_Of_K_Consecutive_Bit_Flips {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize n with nums.size().
     * 2. Create a deque flipQueue to keep track of flips.
     * 3. Initialize flipped to 0, representing the current flip state.
     * 4. Initialize result to 0, representing the total number of flips.
     * 5. Iterate through the nums vector from index 0 to n - 1:
     * a. If the current index i is greater than or equal to k:
     * a1. XOR flipped with the front element of flipQueue.
     * a2. Remove the front element from flipQueue.
     * b. If flipped == nums[i] (the current bit needs to be flipped):
     * b1. If i + k > n, return -1 (flipping the window extends beyond the array
     * length).
     * b2. Push 1 to flipQueue.
     * b3. XOR flipped with 1 (toggle the flipped state).
     * b4. Increment result.
     * c. Else:
     * c1. Push 0 to flipQueue.
     * 6. Return result.
     */

    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length; // Length of the input array
        Deque<Integer> flipQueue = new LinkedList<>(); // Queue to keep track of flips
        int flipped = 0; // Current flip state
        int result = 0; // Total number of flips

        for (int i = 0; i < n; i++) {
            // Remove the effect of the oldest flip if it's out of the current window
            if (i >= k) {
                flipped ^= flipQueue.poll();
            }

            // If the current bit is 0 (i.e., it needs to be flipped)
            if (flipped == nums[i]) {
                // If we cannot flip a subarray starting at index i
                if (i + k > n) {
                    return -1;
                }
                // Add a flip at this position
                flipQueue.offer(1);
                flipped ^= 1; // Toggle the flipped state
                result += 1; // Increment the flip count
            } else {
                flipQueue.offer(0);
            }
        }

        return result;
    }
}
