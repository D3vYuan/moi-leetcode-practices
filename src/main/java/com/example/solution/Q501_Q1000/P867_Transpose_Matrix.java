package com.example.solution.Q501_Q1000;

public class P867_Transpose_Matrix {

    /**
     * Reference:
     * https://leetcode.com/problems/transpose-matrix/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation
     * 1. The transpose of a matrix A with dimensions R x C is a matrix ans with
     * dimensions C x R for which ans[c][r] = A[r][c]
     */

    public int[][] transpose(int[][] A) {
        int R = A.length, C = A[0].length;
        int[][] ans = new int[C][R];
        for (int r = 0; r < R; ++r)
            for (int c = 0; c < C; ++c) {
                ans[c][r] = A[r][c];
            }
        return ans;
    }
}