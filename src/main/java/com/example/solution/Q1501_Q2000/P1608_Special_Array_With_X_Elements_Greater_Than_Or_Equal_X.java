package com.example.solution.Q1501_Q2000;

public class P1608_Special_Array_With_X_Elements_Greater_Than_Or_Equal_X {
    /**
     * Reference: https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize an array freq with size N + 1 with all values as 0.
     * 2. Iterate over the array nums and store the frequency of each integer in the array freq. If the value nums[i] is greater than N store the frequency at index N.
     * 3. Initialize the variable numGreaterThanOrEqual to 0. This is the number of elements that are greater than or equal to the current element.
     * 4. Iterate over the values from N to 1 and for each value i:
     *  Add the value freq[i] to numGreaterThanOrEqual;
     *  If the value i is equal to the numGreaterThanOrEqual then return i
     * 5. Return -1.
     */

    public int specialArray(int[] nums) {
        int N = nums.length;
        int[] freq = new int[N + 1];

        for (int i = 0; i < N; i++) {
            freq[Math.min(N, nums[i])]++;
        }
        
        int numGreaterThanOrEqual = 0;
        for (int i = N; i >= 1; i--) {
            numGreaterThanOrEqual += freq[i];
            if (i == numGreaterThanOrEqual) {
                return i;
            }
        }
        
        return -1;
    }
}
