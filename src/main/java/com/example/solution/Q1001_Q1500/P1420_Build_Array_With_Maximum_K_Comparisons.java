package com.example.solution.Q1001_Q1500;

import java.util.Arrays;

public class P1420_Build_Array_With_Maximum_K_Comparisons {
    /**
     * Reference:
     * https://leetcode.com/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons/?envType=daily-question&envId=2024-01-17
     * 
     */

    /**
     * Explanation:
     * 1. How many arrays of length n with values in the range [1, m] exist, such
     * that you will find exactly k new maximums when traversing from left to right?
     * a. How many elements have we placed so far? Suppose we add elements to the
     * array in order. We can represent this with an index i that indicates the
     * index of the next element we will place. For example, if the current array is
     * [1, 6, 4], the next element we will place is at i = 3.
     * b. The maximum element in the array. We can represent this with an integer
     * maxSoFar. In the example of [1, 6, 4], we have maxSoFar = 6.
     * c. How many remaining new maximums must we encounter before the end? We can
     * represent this with an integer remain. In the example of [1, 6, 4], both 1
     * and 6 are maximums, so if we need a total of x maximums, we have remain = x -
     * 2
     * 2. Base Case
     * a. If i == n, we have filled the array. The array is valid if remain == 0 and
     * we will return 1 in that case, or 0 otherwise.
     * b. If remain < 0, then we have placed too many new maximums. We should
     * immediately return 0 as it is impossible to form a valid array from this
     * point forward
     * 3. Sub Problem
     * a. The range of numbers we could choose from is [1, maxSoFar]. The size of
     * this range is maxSoFar - 1 + 1 = maxSoFar. After placing a number, the next
     * state is i + 1, maxSoFar, remain. We move to the next index, and maxSoFar and
     * remain are unchanged since we didn't place a new maximum. Thus, the total
     * possibilities is maxSoFar * dp(i + 1, maxSoFar, remain)
     * b. The range of numbers we could choose from is [maxSoFar + 1, m]. Let's say
     * that we choose a number num from this range. The state would be i + 1, num,
     * remain - 1. We move to the next index, maxSoFar is updated, and we placed a
     * new maximum. We need to try all possibilities in the range [maxSoFar + 1, m]
     * 
     * Strategy:
     * 1. Define a memoized function dp(i, maxSoFar, remain):
     * a. If i == n, return 1 if remain == 0, and 0 otherwise.
     * b. If remain < 0, return 0.
     * 2. Initialize ans as maxSoFar * dp(i + 1, maxSoFar, remain).
     * 3. Iterate num in the range [maxSoFar + 1, m]:
     * a. Add dp(i + 1, num, remain - 1) to ans.
     * 4. Return ans.
     * 5. Return dp(0, 0, k), the answer to the original problem
     */

    int[][][] memo;
    int MOD = (int) 1e9 + 7;

    public int dp(int i, int n, int m, int maxSoFar, int remain) {
        if (i == n) {
            if (remain == 0) {
                return 1;
            }

            return 0;
        }

        if (remain < 0) {
            return 0;
        }

        if (memo[i][maxSoFar][remain] != -1) {
            return memo[i][maxSoFar][remain];
        }

        int ans = 0;
        for (int num = 1; num <= maxSoFar; num++) {
            ans = (ans + dp(i + 1, n, m, maxSoFar, remain)) % MOD;
        }

        for (int num = maxSoFar + 1; num <= m; num++) {
            ans = (ans + dp(i + 1, n, m, num, remain - 1)) % MOD;
        }

        memo[i][maxSoFar][remain] = ans;
        return ans;
    }

    private int topDown(int n, int m, int k) {
        memo = new int[n][m + 1][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        return dp(0, n, m, 0, k);
    }

    /**
     * 
     * Strategy:
     * 1. Initialize a 3d array dp[n + 1][m + 1][k + 1].
     * 2. Set the base cases: dp[n][...][0] = 1.
     * 3. Iterate using the nested loops: i from n - 1 until 0, maxSoFar from m
     * until 0, remain from 0 until k:
     * a. Initialize ans = maxSoFar * dp[i + 1][maxSoFar][remain].
     * b. If remain > 0, iterate num from maxSoFar + 1 until m:
     * b1. Add dp[i + 1][num][remain - 1] to ans.
     * c. Set dp[i][maxSoFar][remain] = ans.
     * 4. Return dp[0][0][k], the answer to the original problem.
     */
    private int bottomUp(int n, int m, int k) {
        int[][][] dp = new int[n + 1][m + 1][k + 1];
        int MOD = (int) 1e9 + 7;

        for (int num = 0; num < dp[0].length; num++) {
            dp[n][num][0] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int maxSoFar = m; maxSoFar >= 0; maxSoFar--) {
                for (int remain = 0; remain <= k; remain++) {
                    int ans = 0;
                    for (int num = 1; num <= maxSoFar; num++) {
                        ans = (ans + dp[i + 1][maxSoFar][remain]) % MOD;
                    }

                    if (remain > 0) {
                        for (int num = maxSoFar + 1; num <= m; num++) {
                            ans = (ans + dp[i + 1][num][remain - 1]) % MOD;
                        }
                    }

                    dp[i][maxSoFar][remain] = ans;
                }
            }
        }

        return dp[0][0][k];
    }

    public int numOfArrays(int n, int m, int k) {
        return topDown(n, m, k);
        // return bottomUp(n, m, k);
    }
}
