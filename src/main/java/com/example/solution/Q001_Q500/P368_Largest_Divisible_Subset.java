package com.example.solution.Q001_Q500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class P368_Largest_Divisible_Subset {

    /**
     * Reference:
     * https://leetcode.com/problems/largest-divisible-subset/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Explanation:
     * 1. Corollary I: For any value that can be divided by the largest element in
     * the divisible subset, by adding the new value into the subset, one can form
     * another divisible subset, i.e. for all h, if h % G == 0, then [E, F, G, h]
     * forms a new divisible subset.
     * 2. Corollary II: For all values that can divide the smallest element in the
     * subset, by adding the new value into the subset, one can form another
     * divisible subset, i.e. for all d, if E % d == 0, then [d, E, F, G] forms a
     * new divisible subset.
     */

    HashMap<Integer, List<Integer>> _eds = new HashMap<Integer, List<Integer>>();
    int[] nums = {};

    private List<Integer> solve(Integer i) {
        // memoization
        if (this._eds.containsKey(i))
            return this._eds.get(i);

        List<Integer> maxSubset = new ArrayList<>();
        // Find the largest divisible subset of previous elements.
        for (int k = 0; k < i; ++k) {
            if (this.nums[i] % this.nums[k] == 0) {
                List<Integer> subset = solve(k);
                if (maxSubset.size() < subset.size())
                    maxSubset = subset;
            }
        }
        // Extend the found subset with the element itself.
        List<Integer> newEntry = new ArrayList<>();
        newEntry.addAll(maxSubset);
        newEntry.add(this.nums[i]);

        // update the cached values
        this._eds.put(i, newEntry);
        return newEntry;
    }

    private List<Integer> runTopDown(int[] nums) {
        // Test case with empty set.
        int n = nums.length;
        if (n == 0)
            return new ArrayList<>();

        // Container to keep the largest divisible subset
        // that ends with each of the nums
        // key: the index of the element
        this._eds = new HashMap<Integer, List<Integer>>();

        /* Sort the original list in ascending order. */
        Arrays.sort(nums);
        this.nums = nums;

        List<Integer> maxSubset = new ArrayList<>();
        /*
         * Calculate the values of all EDS(X_i),
         * while keeping track of the largest one as the result value.
         */
        for (int i = 0; i < n; ++i) {
            List<Integer> subset = solve(i);
            if (maxSubset.size() < subset.size())
                maxSubset = subset;
        }

        // return the largest one
        return maxSubset;
    }

    private List<Integer> runBottomUp(int[] nums) {
        // Test case with empty set.
        int n = nums.length;
        if (n == 0)
            return new ArrayList<>();

        // Container to keep the size of the largest divisible subset
        // that ends with each of the nums.
        Integer[] dp = new Integer[n];

        /* Sort the original list in ascending order. */
        Arrays.sort(nums);

        Integer maxSubsetSize = -1, maxSubsetIndex = -1;

        /* Calculate the rest of EDS(X_i) */
        for (int i = 0; i < n; ++i) {
            Integer subsetSize = 0;

            // Find the size of the largest divisible subset.
            for (int k = 0; k < i; ++k)
                if (nums[i] % nums[k] == 0 && subsetSize < dp[k])
                    subsetSize = dp[k];

            // Extend the found subset with the element itself.
            dp[i] = subsetSize + 1;

            // We reuse this loop to obtain the largest subset size
            // in order to prepare for the reconstruction of subset.
            if (maxSubsetSize < dp[i]) {
                maxSubsetSize = dp[i];
                maxSubsetIndex = i;
            }
        }

        /* Reconstruct the largest divisible subset */
        LinkedList<Integer> subset = new LinkedList<>();
        Integer currSize = maxSubsetSize;
        Integer currTail = nums[maxSubsetIndex];
        for (int i = maxSubsetIndex; i >= 0; --i) {
            if (currSize == 0)
                break;

            if (currTail % nums[i] == 0 && currSize == dp[i]) {
                subset.addFirst(nums[i]);
                currTail = nums[i];
                currSize -= 1;
            }
        }

        return subset;
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        return runTopDown(nums);
        // return runBottomUp(nums);
    }
}
