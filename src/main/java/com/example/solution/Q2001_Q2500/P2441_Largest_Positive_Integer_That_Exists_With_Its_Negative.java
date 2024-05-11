package com.example.solution.Q2001_Q2500;

import java.util.Arrays;

public class P2441_Largest_Positive_Integer_That_Exists_With_Its_Negative {
    /***
     * Reference:
     * https://leetcode.com/problems/largest-positive-integer-that-exists-with-its-negative/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. Sort the nums array in ascending order.
     * 2. Initialize lo to 0 and hi to the last index of the nums array.
     * 3. While lo is less than hi:
     * a. If -nums[lo] is equal to nums[hi], return nums[hi].
     * b. If -nums[lo] is greater than nums[hi], increment lo.
     * c. If -nums[lo] is less than nums[hi], decrement hi.
     * 4. If the loop completes without finding a matching pair, return -1.
     */
    public int findMaxK(int[] nums) {
        // Sort the input array in ascending order
        Arrays.sort(nums);

        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {

            // If the negation of the element at lo is equal to the element at hi
            if (-nums[lo] == nums[hi]) {
                return nums[hi];
            }

            // If the negation of element at lo is greater than element at hi
            else if (-nums[lo] > nums[hi]) {
                ++lo;
            }

            // If the negation of element at lo is smaller than element at hi
            else {
                --hi;
            }
        }
        return -1;
    }
}
