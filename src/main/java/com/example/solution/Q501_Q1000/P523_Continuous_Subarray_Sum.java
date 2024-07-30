package com.example.solution.Q501_Q1000;

import java.util.HashMap;

public class P523_Continuous_Subarray_Sum {
    /**
     * Reference:
     * https://leetcode.com/problems/continuous-subarray-sum/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize an integer prefixMod = 0 and a hashmap modSeen. Initialize
     * modSeen[0] with -1 to account for the initial value of prefixMod.
     * 2. Iterate over all the elements of nums:
     * a. Compute the prefixMod as prefixMod = (prefixMod + nums[i]) % k.
     * b. If prefixMod exists in the hashmap:
     * If the size of the longest subarray with modulo k is at least 2.
     * Return true.
     * c. If prefixMod doesn't exist in the hashmap:
     * Set modSeen[prefixMod] = i.
     * 3. Return false.
     */

    public boolean checkSubarraySum(int[] nums, int k) {
        int prefixMod = 0;
        HashMap<Integer, Integer> modSeen = new HashMap<>();
        modSeen.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            prefixMod = (prefixMod + nums[i]) % k;

            if (modSeen.containsKey(prefixMod)) {
                // ensures that the size of subarray is at least 2
                if (i - modSeen.get(prefixMod) > 1) {
                    return true;
                }
            } else {
                // mark the value of prefixMod with the current index.
                modSeen.put(prefixMod, i);
            }
        }

        return false;
    }
}
