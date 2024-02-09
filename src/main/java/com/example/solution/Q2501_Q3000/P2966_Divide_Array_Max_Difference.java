package com.example.solution.Q2501_Q3000;

import java.util.Arrays;

public class P2966_Divide_Array_Max_Difference {
    /**
     * Reference:
     * https://leetcode.com/problems/divide-array-into-arrays-with-max-difference/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. Sort the given array nums in ascending order.
     * 2. Initialize an empty array ans to store the result, which will be a 2D
     * array containing arrays of size 3.
     * 3. Use a for loop to iterate through the sorted array nums with a step size
     * of 3. The loop variable i represents the starting index of each potential
     * array of size 3.
     * 4. For each potential array of size 3, check if the difference between the
     * third element (nums[i + 2]) and the first element (nums[i]) is greater than
     * k. If the difference exceeds k, the conditions are not satisfied and return
     * an empty array.
     * 5. If the difference condition is met for the current potential array, append
     * a new array to the result (ans). The new array consists of the three elements
     * at indices i, i + 1, and i + 2 in the sorted array.
     * 6. After processing all potential arrays, return the final 2D array ans
     * containing valid arrays of size 3.
     */
    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        int[][] ans = new int[nums.length / 3][3];
        for (int i = 0; i < nums.length; i += 3) {
            if (nums[i + 2] - nums[i] > k) {
                return new int[0][0];
            }
            ans[i / 3] = new int[] { nums[i], nums[i + 1], nums[i + 2] };
        }
        return ans;
    }

}
