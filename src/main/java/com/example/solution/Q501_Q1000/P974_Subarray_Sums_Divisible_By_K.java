package com.example.solution.Q501_Q1000;

public class P974_Subarray_Sums_Divisible_By_K {
    /**
     * Reference:
     * https://leetcode.com/problems/subarray-sums-divisible-by-k/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize an integer prefixMod = 0 to store the remainder when the sum of
     * the elements of a array till the current index when divided by k, and the
     * answer variable result = 0 to store the number of subarrays divisible by k.
     * 2. Initialize an array, modGroups[k] where modGroup[R] stores the number of
     * subarrays encountered with the sum of elements having a remainder R when
     * divided by k. Set modGroups[0] = 1.
     * 3. Iterate over all the elements of num.
     * a. For each index i, compute the prefix modulo as prefixMod = (prefixMod +
     * num[i] % k + k) % k. We take modulo twice in (prefixMod + num[i] % k + k) % k
     * to remove negative numbers since num[i] can be a negative number and the sum
     * prefixMod + nums[i] % k can turn out to be negative. To remove the negative
     * number we add k to make it positive and then takes its modulo again with k.\
     * b. Add the number of subarrays encountered till now that have the same
     * remainder to the result: result = result + modGroups[prefixMod].
     * c. In the end, we include the remainder of the subarray in the modGroups,
     * i.e., modGroups[prefixMod] = modGroups[prefixMod] + 1 for future matches.
     * 4. Return result.
     */

    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        int prefixMod = 0, result = 0;

        // There are k mod groups 0...k-1.
        int[] modGroups = new int[k];
        modGroups[0] = 1;

        for (int num : nums) {
            // Take modulo twice to avoid negative remainders.
            prefixMod = (prefixMod + num % k + k) % k;
            // Add the count of subarrays that have the same remainder as the current
            // one to cancel out the remainders.
            result += modGroups[prefixMod];
            modGroups[prefixMod]++;
        }

        return result;
    }
}
