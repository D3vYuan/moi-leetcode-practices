package com.example.solution.Q2001_Q2500;

import java.util.Arrays;

public class P2482_Difference_Between_Ones_And_Zeroes {
    /**
     * References:
     * https://leetcode.com/problems/difference-between-ones-and-zeros-in-row-and-column/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy:
     * 1. Initialize two arrays onesRow and onesCol of size M and N with zeroes.
     * 2. Iterate over the cells in the matrix grid and add the value grid[i][j] to
     * onesRow[i] and onesCol[j].
     * 3. Initialize an empty matrix matrix diff with size M * N.
     * 4. Iterate over the matrix grid and assign diff[i][j] as 2 * onesRow[i] + 2 *
     * onesCol[j] - N - M.
     * 5. Return diff.
     */

    public int[][] onesMinusZeros(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] onesRow = new int[m];
        int[] onesCol = new int[n];

        Arrays.fill(onesRow, 0);
        Arrays.fill(onesCol, 0);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                onesRow[i] += grid[i][j];
                onesCol[j] += grid[i][j];
            }
        }

        int[][] diff = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                diff[i][j] = 2 * onesRow[i] + 2 * onesCol[j] - n - m;
            }
        }

        return diff;
    }
}
