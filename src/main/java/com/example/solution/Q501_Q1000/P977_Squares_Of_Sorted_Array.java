package com.example.solution.Q501_Q1000;

public class P977_Squares_Of_Sorted_Array {
    /**
     * Reference:
     * https://leetcode.com/problems/squares-of-a-sorted-array/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. We can use two pointers to read the positive and negative parts of the
     * array - one pointer j in the positive direction, and another i in the
     * negative direction.
     * 2. Now that we are reading two increasing arrays (the squares of the
     * elements), we can merge these arrays together using a two-pointer technique.
     */

    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0;
        int right = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            int square;
            if (Math.abs(nums[left]) < Math.abs(nums[right])) {
                square = nums[right];
                right--;
            } else {
                square = nums[left];
                left++;
            }
            result[i] = square * square;
        }
        return result;
    }
}
