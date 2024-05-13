package com.example.solution.Q2501_Q3000;

public class P2997_Minimum_NUmber_Of_Operations_Make_Array_XOR_Equal_K {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-number-of-operations-to-make-array-xor-equal-to-k/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize the variable finalXor to 0.
     * 2. Iterate over the elements in the array nums and find the XOR of each
     * element with the variable finalXor.
     * 3. Return the number of set bits in the variable finalXor.
     */
    public int minOperations(int[] nums, int k) {
        int finalXor = 0;
        for (int n : nums) {
            finalXor = finalXor ^ n;
        }

        return Integer.bitCount(finalXor ^ k);
    }
}
