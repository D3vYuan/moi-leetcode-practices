package com.example.solution.Q001_Q500;

public class P343_Integer_Break {
    /**
     * Reference:
     * https://leetcode.com/problems/integer-break/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Explanation:
     * 1. we have an integer num and split it into two integers: i and num - i. The
     * highest product possible would be i * BEST, where BEST is the highest product
     * possible from splitting up num - i.
     * 2. We have the following base cases for this function:
     * a. If num == 1, then it isn't possible to split the number up, so we just
     * return 1
     * b. If num == 2, then it would be better to not split the number at all, since
     * the only possible split 1 * 1 is less than 2, so just return 2. The exact
     * same argument can be made for num == 3: the only possible split 1 * 2 is less
     * than 3 itself, so just return 3.
     * 3. Otherwise, we have two options:
     * a. Don't split the number up at all. We can initialize the answer as ans =
     * num.
     * b. Split the number. We can try all possible splits. Iterate i from 2 until
     * num. For each value of i, try to update ans with i * dp(num - i) if it is
     * larger.
     */

    public int integerBreak(int n) {
        return runTopDown(n);
        // runBottomUp(n);
    }

    /**
     * Strategy:
     * 1. If n <= 3, then return n - 1.
     * 2. Define a memoized function dp(num):
     * a. Base case: if num <= 3, then return num.
     * b. Initialize ans = num. This is the case of not splitting the number at all.
     * c. Iterate i from 2 until num:
     * c1. Try to update ans with i * dp(num - i) if it is larger.
     * d. Return ans.
     * 3. Return dp(n).
     * 
     */

    int[] memo;

    private int dp(int num) {
        if (num <= 3) {
            return num;
        }

        if (memo[num] != 0) {
            return memo[num];
        }

        int ans = num;
        for (int i = 2; i < num; i++) {
            ans = Math.max(ans, i * dp(num - i));
        }

        return memo[num] = ans;
    }

    private int runTopDown(int n) {
        if (n <= 3) {
            return n - 1;
        }

        memo = new int[n + 1];
        return dp(n);
    }

    /**
     * Strategy:
     * 1. If n <= 3, then return n - 1.
     * 2. Create an array dp of length n + 1.
     * 3. Set the base cases. For i = 1, 2, 3, set dp[i] = i.
     * 4. Iterate num from 4 to n:
     * a. Initialize ans = num. This is the case of not splitting the number at all.
     * b. Iterate i from 2 until num:
     * b1. Try to update ans with i * dp[num - i] if it is larger.
     * c. Set dp[num] = ans.
     * 5. Return dp[n].
     * 
     */
    private int runBottomUp(int n) {
        if (n <= 3) {
            return n - 1;
        }

        int[] dp = new int[n + 1];

        // Set base cases
        for (int i = 1; i <= 3; i++) {
            dp[i] = i;
        }

        for (int num = 4; num <= n; num++) {
            int ans = num;
            for (int i = 2; i < num; i++) {
                ans = Math.max(ans, i * dp[num - i]);
            }

            dp[num] = ans;
        }

        return dp[n];
    }
}
