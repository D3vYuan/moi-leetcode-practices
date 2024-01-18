package com.example.katana.Q001_Q500;

import java.util.Arrays;
import java.util.stream.Collectors;

public class P70_Climbing_Stairs {
    private int solve(int n, int[] dp) {
        if (n == 0) {
            return 1;
        }

        if (n < 0) {
            return 0;
        }

        if (dp[n] != 0) {
            return dp[n];
        }

        int totalWaysWith1Step = solve(n - 1, dp);
        int totalWaysWith2Step = solve(n - 2, dp);
        int totalWaysForN = totalWaysWith1Step + totalWaysWith2Step;
        dp[n] = totalWaysForN;

        return dp[n];
    }

    private int runBottomUp(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;

        return solve(n, dp);
    }

    private int runTopDown(int n) {
        int[] dp = new int[n + 1];

        if (n <= 2) {
            return n;
        }

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        String arrDesc = Arrays.stream(dp).mapToObj(String::valueOf).collect(Collectors.joining(","));
        System.out.println(String.format("Array: %s", arrDesc));
        return dp[n];
    }

    public int climbStairs(int n) {
        return runTopDown(n);
        // return runBottomUp(n);
    }
}
