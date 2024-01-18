package com.example.solution.Q1001_Q1500;

import java.util.Arrays;

public class P1464_Maximum_Product_Two_Elements {
    /**
     * Reference:
     * https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy:
     * 1.Sort nums in ascending order.
     * 2. Set x as the last element in nums and y as the second last element in
     * nums.
     * 3. Return (x - 1) * (y - 1).
     */
    public int maxProduct(int[] nums) {
        Arrays.sort(nums);
        int x = nums[nums.length - 1];
        int y = nums[nums.length - 2];
        return (x - 1) * (y - 1);
    }
}
