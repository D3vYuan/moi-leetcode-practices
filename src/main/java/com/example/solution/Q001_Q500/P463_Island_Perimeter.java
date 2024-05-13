package com.example.solution.Q001_Q500;

public class P463_Island_Perimeter {
    /**
     * Reference:
     * https://leetcode.com/problems/island-perimeter/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Go through every cell on the grid and whenever you are at cell 1 (land
     * cell), look for surrounding (UP, RIGHT, DOWN, LEFT) cells. A land cell
     * without any surrounding land cell will have a perimeter of 4. Subtract 1 for
     * each surrounding land cell.
     * 2. When you are at cell 0 (water cell), you don't need to do anything. Just
     * proceed to another cell.
     */
    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int up, down, left, right;
        int result = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    if (r == 0) {
                        up = 0;
                    } else {
                        up = grid[r - 1][c];
                    }

                    if (c == 0) {
                        left = 0;
                    } else {
                        left = grid[r][c - 1];
                    }

                    if (r == rows - 1) {
                        down = 0;
                    } else {
                        down = grid[r + 1][c];
                    }

                    if (c == cols - 1) {
                        right = 0;
                    } else {
                        right = grid[r][c + 1];
                    }

                    result += 4 - (up + left + right + down);
                }
            }
        }

        return result;
    }

}
