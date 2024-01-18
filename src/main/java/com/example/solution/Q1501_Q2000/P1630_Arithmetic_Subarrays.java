package com.example.solution.Q1501_Q2000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P1630_Arithmetic_Subarrays {
    /**
     * Reference:
     * https://leetcode.com/problems/arithmetic-subarrays/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy
     * 1. Define check(arr):
     * a. Sort arr.
     * b. Initialize diff = arr[1] - arr[0].
     * c. Iterate i over the indices of arr, starting from 2:
     * c1. If arr[i] - arr[i - 1] != diff, return false.
     * d. Return true.
     * 2. Initialize the answer ans.
     * 3. Iterate i over the indices of l:
     * a. Create arr as the subarray of nums from indices l[i] to r[i].
     * b. check(arr) to ans.
     * 4. Return ans.
     */

    public Boolean check(int[] arr) {
        Arrays.sort(arr);
        int diff = arr[1] - arr[0];

        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != diff) {
                return false;
            }
        }
        return true;
    }

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            int[] arr = new int[r[i] - l[i] + 1];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = nums[l[i] + j];
            }

            ans.add(check(arr));
        }

        return ans;
    }
}
