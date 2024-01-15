package com.example.solution;

import java.util.Arrays;

public class P300_Longest_Increasing_Subsequence {
    /**
     * Reference:
     * https://leetcode.com/problems/longest-increasing-subsequence/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation:
     * 1. First, we need some function or array that represents the answer to the
     * problem from a given state - function/array named "dp". For this problem,
     * let's say that we have an array dp. As just stated, this array needs to
     * represent the answer to the problem for a given state, so let's say that
     * dp[i] represents the length of the longest increasing subsequence that ends
     * with the ith element. The "state" is one-dimensional since it can be
     * represented with only one variable - the index i.
     * 2. Second, we need a way to transition between states, such as dp[5] and
     * dp[7]. This is called a recurrence relation and can sometimes be tricky to
     * figure out. Let's say we know dp[0], dp[1], and dp[2]. How can we find dp[3]
     * given this information? Well, since dp[2] represents the length of the
     * longest increasing subsequence that ends with nums[2], if nums[3] > nums[2],
     * then we can simply take the subsequence ending at i = 2 and append nums[3] to
     * it, increasing the length by 1. The same can be said for nums[0] and nums[1]
     * if nums[3] is larger. Of course, we should try to maximize dp[3], so we need
     * to check all 3. Formally, the recurrence relation is: dp[i] = max(dp[j] + 1)
     * for all j where nums[j] < nums[i] and j < i.
     * 3. The third component is the simplest: we need a base case. For this
     * problem, we can initialize every element of dp to 1, since every element on
     * its own is technically an increasing subsequence.
     * 
     * Strategy:
     * 1. Initialize an array dp with length nums.length and all elements equal to
     * 1. dp[i] represents the length of the longest increasing subsequence that
     * ends with the element at index i.
     * 2. Iterate from i = 1 to i = nums.length - 1. At each iteration, use a second
     * for loop to iterate from j = 0 to j = i - 1 (all the elements before i). For
     * each element before i, check if that element is smaller than nums[i]. If so,
     * set dp[i] = max(dp[i], dp[j] + 1).
     * 3. Return the max value from dp.
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];

        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int longest = 0;
        for (int c : dp) {
            longest = Math.max(longest, c);
        }
        return longest;
    }
}
