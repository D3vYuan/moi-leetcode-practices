package com.example.solution.Q501_Q1000;

public class P931_Minimum_Falling_Path_Sum {

    /**
     * Reference:
     * https://leetcode.com/problems/minimum-falling-path-sum/?envType=daily-question&envId=2024-01-19
     */

    /**
     * Strategy:
     * 1. In order to record the results of computation for every cell, maintain a
     * 2-dimensional matrix named memo where the value of memo[row][col] would give
     * the minimum falling path starting from the cell (row, col).
     * 2. Implement a Depth First Search algorithm, by defining a recursive
     * function, findMinFallingPathSum(row, col), that recursively explores all the
     * paths from the current cell (defined by parameters row and col).
     * a. Define Base Case: In any recursive function, we must define the
     * terminating condition i.e the base case. When the terminating condition is
     * satisfied, we will exit the recursive search process. The base cases are as
     * follows,
     * a1. The row or col values are not within the matrix boundaries.
     * a2. We have reached the last row. In this case, we will return the value of
     * the current cell and not make any other recursive calls.
     * b. Recursively explore all paths: If the base case is not satisfied, it means
     * that we have not reached the end of our current path, and we must try all
     * options to extend our path and find the one with the minimum sum:
     * --
     * minimumPath = Minimum(findMinFallingPathSum(row + 1, col + 1),
     * findMinFallingPathSum(row + 1, col),
     * findMinFallingPathSum(row + 1, col - 1))
     * --
     * 3. To avoid repetitive computation of the results as in the brute force
     * approach, we make use of stored results as follows,
     * a. Before recursively computing the result for the current cell, check if the
     * memo has the result for the current cell. If so, return the result,
     * otherwise, proceed with the recursive call to compute the result.
     * b. After computing the result, store the result in the memo[row][col].
     * 4. Iteratively find the minimum falling path for all possible starting cells
     * i.e cells in 0th row and columns ranging from 0 to
     * matrix.length−1. Track the minimum value in the variable minFallingSum and
     * return the result.
     */
    private int findMinFallingPathSum(int[][] matrix, int row, int col, Integer[][] dp) {
        // base cases
        if (col < 0 || col == matrix.length) {
            return Integer.MAX_VALUE;
        }

        // check if we have reached the last row
        if (row == matrix.length - 1) {
            return matrix[row][col];
        }

        // check if the results are calculated before
        if (dp[row][col] != null) {
            return dp[row][col];
        }

        // calculate the minimum falling path sum starting from each possible next step
        int left = findMinFallingPathSum(matrix, row + 1, col, dp);
        int middle = findMinFallingPathSum(matrix, row + 1, col + 1, dp);
        int right = findMinFallingPathSum(matrix, row + 1, col - 1, dp);

        dp[row][col] = Math.min(left, Math.min(middle, right)) + matrix[row][col];
        return dp[row][col];
    }

    private int topDown(int[][] matrix) {
        int minFallingSum = Integer.MAX_VALUE;
        Integer[][] dp = new Integer[matrix.length][matrix[0].length];

        // for (int i = 0; i < dp.length; i++) {
        // Arrays.fill(dp[i], -1);
        // }

        // start a DFS (with memoization) from each cell in the top row
        for (int startCol = 0; startCol < matrix.length; startCol++) {
            minFallingSum = Math.min(minFallingSum, findMinFallingPathSum(matrix, 0, startCol, dp));
        }
        return minFallingSum;
    }

    /**
     * Strategy:
     * 1. To compute the minimum falling path for a certain cell (row, col), we must
     * have pre-computed values for the minimum falling path for cells (row + 1, col
     * - 1), (row + 1, col) and (row + 1, col + 1). For this, we will iterate from
     * (n−1)th row to 0th row and from 0th column to (n−1) column.
     * Note: The order of iterating the columns does not matter in this case. Even
     * if we iterate from (n−1)th to 0th column, the results would be the same.
     * 2. Build a 2D matrix dp and compute the minimum falling path of current row
     * and col as,
     * --
     * dp[row][col] = min(dp[row + 1][col - 1],
     * dp[row + 1][col],
     * dp[row + 1][col + 1]) + value of current (row, col) in matrix
     * --
     * Note: We must handle the edge cases, for example, if the col value is 0 or
     * n-1
     * 3. Once we have the value of the minimum falling path from every cell, we
     * must get the result from the start cell. The start cell can be any cell on
     * the first row. Thus, we will iterate over all the cells in the first row, and
     * return the minimum value.
     */
    private int bottomUp(int[][] matrix) {
        int dp[][] = new int[matrix.length + 1][matrix.length + 1];
        for (int row = matrix.length - 1; row >= 0; row--) {
            for (int col = 0; col < matrix.length; col++) {
                if (col == 0) {
                    dp[row][col] = Math.min(dp[row + 1][col], dp[row + 1][col + 1]) + matrix[row][col];
                } else if (col == matrix.length - 1) {
                    dp[row][col] = Math.min(dp[row + 1][col], dp[row + 1][col - 1]) + matrix[row][col];
                } else {
                    dp[row][col] = Math.min(dp[row + 1][col],
                            Math.min(dp[row + 1][col + 1], dp[row + 1][col - 1])) + matrix[row][col];
                }
            }
        }
        int minFallingSum = Integer.MAX_VALUE;
        for (int startCol = 0; startCol < matrix.length; startCol++) {
            minFallingSum = Math.min(minFallingSum, dp[0][startCol]);
        }
        return minFallingSum;
    }

    public int minFallingPathSum(int[][] matrix) {
        return topDown(matrix);
        // return bottomUp(matrix);
    }
}
