package com.example.solution.Q501_Q1000;

public class P930_Binary_Subarrays_With_Sum {
    /**
     * Reference:
     * https://leetcode.com/problems/binary-subarrays-with-sum/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize variables start, prefixZeros, currentSum, and totalCount to 0.
     * 2. Iterate through the array using the end variable as the end index of the
     * sliding window.
     * a. Add the current element to the currentSum.
     * b. Enter a while loop to shrink the window from the left side if the sum
     * exceeds the goal or if the element at the start of the window is 0.
     * b1. Inside the while loop, check if the element at the start of the window is
     * 1. If it is, reset the prefixZeros count to 0. Otherwise, increment the
     * prefixZeros count.
     * b2. Then subtract the element at the start of the window from the currentSum
     * and increment the start pointer to move the window.
     * c. If the currentSum is equal to the goal, increment the totalCount by 1 plus
     * the prefixZeros count.
     * 3. Finally, return the totalCount.
     * 
     */
    public int numSubarraysWithSum(int[] nums, int goal) {
        int start = 0;
        int prefixZeros = 0;
        int currentSum = 0;
        int totalCount = 0;

        // Loop through the array using end pointer
        for (int end = 0; end < nums.length; end++) {
            // Add current element to the sum
            currentSum += nums[end];

            // Slide the window while condition is met
            while (start < end && (nums[start] == 0 || currentSum > goal)) {
                if (nums[start] == 1) {
                    prefixZeros = 0;
                } else {
                    prefixZeros++;
                }

                currentSum -= nums[start];
                start++;
            }

            // Count subarrays when window sum matches the goal
            if (currentSum == goal) {
                totalCount += 1 + prefixZeros;
            }
        }

        return totalCount;
    }
}
