package com.example.solution.Q1501_Q2000;

public class P1605_Find_Valid_Matrix_Given_Rows_And_Columns_Sum {
    /**
     * Reference:
     * https://leetcode.com/problems/find-valid-matrix-given-row-and-column-sums/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize the number of rows and number of columns as N and M
     * respectively.
     * 2. Initialize the answer matrix origMatrix of size N∗M with all values as
     * zero.
     * 3. Iterate over all cells in the matrix and for each cell (i, j), do the
     * following:
     * a. Store the value in origMatrix[i][j] as min(rowSum[i], colSum[j]).
     * b. Subtract the above value from rowSum[i] and colSum[j].
     * 4. Return origMatrix.
     */

    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int N = rowSum.length;
        int M = colSum.length;

        int[][] origMatrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                origMatrix[i][j] = Math.min(rowSum[i], colSum[j]);

                rowSum[i] -= origMatrix[i][j];
                colSum[j] -= origMatrix[i][j];
            }
        }

        return origMatrix;
    }
}
