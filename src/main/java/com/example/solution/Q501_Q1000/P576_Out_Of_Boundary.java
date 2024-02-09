package com.example.solution.Q501_Q1000;

import java.util.Arrays;

public class P576_Out_Of_Boundary {
    /**
     * Reference:
     * https://leetcode.com/problems/out-of-boundary-paths/editorial/?envType=daily-question&envId=2024-01-19
     * 
     */

    /**
     * Strategy:
     * 1.
     */
    int M = 1_000_000_007;

    private int solve(int totalRow, int totalCol, int remainingMoves, int currentRow, int currentColumn,
            int[][][] memo) {
        if (currentRow == totalRow || currentColumn == totalCol || currentRow < 0 || currentColumn < 0) {
            return 1;
        }

        if (remainingMoves == 0) {
            return 0;
        }

        if (memo[currentRow][currentColumn][remainingMoves] >= 0) {
            return memo[currentRow][currentColumn][remainingMoves];
        }

        int moveUp = solve(totalRow, totalCol, remainingMoves - 1, currentRow - 1, currentColumn, memo);
        int moveDown = solve(totalRow, totalCol, remainingMoves - 1, currentRow + 1, currentColumn, memo);
        int moveRow = (moveUp + moveDown) % M;

        int moveLeft = solve(totalRow, totalCol, remainingMoves - 1, currentRow, currentColumn - 1, memo);
        int moveRight = solve(totalRow, totalCol, remainingMoves - 1, currentRow, currentColumn + 1, memo);
        int moveCol = (moveLeft + moveRight) % M;

        return memo[currentRow][currentColumn][remainingMoves] = (moveRow + moveCol) % M;
    }

    private int runTopDown(int totalRow, int totalCol, int maxMove, int startRow, int startColumn) {
        int[][][] memo = new int[totalRow][totalCol][maxMove + 1];
        for (int[][] row : memo) {
            for (int[] col : row) {
                Arrays.fill(col, -1);
            }
        }
        return solve(totalRow, totalCol, maxMove, startRow, startColumn, memo);
    }

    /**
     * Strategy:
     * 1.
     */
    private int runBottomUp(int totalRow, int totalCol, int maxMove, int startRow, int startColumn) {
        int M = 1_000_000_007;
        int dp[][] = new int[totalRow][totalCol];
        dp[startRow][startColumn] = 1;
        int count = 0;
        for (int moves = 1; moves <= maxMove; moves++) {
            int[][] temp = new int[totalRow][totalCol];
            for (int i = 0; i < totalRow; i++) {
                for (int j = 0; j < totalCol; j++) {
                    if (i == totalRow - 1)
                        count = (count + dp[i][j]) % M;
                    if (j == totalCol - 1)
                        count = (count + dp[i][j]) % M;
                    if (i == 0)
                        count = (count + dp[i][j]) % M;
                    if (j == 0)
                        count = (count + dp[i][j]) % M;
                    temp[i][j] = (((i > 0 ? dp[i - 1][j] : 0) + (i < totalRow - 1 ? dp[i + 1][j] : 0)) % M +
                            ((j > 0 ? dp[i][j - 1] : 0) + (j < totalCol - 1 ? dp[i][j + 1] : 0)) % M) % M;
                }
            }
            dp = temp;
        }
        return count;
    }

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        return runTopDown(m, n, maxMove, startRow, startColumn);
        // return runBottomUp(m, n, maxMove, startRow, startColumn);
    }
}
