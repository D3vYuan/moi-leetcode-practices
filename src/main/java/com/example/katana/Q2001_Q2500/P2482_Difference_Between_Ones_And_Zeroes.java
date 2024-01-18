package com.example.katana.Q2001_Q2500;

public class P2482_Difference_Between_Ones_And_Zeroes {
    public int[][] onesMinusZeros(int[][] grid) {
        // Compute Col SUm and Row SUm
        int totalRows = grid.length;
        int totalCols = grid[0].length;

        int[] rowSum = new int[totalRows];
        int[] colSum = new int[totalCols];

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                rowSum[row] += grid[row][col];
                colSum[col] += grid[row][col];
            }
        }

        int[][] diff = new int[totalRows][totalCols];

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                diff[row][col] = rowSum[row] + colSum[col] - (totalRows - rowSum[row]) - (totalCols - colSum[col]);
            }
        }

        return diff;
    }
}
