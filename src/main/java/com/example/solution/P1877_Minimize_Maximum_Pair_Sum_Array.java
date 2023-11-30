package com.example.solution;

import java.util.Arrays;

public class P1877_Minimize_Maximum_Pair_Sum_Array {
    /*
     * Reference:
     * https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/editorial/?
     * envType=daily-question&envId=2023-11-13
     * 
     * potential strategy to pair up the smallest integer with the greatest integer
     * and then second-smallest with the second-greatest and so on.
     * This method ensures we don't end up pairing the two greatest integers as we
     * did in the previous method.
     * 
     * Strategy
     * [1] Sort the array nums.
     * [2] Initialize the variable maxSum to 0.
     * [3] Iterate over the array nums from index 0 to nums.length() / 2 - 1.
     * [4] Get the sum of the current element and its corresponding pair nums[i] +
     * nums[nums.length() - 1 - i], and update maxSum if the sum is larger.
     * [5] Return maxSum.
     */
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);

        int maxSum = 0;
        for (int i = 0; i < nums.length / 2; i++) {
            maxSum = Math.max(maxSum, nums[i] + nums[nums.length - 1 - i]);
        }

        return maxSum;
    }
}
