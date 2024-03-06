package com.example.solution.Q1001_Q1500;

public class P1463_Cherry_Pickup_2 {
    /**
     * Reference:
     * https://leetcode.com/problems/cherry-pickup-ii/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Explanation:
     * 1. when we move one robot several steps earlier than the other, the movement
     * of the first robot will impact the movement of the second robot.
     * 2. Unless we move them synchronously (i.e., move one step of robot1 and
     * robot2 at the same time).
     * 3. Define the DP state as (row1, col1, row2, col2), where (row1, col1)
     * represents the location of robot1, and (row2, col2) represents the location
     * of robot2.
     * 4. If we move them synchronously, robot1 and robot2 will always on the same
     * row. Therefore, row1 == row2.
     * 5. Let row = row1. The DP state is simplified to (row, col1, col2), where
     * (row, col1)
     * represents the location of robot1, and (row, col2) represents the location of
     * robot2.
     * 6. Let dp(row, col1, col2) return the maximum cherries we can pick if robot1
     * starts at (row, col1) and robot2 starts at (row, col2)
     * 7. The base cases are that robot1 and robot2 both start at the bottom line.
     * In this case, they do not need to move. All we need to do is to collect the
     * cherries at current cells. Remember not to double count if robot1 and robot2
     * are at exactly the same cell.
     * 8. Since we move robots synchronously, and each robot has three different
     * movements for one step, we totally have 3∗3=93*3 = 93∗3=9 possible movements
     * for two robots
     * 9. The maximum cherries robots can pick in the future would be the max of
     * those 9 movements, which is the maximum of dp(row+1, new_col1, new_col2),
     * where new_col1 can be col1, col1+1, or col1-1, and new_col2 can be col2,
     * col2+1, or col2-1
     */

    /**
     * Strategy:
     * 1. Define a dp function that takes three integers row, col1, and col2 as
     * input.
     * (row, col1) represents the location of robot1, and (row, col2) represents the
     * location of robot2.
     * 2. The dp function returns the maximum cherries we can pick if robot1 starts
     * at (row, col1) and robot2 starts at (row, col2).
     * 3. In the dp function:
     * a. Collect the cherry at (row, col1) and (row, col2). Do not double count if
     * col1 == col2.
     * b. If we do not reach the last row, we need to add the maximum cherries we
     * can pick in the future.
     * c. The maximum cherries we can pick in the future is the maximum of dp(row+1,
     * new_col1, new_col2), where new_col1 can be col1, col1+1, or col1-1, and
     * new_col2 can be col2, col2+1, or col2-1.
     * d. Return the total cherries we can pick.
     * 4. Finally, return dp(row=0, col1=0, col2=last_column) in the main function.
     */

    private int runTopDown(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] memo = new int[m][n][n];
        // initial all elements to -1 to mark unseen
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    memo[i][j][k] = -1;
                }
            }
        }
        return solve(0, 0, n - 1, grid, memo);
    }

    private int solve(int row, int col1, int col2, int[][] grid, int[][][] memo) {
        if (col1 < 0 || col1 >= grid[0].length || col2 < 0 || col2 >= grid[0].length) {
            return 0;
        }
        // check cache
        if (memo[row][col1][col2] != -1) {
            return memo[row][col1][col2];
        }
        // current cell
        int result = 0;
        result += grid[row][col1];
        if (col1 != col2) {
            result += grid[row][col2];
        }
        // transition
        if (row != grid.length - 1) {
            int max = 0;
            for (int newCol1 = col1 - 1; newCol1 <= col1 + 1; newCol1++) {
                for (int newCol2 = col2 - 1; newCol2 <= col2 + 1; newCol2++) {
                    max = Math.max(max, solve(row + 1, newCol1, newCol2, grid, memo));
                }
            }
            result += max;
        }

        memo[row][col1][col2] = result;
        return result;
    }

    /**
     * Strategy:
     * 1. Define a three-dimension dp array where each dimension has a size of m, n,
     * and n respectively.
     * 2. dp[row][col1][col2] represents the maximum cherries we can pick if robot1
     * starts at (row, col1) and robot2 starts at (row, col2).
     * 3. To compute dp[row][col1][col2] (transition equation):
     * a. Collect the cherry at (row, col1) and (row, col2). Do not double count if
     * col1 == col2.
     * b. If we are not in the last row, we need to add the maximum cherries we can
     * pick in the future.
     * c. The maximum cherries we can pick in the future is the maximum of
     * dp[row+1][new_col1][new_col2], where new_col1 can be col1, col1+1, or col1-1,
     * and new_col2 can be col2, col2+1, or col2-1.
     * 4. Finally, return dp[0][0][last_column].
     */
    private int runBottomUp(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int dp[][][] = new int[m][n][n];

        for (int row = m - 1; row >= 0; row--) {
            for (int col1 = 0; col1 < n; col1++) {
                for (int col2 = 0; col2 < n; col2++) {
                    int result = 0;
                    // current cell
                    result += grid[row][col1];
                    if (col1 != col2) {
                        result += grid[row][col2];
                    }
                    // transition
                    if (row != m - 1) {
                        int max = 0;
                        for (int newCol1 = col1 - 1; newCol1 <= col1 + 1; newCol1++) {
                            for (int newCol2 = col2 - 1; newCol2 <= col2 + 1; newCol2++) {
                                if (newCol1 >= 0 && newCol1 < n && newCol2 >= 0 && newCol2 < n) {
                                    max = Math.max(max, dp[row + 1][newCol1][newCol2]);
                                }
                            }
                        }
                        result += max;
                    }
                    dp[row][col1][col2] = result;
                }
            }
        }
        return dp[0][0][n - 1];
    }

    public int cherryPickup(int[][] grid) {
        return runTopDown(grid);
        // return runBottomUp(grid);
    }
}
