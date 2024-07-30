package com.example.solution.Q001_Q500;

public class P330_Patching_Array {
    /**
     * Reference:
     * https://leetcode.com/problems/patching-array/?envType=daily-question&envId=2024-05-05
     * 
     * Explanation:
     * 1. Suppose miss is the smallest missing number, then we know that [1, miss)
     * (left-closed, right-open) is already covered . In order to cover miss, we
     * have to add something smaller than or equal to miss. Otherwise, there is no
     * way we can cover it.
     * 2. For example, you have any array nums = [1,2,3,8] and n = 16. The numbers
     * already covered is in the ranges [1, 6] and [8, 14]. In other words, 7, 15,
     * 16 are missing. If you add patches larger than 7, then 7 is still missing.
     * 3. Suppose the number we added is x then, the ranges [1, miss) and [x, x +
     * miss) are both covered. And since we know that x <= miss, the two ranges will
     * cover the range [1, x + miss). We want to choose x as large as possible so
     * that the range can cover as large as possible. Therefore, the best option is
     * x = miss.
     * 
     * Strategy:
     * 1. Initialize the range [1, miss) = [1, 1) = empty
     * 2. While n is not covered yet
     * a. if the current element nums[i] is less than or equal to miss
     * a1. extends the range to [1, miss + nums[i])
     * a2. increase i by 1
     * b. otherwise
     * b1. patch the array with miss, extends the range to [1, miss + miss)
     * b2. increase the number of patches
     * c. Return the number of patches
     */

    public int minPatches(int[] nums, int n) {
        int patches = 0, i = 0;
        long miss = 1; // use long to avoid integer overflow error
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) // miss is covered
                miss += nums[i++];
            else { // patch miss to the array
                miss += miss;
                patches++; // increase the answer
            }
        }
        return patches;
    }
}