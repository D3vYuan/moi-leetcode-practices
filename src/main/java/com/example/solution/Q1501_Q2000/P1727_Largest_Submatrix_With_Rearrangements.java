package com.example.solution.Q1501_Q2000;

import java.util.Arrays;

public class P1727_Largest_Submatrix_With_Rearrangements {
    /**
     * Reference:
     * https://leetcode.com/problems/largest-submatrix-with-rearrangements/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation
     * A submatrix is just a rectangle - what is the area of a rectangle? It's B *
     * H, where B is the base (width) and H is the height of the rectangle. As we
     * are looking for the largest submatrix, we would prefer larger values for B
     * and H.
     * As such, a good first step would be to determine how much height each column
     * contributes on its own.
     * Let's iterate over this sorted row and consider the largest submatrix we can
     * make.
     * - At column 0, we have a height of 3. What is our base? We only have one
     * column, so the base is 1. Thus, we have an area of 3.
     * - At column 1, we have a height of 2. What is our base? Every column must
     * have a height of at least 2 for us to have a valid submatrix. Because we
     * sorted descending, every column to the left must have a height of at least 2.
     * Thus, we have a base of 2, and an area of 2 * 2 = 4.
     * - At column 2, we have a height of 0 and a base of 3. The area is 0
     * 
     * Strategy
     * 1. Initialize m = matrix.length, n = matrix[0].length, and the answer ans=0
     * 2. Iterate row from 0 to m:
     * a. Iterate col from 0 to n:
     * a1. If matrix[row][col] != 0 and row > 0: Add matrix[row - 1][col] to
     * matrix[row][col].
     * b. Create a copy of matrix[row] as currRow, then sort currRow in descending
     * order.
     * c. Iterate i over the indiecs of currRow:
     * d. Update ans with currRow[i] * (i + 1) if it is larger.
     * e. Return ans.
     * 
     */

    public int largestSubmatrix(int[][] matrix) {
        int matRow = matrix.length;
        int matCol = matrix[0].length;
        int ans = 0;

        for (int row = 0; row < matRow; row++) {
            for (int col = 0; col < matCol; col++) {
                if (matrix[row][col] != 0 && row > 0) {
                    matrix[row][col] += matrix[row - 1][col];
                }
            }

            int[] currRow = matrix[row].clone();
            Arrays.sort(currRow);
            for (int i = 0; i < matCol; i++) {
                ans = Math.max(ans, currRow[i] * (matCol - i));
                System.out.println(String.format("Area: %d | Row: %d | Col: %d ", currRow[i] * (matCol - 1), currRow[i], (matCol - i)));
            }
        }

        return ans;
    }
}
