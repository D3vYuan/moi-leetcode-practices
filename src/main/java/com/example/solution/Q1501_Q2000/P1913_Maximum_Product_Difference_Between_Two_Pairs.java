package com.example.solution.Q1501_Q2000;

import java.util.Arrays;

public class P1913_Maximum_Product_Difference_Between_Two_Pairs {
    /**
     * Reference:
     * https://leetcode.com/problems/maximum-product-difference-between-two-pairs/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy:
     * 1. Sort nums in ascending order.
     * 2. Return nums[nums.length - 1] * nums[nums.length - 2] - nums[0] * nums[1].
     */
    public int maxProductDifference(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length - 1] * nums[nums.length - 2] - nums[0] * nums[1];
    }
}
