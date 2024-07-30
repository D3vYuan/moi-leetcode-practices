package com.example.solution.Q1001_Q1500;

public class P1219_Path_With_Maximum_Gold {
    /**
     * Reference:
     * https://leetcode.com/problems/path-with-maximum-gold/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a constant array DIRECTIONS to {0, 1, 0, -1, 0}.
     * 2. Initialize the variable rows to the number of rows in the grid and cols to
     * the number of columns.
     * 3. Initialize a variable maxGold for storing the amount of gold collected on
     * any path so far to 0.
     * 4. Define a function dfsBacktrack that finds the path with the maximum gold
     * using DFS and backtracking. The function takes parameters grid, rows, cols,
     * row, and col, representing the coordinates of the current cell within the
     * grid.
     * a. Base Case: We cannot collect gold in the cell (row, col). If
     * grid[row][col] equals 0, or if the cell is outside the grid, return zero. We
     * check whether the cell is outside the grid using the condition row < 0 or col
     * < 0 or row == rows or col == cols.
     * b. Initialize a local variable maxGold to 0.
     * c. Mark the current cell as visited and save the value. Initialize a variable
     * originalVal to grid[row][col], and set grid[row][col] to 0.
     * d. Search each of the four adjacent cells. Call dfsBacktrack for the cells to
     * the left, right, above, and below the current cell. Update the maximum gold
     * if a better path is found.
     * e. Reset the current cell back to its original value so that when we
     * backtrack, we can explore other possible paths from this cell.
     * f. Return the sum of maxGold and originalVal, which represents the gold
     * collected on this path so far.
     * 5. Using nested for loops for each cell (row, col) in the grid, find the
     * maximum gold that can be collected starting at that cell using the
     * dfsBacktrack function and update maxGold whenever a better path is found.
     * 6. Return maxGold.
     */
    private final int[] DIRECTIONS = new int[] { 0, 1, 0, -1, 0 };

    public int getMaximumGold(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int maxGold = 0;

        // Search for the path with the maximum gold starting from each cell
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                maxGold = Math.max(maxGold, dfsBacktrack(grid, rows, cols, row, col));
            }
        }
        return maxGold;
    }

    private int dfsBacktrack(int[][] grid, int rows, int cols, int row, int col) {
        // Base case: this cell is not in the matrix or this cell has no gold
        if (row < 0 || col < 0 || row == rows || col == cols || grid[row][col] == 0) {
            return 0;
        }
        int maxGold = 0;

        // Mark the cell as visited and save the value
        int originalVal = grid[row][col];
        grid[row][col] = 0;

        // Backtrack in each of the four directions
        for (int direction = 0; direction < 4; direction++) {
            maxGold = Math.max(maxGold,
                    dfsBacktrack(grid, rows, cols, DIRECTIONS[direction] + row,
                            DIRECTIONS[direction + 1] + col));
        }

        // Set the cell back to its original value
        grid[row][col] = originalVal;
        return maxGold + originalVal;
    }
}
