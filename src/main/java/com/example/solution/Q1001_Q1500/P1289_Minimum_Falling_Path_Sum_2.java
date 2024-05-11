package com.example.solution.Q1001_Q1500;

public class P1289_Minimum_Falling_Path_Sum_2 {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-falling-path-sum-ii/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Save the size of the square grid in a variable n.
     * 2. Declare a two-dimensional array memo to cache the minimum sum of a falling
     * path with non-zero shifts, starting from a particular cell. It will have size
     * n x n.
     * 3. Fill the base case. For every cell in last row, memo[n - 1][col] will be
     * grid[n - 1][col].
     * 4. Fill the recursive cases. For every row from n - 2 to 0, and for every
     * column from 0 to n - 1, do the following
     * a. Initialize a variable next_minimum with INT_MAX. This variable will store
     * the minimum sum of a falling path with non-zero shifts, starting from the
     * next row.
     * b. From this cell, we have n - 1 possibilities of selecting an element from
     * the next row.
     * Thus traverse linearly in the next row row + 1 using variable next_row_col.
     * If next_row_col != col, then we can select memo[row + 1][next_row_col].
     * We need to select next_row_col for which next_minimum is the minimum. Thus,
     * next_minimum will be min(memo[row + 1][next_row_col]) where 0 <= next_row_col
     * < n and next_row_col != col.
     * c. Thus, from this cell, the minimum sum of a falling path with non-zero
     * shifts is grid[row][col] + next_minimum. Cache this value in memo[row][col].
     * 5. Find the minimum from the first row of memo. Return this value.
     */
    // Initialize a hash map to cache the result of each sub-problem

    public int minFallingPathSum(int[][] grid) {
        // Initialize a two-dimensional array to cache the result of each sub-problem
        int[][] memo = new int[grid.length][grid.length];

        // Fill the base case
        for (int col = 0; col < grid.length; col++) {
            memo[grid.length - 1][col] = grid[grid.length - 1][col];
        }

        // Fill the recursive cases
        for (int row = grid.length - 2; row >= 0; row--) {
            for (int col = 0; col < grid.length; col++) {
                // Select minimum from valid cells of next row
                int nextMinimum = Integer.MAX_VALUE;
                for (int nextRowCol = 0; nextRowCol < grid.length; nextRowCol++) {
                    if (nextRowCol != col) {
                        nextMinimum = Math.min(nextMinimum, memo[row + 1][nextRowCol]);
                    }
                }

                // Minimum cost from this cell
                memo[row][col] = grid[row][col] + nextMinimum;
            }
        }

        // Find the minimum from the first row
        int answer = Integer.MAX_VALUE;
        for (int col = 0; col < grid.length; col++) {
            answer = Math.min(answer, memo[0][col]);
        }

        // Return the answer
        return answer;
    }
}
