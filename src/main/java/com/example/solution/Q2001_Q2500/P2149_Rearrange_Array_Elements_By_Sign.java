package com.example.solution.Q2001_Q2500;

public class P2149_Rearrange_Array_Elements_By_Sign {
    /**
     * Reference:
     * https://leetcode.com/problems/rearrange-array-elements-by-sign/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize n to the size of nums. Initialize ans array of size n.
     * 2. Initialize two integers posIndex and negIndex with 0 and 1 respectively.
     * 3. Traverse nums from the start. Note that 0 won't be in the array according
     * to the constraints.
     * i. If the current integer is positive, set ans[posIndex] equal to it.
     * Increment posIndex by 2.
     * ii. If the current integer is negative, set ans[negIndex] equal to it.
     * Increment negIndex by 2.
     * 4. Once nums is fully traversed, return ans.
     * 
     */
    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;

        // Initializing an answer array of size n
        int[] ans = new int[n];

        // Initializing two pointers to track even and
        // odd indices for positive and negative integers respectively
        int posIndex = 0, negIndex = 1;

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                // Placing the positive integer at the
                // desired index in ans and incrementing posIndex by 2
                ans[posIndex] = nums[i];
                posIndex += 2;
            } else {
                // Placing the negative integer at the
                // desired index in ans and incrementing negIndex by 2
                ans[negIndex] = nums[i];
                negIndex += 2;
            }
        }

        return ans;
    }
}
