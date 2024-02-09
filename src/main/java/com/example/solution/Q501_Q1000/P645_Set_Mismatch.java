package com.example.solution.Q501_Q1000;

import java.util.Arrays;

public class P645_Set_Mismatch {
    /**
     * Reference:
     * https://leetcode.com/problems/set-mismatch/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. sort the given nums array.
     * 2. The numbers which are equal will always lie together.
     * 3. We can easily identify the missing number by checking if every two
     * consecutive elements in the sorted nums array are just one count apart or
     * not.
     */
    public int[] findErrorNums(int[] nums) {
        Arrays.sort(nums);
        int dup = -1, missing = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1])
                dup = nums[i];
            else if (nums[i] > nums[i - 1] + 1)
                missing = nums[i - 1] + 1;
        }
        return new int[] { dup, nums[nums.length - 1] != nums.length ? nums.length : missing };
    }
}
