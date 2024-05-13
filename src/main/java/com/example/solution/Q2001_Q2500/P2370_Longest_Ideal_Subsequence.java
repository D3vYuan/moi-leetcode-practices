package com.example.solution.Q2001_Q2500;

import java.util.Arrays;

public class P2370_Longest_Ideal_Subsequence {
    /**
     * Reference:
     * https://leetcode.com/problems/longest-ideal-subsequence/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a dp table with N rows and 26 columns, and set the default
     * values to -1.
     * 2. Create the dfs method that passes i, c, dp, s, and k as parameters. Note
     * that dp and s should be passed by reference. Steps 3-7 describe the
     * implementation of the dfs method.
     * 3. If dp[i][c] is not equal to -1, return the memoized value stored in
     * dp[i][c].
     * 4. Otherwise, set dp[i][c] to 1 if c == (s[i] - 'a'), and 0 otherwise.
     * 5. If the current state is not a base case (i>0i > 0i>0), check the option of
     * not including si in this ideal subsequence.
     * 6. If c == (s[i] - 'a'), check all transistions to previous letters p such
     * that |c - p| ≤ k.
     * 7. Return dp[i][c] to end the recursive call.
     * 8. Find the maximum of dp[N-1][c] for all c from 0 to 25, and return this
     * value as the answer.
     */
    public int longestIdealString(String s, int k) {
        int N = s.length();

        // Initialize all dp values to -1 to indicate non-visited states
        int[][] dp = new int[N][26];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        // Find the maximum dp[N-1][c] and return the result
        int res = 0;
        for (int c = 0; c < 26; c++) {
            res = Math.max(res, dfs(N - 1, c, dp, s, k));
        }
        return res;
    }

    public int dfs(int i, int c, int[][] dp, String s, int k) {
        // Memoized value
        if (dp[i][c] != -1) {
            return dp[i][c];
        }

        // State is not visited yet
        dp[i][c] = 0;
        boolean match = c == (s.charAt(i) - 'a');
        if (match) {
            dp[i][c] = 1;
        }

        // Non base case handling
        if (i > 0) {
            dp[i][c] = dfs(i - 1, c, dp, s, k);
            if (match) {
                for (int p = 0; p < 26; p++) {
                    if (Math.abs(c - p) <= k) {
                        dp[i][c] = Math.max(dp[i][c], dfs(i - 1, p, dp, s, k) + 1);
                    }
                }
            }
        }
        return dp[i][c];
    }
}
