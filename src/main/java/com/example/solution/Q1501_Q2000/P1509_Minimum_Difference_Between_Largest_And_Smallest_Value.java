package com.example.solution.Q1501_Q2000;

import java.util.Arrays;

public class P1509_Minimum_Difference_Between_Largest_And_Smallest_Value {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialization:
     * a. Determine the size of the array nums and store it in numsSize.
     * b. If numsSize is less than or equal to 4, return 0.
     * c. Sort the array nums.
     * d. Initialize minDiff to a very large number to store the minimum difference.
     * 2. Iterate through the first four elements of the sorted array:
     * a. For each index left from 0 to 3:
     * Calculate the corresponding right index as numsSize - 4 + left.
     * Compute the difference between the elements at indices right and left in the
     * sorted array.
     * Update minDiff with the minimum value between minDiff and the computed
     * difference.
     * 3. Return minDiff, which stores the minimum difference between the largest
     * and smallest values after removing up to three elements.
     */

    public int minDifference(int[] nums) {
        int numsSize = nums.length;

        // If the array has 4 or fewer elements, return 0
        if (numsSize <= 4)
            return 0;

        Arrays.sort(nums);

        int minDiff = Integer.MAX_VALUE;

        // Four scenarios to compute the minimum difference
        for (int left = 0, right = numsSize - 4; left < 4; left++, right++) {
            minDiff = Math.min(minDiff, nums[right] - nums[left]);
        }

        return minDiff;
    }
}
