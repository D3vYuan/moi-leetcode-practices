package com.example.solution.Q001_Q500;

import java.util.Arrays;

public class P279_Perfect_Squares {
    /**
     * Reference:
     * https://leetcode.com/problems/perfect-squares/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. As for almost all DP solutions, we first create an array dp of one or
     * multiple dimensions to hold the values of intermediate sub-solutions, as well
     * as the final solution which is usually the last element in the array. Note
     * that, we create a fictional element dp[0]=0 to simplify the logic, which
     * helps in the case that the remainder (n-k) happens to be a square number.
     * 2. As an additional preparation step, we pre-calculate a list of square
     * numbers (i.e. square_nums) that is less than the given number n.
     * 3. As the main step, we then loop from the number 1 to n, to calculate the
     * solution for each number i (i.e. numSquares(i)). At each iteration, we keep
     * the result of numSquares(i) in dp[i], while resuing the previous results
     * stored in the array.
     * 4. At the end of the loop, we then return the last element in the array as
     * the result of the solution.
     */
    public int numSquares(int n) {
        int dp[] = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // bottom case
        dp[0] = 0;

        // pre-calculate the square numbers.
        int max_square_index = (int) Math.sqrt(n) + 1;
        int square_nums[] = new int[max_square_index];
        for (int i = 1; i < max_square_index; ++i) {
            square_nums[i] = i * i;
        }

        for (int i = 1; i <= n; ++i) {
            for (int s = 1; s < max_square_index; ++s) {
                if (i < square_nums[s])
                    break;
                dp[i] = Math.min(dp[i], dp[i - square_nums[s]] + 1);
            }
        }
        return dp[n];
    }
}
