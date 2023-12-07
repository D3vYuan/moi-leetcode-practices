package com.example.solution;

import java.util.Arrays;

public class P1887_Reduction_Operations_Make_Element_Equal {
    /**
     * Reference:
     * https://leetcode.com/problems/reduction-operations-to-make-the-array-elements-equal/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy
     * 1. Sort nums.
     * 2. Initialize the answer ans = 0 and the number of up steps taken so far up =
     * 0.
     * 3. Iterate i over the indices of nums, starting with i = 1:
     * a. Check if nums[i] != nums[i - 1]. If so, increment up.
     * b. Add up to ans.
     * 4. Return ans.
     * 
     */
    public int reductionOperations(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        int up = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i -1]){
                up ++;
            }
            ans += up;
        }

        return ans;
    }
}
