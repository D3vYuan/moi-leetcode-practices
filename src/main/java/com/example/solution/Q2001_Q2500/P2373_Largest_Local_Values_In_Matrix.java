package com.example.solution.Q2001_Q2500;

public class P2373_Largest_Local_Values_In_Matrix {
    /**
     * Reference:
     * https://leetcode.com/problems/largest-local-values-in-a-matrix/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Create an empty matrix maxLocal of size (N−2)⋅(N−2)(N - 2) \cdot (N -
     * 2)(N−2)⋅(N−2), this will store the maximum values of all possible 3 x 3
     * matrices.
     * 2. Define the findMax function, which takes the grid and the coordinates (x,
     * y) as parameters. This function finds the maximum value in the 3 x 3 section
     * of the grid, where (x, y) is the top-left corner.
     * 3. Iterate over the 3 x 3 matrix starting with (x, y) as top-left cell.
     * 4. Find and return the maximum value as maxElement.
     * 5. Iterate over the grid rows 0 to N - 2 and columns 0 to N - 2, and for each
     * cell (i, j):
     * 6. Use findMax(grid, i, j) to find the maximum local element and store it in
     * the matrix maxLocal at position (i, j).
     * 7. Return maxLocal.
     * 
     */
    // Return the maximum values in the 3 x 3 matrix with top-left as (x, y).
    private int findMax(int[][] grid, int x, int y) {
        int maxElement = 0;
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                maxElement = Math.max(maxElement, grid[i][j]);
            }
        }

        return maxElement;
    }

    public int[][] largestLocal(int[][] grid) {
        int N = grid.length;

        int[][] maxLocal = new int[N - 2][N - 2];
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < N - 2; j++) {
                maxLocal[i][j] = findMax(grid, i, j);
            }
        }

        return maxLocal;
    }
}
