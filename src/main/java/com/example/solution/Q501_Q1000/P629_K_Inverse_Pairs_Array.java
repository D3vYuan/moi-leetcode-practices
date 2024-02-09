package com.example.solution.Q501_Q1000;

public class P629_K_Inverse_Pairs_Array {
    /**
     * Reference:
     * https://leetcode.com/problems/k-inverse-pairs-array/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Explanation:
     * 1. recursive formula for finding the number of arrangements with exactly k
     * inverse pairs as follows. Let's say count(i,j) represents the number of
     * arrangements with i elements and exactly j inverse pairs.
     * a. If n=0, no inverse pairs exist. Thus, count(0,k)=0.
     * b. If k=0, only one arrangement is possible, which is all numbers sorted in
     * ascending order. Thus, count(n,0)=1.
     * c. Otherwise, count(n,k)=∑i=0min(k,n−1)count(n−1,k−i)
     * 
     * Strategy:
     * 1.
     */
    public int kInversePairs(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (j == 0)
                    dp[i][j] = 1;
                else {
                    for (int p = 0; p <= Math.min(j, i - 1); p++)
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - p]) % 1000000007;
                }
            }
        }
        return dp[n][k];
    }
}
