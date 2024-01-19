package com.example.solution.Q1001_Q1500;

import java.util.Arrays;

public class P1269_Number_Of_Way_Stay_In_Same_Place {

    /**
     * Reference:
     * https://leetcode.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/?envType=daily-question&envId=2024-01-19
     * 
     */

    /**
     * Explanation:
     * 1. We start at 0, need to make steps moves, and want to end up back at 0.
     * Without loss of generality, let's say we are currently at curr on the number
     * line and need to make remain more moves. We have three options:
     * a. Don't move. We will stay at curr and need to make remain - 1 more moves.
     * b. Move left. We can only do this if curr > 0. We move to curr - 1 and need
     * to make remain - 1 more moves.
     * c. Move right. We can only do this if curr < arrLen - 1. We move to curr + 1
     * and need to make remain - 1 more moves.
     * 2. If we want to go back to 0, we can only do so through one of these three
     * options. In other words, the number of ways to return to 0 from the current
     * state is equivalent to the sum of the number of ways to return to 0 in the
     * next three options. We have the following transitions:
     * a. dp(curr, remain) += dp(curr, remain - 1)
     * b. dp(curr, remain) += dp(curr - 1, remain - 1) if curr > 0
     * c. dp(curr, remain) += dp(curr + 1, remain - 1) if curr < arrLen - 1
     * 3. If remain = 0, we have no more moves to make. If curr = 0, then we have
     * found a way to accomplish our task, so we return 1. Otherwise, we return 0
     * 
     * Strategy:
     * 1. Create a memoized function dp(curr, remain):
     * a. If remain == 0:
     * a1. Return 1 if curr == 0, and 0 otherwise.
     * b. Initialize ans = dp(curr, remain - 1).
     * c. If curr > 0, add dp(curr - 1, remain - 1) to ans.
     * d. If curr < arrLen - 1, add dp(curr + 1, remain - 1) to ans.
     * e. Return ans.
     * 2. Set arrLen = min(arrLen, steps).
     * 3. Return dp(0, steps).
     */
    int memo[][];
    int MOD = (int) 1e9 + 7;
    int arrLen;

    private int dp(int current, int remain) {
        if (remain == 0) {
            return current == 0 ? 1 : 0;
        }

        if (memo[current][remain] != -1) {
            return memo[current][remain];
        }

        // Stay Put if Current == 0
        int ans = dp(current, remain - 1);

        // Move current towards 0
        if (current > 0) {
            ans = (ans + dp(current - 1, remain - 1)) % MOD;
        }

        // Move current towards 0
        if (current < arrLen - 1) {
            ans = (ans + dp(current + 1, remain - 1)) % MOD;
        }

        return memo[current][remain] = ans;
    }

    private int topDown(int steps, int arrLen) {
        arrLen = Math.min(arrLen, steps);
        this.arrLen = arrLen;
        memo = new int[arrLen][steps + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dp(0, steps);
    }

    /**
     * Strategy:
     * 1. Set arrLen = min(arrLen, steps).
     * 2. Create an array dp[arrLen][steps + 1].
     * 3. Set dp[0][0] = 1, the base case.
     * 4. Iterate remain from 1 to steps:
     * a. Iterate curr from arrLen - 1 to 0:
     * a1. Initialize ans = dp[curr][remain - 1].
     * a2. If curr > 0, add dp[curr - 1][remain - 1] to ans.
     * a3. If curr < arrLen - 1, add dp[curr + 1][remain - 1] to ans.
     * a4. Set dp[curr][remain] = ans.
     * 5. Return dp[0][steps].
     */
    private int bottomUp(int steps, int arrLen) {
        int MOD = (int) 1e9 + 7;
        arrLen = Math.min(arrLen, steps);
        int[][] dp = new int[arrLen][steps + 1];
        dp[0][0] = 1;

        for (int remain = 1; remain <= steps; remain++) {
            for (int curr = arrLen - 1; curr >= 0; curr--) {
                int ans = dp[curr][remain - 1];

                if (curr > 0) {
                    ans = (ans + dp[curr - 1][remain - 1]) % MOD;
                }

                if (curr < arrLen - 1) {
                    ans = (ans + dp[curr + 1][remain - 1]) % MOD;
                }

                dp[curr][remain] = ans;
            }
        }

        return dp[0][steps];
    }

    public int numWays(int steps, int arrLen) {
        return topDown(steps, arrLen);
        // return bottomUp(steps, arrLen);
    }
}
