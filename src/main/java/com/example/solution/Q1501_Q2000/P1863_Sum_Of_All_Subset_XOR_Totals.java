package com.example.solution.Q1501_Q2000;

public class P1863_Sum_Of_All_Subset_XOR_Totals {
    /**
     * Reference:
     * https://leetcode.com/problems/sum-of-all-subset-xor-totals/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Declare a recursive function XORSum that calculates the sum of the subset
     * XOR totals using backtracking. The parameters are nums, index, and
     * currentXOR.
     * a. Base case: index equals the size of nums. The current subset is complete.
     * Return currentXOR.
     * b. Calculate the sum of the subset XOR totals when the current element
     * nums[i] is added to the current subset. Save the result of XORSum with the
     * next element and currentXOR ^ nums[index] as withElement.
     * c. Calculate the sum of the subset XOR totals when the current element
     * nums[i] is not added to the current subset. Save the result of XORSum with
     * the next element and currentXOR as withoutElement.
     * d. Return the sum of withElement and withoutElement, which is the sum of the
     * subset XOR totals.
     * 2. Return the result of XORSum with nums. The initial index and initial
     * currentXOR are both 0
     */

    public int subsetXORSum(int[] nums) {
        return XORSum(nums, 0, 0);
    }

    private int XORSum(int[] nums, int index, int currentXOR) {
        // Return currentXOR when all elements in nums are already considered
        if (index == nums.length)
            return currentXOR;

        // Calculate sum of subset xor with current element
        int withElement = XORSum(nums, index + 1, currentXOR ^ nums[index]);

        // Calculate sum of subset xor without current element
        int withoutElement = XORSum(nums, index + 1, currentXOR);

        // Return sum of xor totals
        return withElement + withoutElement;
    }
}
