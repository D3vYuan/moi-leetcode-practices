package com.example.solution.Q001_Q500;

public class P70_Climbing_Stairs {
    /**
     * Reference:
     * 1.
     * https://leetcode.com/problems/climbing-stairs/editorial/?envType=daily-question&envId=2024-01-17
     * 2. https://en.wikipedia.org/wiki/Fibonacci_sequence#Closed-form_expression
     * 
     * Explanation:
     * 1. One can reach ith step in one of the two ways:
     * a. Taking a single step from (i−1)th step.
     * b. Taking a step of 222 from (i−2)th step.
     * 2. The total number of ways to reach ith is equal to sum of ways of reaching
     * (i−1)th step and ways of reaching (i−2)th step
     * 3. dp[i] denotes the number of ways to reach on ith step:
     * dp[i] = dp[i−1] + dp[i−2]
     */

    private int byDynamicProgramming(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    private int byFormula(int n) {
        double sqrt5 = Math.sqrt(5);
        double phi = (1 + sqrt5) / 2;
        double psi = (1 - sqrt5) / 2;
        return (int) ((Math.pow(phi, n + 1) - Math.pow(psi, n + 1)) / sqrt5);
    }

    public int climbStairs(int n) {
        return byDynamicProgramming(n);
    }
}
