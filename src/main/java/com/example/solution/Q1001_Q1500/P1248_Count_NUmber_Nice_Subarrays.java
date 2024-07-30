package com.example.solution.Q1001_Q1500;

import java.util.HashMap;
import java.util.Map;

public class P1248_Count_NUmber_Nice_Subarrays {
    /**
     * Reference:
     * 
     * Strategy:
     * 1. Initialize integers currSum = 0,subarrays = 0 and a hashmap prefixSum.
     * 2. Initialize prefixSum[0] with 1 to account for the initial value of
     * currSum.
     * 3. Iterate over all the elements of nums:
     * a. Compute currSum as currSum = currSum + nums[i] % 2.
     * b. If currSum - k exists in the hashmap:
     * Increment the value of subarrays with prefixSum[currSum - k].
     * c. Increment prefixSum[currSum] by 1.
     * 4. Return subarrays.
     */

    public int numberOfSubarrays(int[] nums, int k) {
        int currSum = 0, subarrays = 0;
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(currSum, 1);

        for (int i = 0; i < nums.length; i++) {
            currSum += nums[i] % 2;
            // Find subarrays with sum k ending at i
            if (prefixSum.containsKey(currSum - k)) {
                subarrays = subarrays + prefixSum.get(currSum - k);
            }
            // Increment the current prefix sum in hashmap
            prefixSum.put(currSum, prefixSum.getOrDefault(currSum, 0) + 1);
        }

        return subarrays;
    }
}
