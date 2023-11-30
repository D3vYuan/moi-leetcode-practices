package com.example.solution;

public class P935_Knight_Dialer {
    /**
     * Reference:
     * https://leetcode.com/problems/knight-dialer/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation
     * 1. considering which squares we can jump to from each square
     * a. 0 -> 4,6
     * b. 1 -> 6,8
     * c. 2 -> 7,9
     * d. 3 -> 4,8
     * e. 4 -> 3,9,0
     * f. 5
     * g. 6 -> 1,7,0
     * h. 7 -> 2,6
     * i. 8 -> 1,3
     * j. 9 -> 2,4
     * 2. If knight is currently on square 7 and we need to make 5 more jumps. How
     * many ways can we finish these 5 jumps? We have two possibilities:
     * a. Jump to square 2. Now, we're on square 2 and need to make 4 more jumps.
     * b. Jump to square 6. Now, we're on square 6 and need to make 4 more jumps.
     * 3. Let's define a function dp(remain, square). It will return the number of
     * ways to finish remain jumps if we're currently on square.
     * a. The base case of this function is when remain = 0. We have finished the
     * task, so we can just return 1.
     * b. Otherwise, we must calculate the value of dp(remain, square). Consider all
     * squares that we could jump to from square (which we can find from the table
     * above). For each nextSquare, jumping to nextSquare would yield dp(remain - 1,
     * nextSquare) ways to finish the jumps. The answer to dp(remain, square) is the
     * sum of all these options.
     * 4. The problem description states that we can place the knight on any
     * starting square. Thus, we must consider all squares as the starting square.
     * Given a starting square, we must make n - 1 jumps. This is because the
     * starting square automatically contributes 1 toward our path of length n, and
     * each jump will contribute 1 more. Thus, we need to make n - 1 jumps.
     * 5. The answer to the problem is the sum of dp(n - 1, square) for all values
     * of square in the range [0, 9]
     * 6. we need to memoize our dp function. Many states of remain, square will
     * overlap as each call to dp can create up to three more calls to dp
     * 
     */

    int[][] memo;
    int n;
    int MOD = (int) 1e9 + 7;
    int[][] jumps = {
            { 4, 6 }, // 0
            { 6, 8 }, // 1
            { 7, 9 }, // 2
            { 4, 8 }, // 3
            { 3, 9, 0 }, // 4
            {}, // 5
            { 1, 7, 0 }, // 6
            { 2, 6 }, // 7
            { 1, 3 }, // 8
            { 2, 4 } // 9
    };

    public int dp(int remain, int square) {
        if (remain == 0) {
            return 1;
        }

        if (memo[remain][square] != 0) {
            return memo[remain][square];
        }

        int ans = 0;
        for (int nextSquare : jumps[square]) {
            ans = (ans + dp(remain - 1, nextSquare)) % MOD;
        }

        memo[remain][square] = ans;
        return ans;
    }

    /**
     * Strategy
     * 1. Define an array jumps where jumps[square] contains a list of all squares
     * that you can jump to from square.
     * 2. Define a memoized function dp(remain, square):
     * a. If remain == 0, return 1.
     * b. Initialize ans = 0.
     * c. Iterate nextSquare over jumps[square]:
     * c1. Add dp(remain - 1, nextSquare) to ans.
     * d. Return ans.
     * 3. Initialize ans = 0.
     * 4. Iterate square from 0 to 9:
     * a. Add dp(n - 1, square) to ans.
     * 5. Return ans
     */
    private int runTopDown() {
        memo = new int[n + 1][10];
        int ans = 0;
        for (int square = 0; square < 10; square++) {
            ans = (ans + dp(n - 1, square)) % MOD;
        }
        return ans;
    }

    /**
     * Strategy
     * 1. Define an array jumps where jumps[square] contains a list of all squares
     * that you can jump to from square.
     * 2. Initialize a 2d array dp of size n * 10.
     * 3. Set the base case: dp[0][square] = 1 for all square from 0 to 9.
     * 4. Iterate remain from 1 until n:
     * a. Iterate square from 1 to 9:
     * a1. Initialize ans = 0.
     * a2. Iterate nextSquare over jumps[square]: Add dp[remain - 1][nextSquare] to
     * ans.
     * a3. Set dp[remain][square] = ans.
     * 5. Initialize ans = 0.
     * 6. Iterate square from 0 to 9:
     * 7. Add dp[n - 1][square] to ans.
     * 8. Return ans.
     */
    private int runBottomUp() {
        memo = new int[n][10];
        for (int square = 0; square < 10; square++) {
            memo[0][square] = 1;
        }

        for (int remain = 1; remain < n; remain++) {
            for (int square = 0; square < 10; square++) {
                int ans = 0;
                for (int nextSquare : jumps[square]) {
                    ans = (ans + memo[remain - 1][nextSquare]) % MOD;
                }

                memo[remain][square] = ans;
            }
        }

        int ans = 0;
        for (int square = 0; square < 10; square++) {
            ans = (ans + memo[this.n - 1][square]) % MOD;
        }

        return ans;
    }

    public int knightDialer(int n) {
        this.n = n;
        return runTopDown();
    }
}
