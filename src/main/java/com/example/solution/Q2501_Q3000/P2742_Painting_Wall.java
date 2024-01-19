package com.example.solution.Q2501_Q3000;

public class P2742_Painting_Wall {
    /**
     * Reference:
     * https://leetcode.com/problems/painting-the-walls/editorial/?envType=daily-question&envId=2024-01-19
     */

    /**
     * Explanation:
     * 1. Let's say that we have the paid painter paint the ith wall. It costs us
     * cost[i] money. The paid painter will paint 1 wall and be occupied for time[i]
     * time. While the paid painter is occupied, the free
     * painter can paint time[i] walls (since the free painter paints one wall per
     * unit of time). Overall, we spent cost[i] money to paint 1 + time[i] walls.
     * 2. The ith item costs cost[i] and paints 1+time[i] walls. We need to paint n
     * walls while minimizing the total cost.
     * 3. We have two base cases here.
     * a. If remain <= 0, we have painted all the walls. We can return 0.
     * b. If i == n, we have run out of walls to put the paid painter on and the
     * task is impossible. We return a large value like infinity.
     * 4. For the ith wall, we have two options. We can either hire the paid painter
     * for this wall or not hire them.
     * a. If we hire them, as mentioned above, we spend cost[i] and paint 1 +
     * time[i] walls. Then, we move to the next index. Thus, the cost of this option
     * is cost[i] + dp(i + 1, remain - 1 - time[i]).
     * b. If we don't hire them, we simply move to the next index. The cost of this
     * option is dp(i + 1, remain).
     * 
     * Strategy:
     * 1. Let n = cost.length.
     * 2. Define a memoized function dp(i, remain):
     * a. If remain <= 0, then return 0.
     * b. If i == n, then return a very large value.
     * c. Set paint = cost[i] + dp(i + 1, remain - 1 - time[i]).
     * d. Set dontPaint = dp(i + 1, remain).
     * e. Return min(paint, dontPaint).
     * 3. Return dp(0, n).
     */

    private int[][] memo;
    private int n;

    private int dp(int i, int remain, int[] cost, int[] time) {
        if (remain <= 0) {
            return 0;
        }

        if (i == n) {
            return (int) 1e9;
        }

        if (memo[i][remain] != 0) {
            return memo[i][remain];
        }

        int paint = cost[i] + dp(i + 1, remain - 1 - time[i], cost, time);
        int dontPaint = dp(i + 1, remain, cost, time);
        memo[i][remain] = Math.min(paint, dontPaint);
        return memo[i][remain];
    }

    private int topDown(int[] cost, int[] time) {
        this.n = cost.length;
        memo = new int[n][n + 1];
        return dp(0, n, cost, time);
    }

    /**
     * Strategy:
     * 1. Let n = cost.length.
     * 2. Create a dp table of size (n + 1) * (n + 1) with values initialized to 0.
     * 3. Set the base cases:
     * a. Set all values inside dp[n] to large values.
     * b. The other base case is implicitly set since we initialized dp with 0.
     * 4. Iterate i from n - 1 until 0:
     * a. Iterate remain from 1 until n:
     * a1. Set paint = cost[i] + dp[i + 1][max(0, remain - 1 - time[i])].
     * a2. Set dontPaint = dp[i + 1][remain].
     * a3. Set dp[i][remain] = min(paint, dontPaint).
     * 5. Return dp[0][n].
     */
    private int bottomUp(int[] cost, int[] time) {
        int n = cost.length;
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            dp[n][i] = (int) 1e9;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int remain = 1; remain <= n; remain++) {
                int paint = cost[i] + dp[i + 1][Math.max(0, remain - 1 - time[i])];
                int dontPaint = dp[i + 1][remain];
                dp[i][remain] = Math.min(paint, dontPaint);
            }
        }

        return dp[0][n];
    }

    public int paintWalls(int[] cost, int[] time) {
        return topDown(cost, time);
        // return bottomUp(cost, time);
    }
}
