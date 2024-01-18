package com.example.solution.Q2001_Q2500;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P2009_Minimum_Operations_To_Make_Array_Continuous {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy:
     * 1. Set n = nums.length and the answer ans = n.
     * 2. Remove duplicates from nums and then sort it. We will call this new array
     * newNums.
     * 3. Initialize j = 0 and iterate i over the indices of newNums:
     * a. While newNums[j] is within our range (less than newNums[i] + n), increment
     * j.
     * b. Calculate count = j - i, the number of elements already in our range.
     * c. Update ans with n - count if it is smaller.
     * 4. Return ans.
     */

    public int minOperations(int[] nums) {
        int n = nums.length;
        int ans = n;

        Set<Integer> unique = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            unique.add(nums[i]);
        }

        int[] newNums = new int[unique.size()];
        int index = 0;

        for (int num : unique) {
            newNums[index++] = num;
        }

        Arrays.sort(newNums);
        int j = 0;
        for (int i = 0; i < newNums.length; i++) {
            // sliding window to see if the next value is within the boundary
            while (j < newNums.length && newNums[j] < newNums[i] + n) {
                j++;
            }

            int count = j - i;
            ans = Math.min(ans, n - count);
        }

        return ans;
    }
}
