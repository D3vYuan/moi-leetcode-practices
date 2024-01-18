package com.example.solution.Q1501_Q2000;

public class P1582_Special_Position_Binary_Matrix {
    /**
     * Reference:
     * https://leetcode.com/problems/special-positions-in-a-binary-matrix/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy
     * 1. Initialize the size of the matrix m = mat.length, n = mat[0].length.
     * 2. Initialize two integer arrays rowCount of length m and colCount of length
     * n.
     * 3. Iterate row from 0 until m:
     * a. Iterate col from 0 until n:
     * a1. If mat[row][col] = 1, increment rowCount[row] and colCount[col].
     * 4. Initialize the answer ans = 0.
     * 5. Iterate row from 0 until m:
     * a. Iterate col from 0 until n:
     * a1. If mat[row][col] = 1: If rowCount[row] = 1 and colCount[col] = 1,
     * increment ans.
     * 6. Return ans.
     */
    public int numSpecial(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[] rowCount = new int[m];
        int[] colCount = new int[n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (mat[row][col] == 1) {
                    rowCount[row]++;
                    colCount[col]++;
                }
            }
        }

        int ans = 0;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (mat[row][col] == 1) {
                    if (rowCount[row] == 1 && colCount[col] == 1) {
                        ans++;
                    }
                }
            }
        }

        return ans;
    }
}
