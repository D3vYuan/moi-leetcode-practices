package com.example.solution.Q501_Q1000;

public class P861_Score_After_Flipping_Matrix {
    /**
     * Reference:
     * https://leetcode.com/problems/score-after-flipping-matrix/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Explanation:
     * 1. In summary, the maximal score for a matrix is obtained by following two
     * key steps:
     * a. Flip rows to ensure all elements in the first column of the matrix are
     * 1's.
     * b. Flip a column if it contains more 0's than 1's.
     * 
     * Strategy:
     * 1. Initialize variables:
     * a. m and n as the number of rows and columns in grid respectively.
     * b. score to store the maximum score of the matrix
     * 2. Iterate through the first column of the matrix.
     * a. If the element is 0, flip the entire row.
     * 3. Iterate from the second column to the last column of the matrix. For each
     * column:
     * a. Count the number of 0's and store it in countZero.
     * b. If number of 0's is greater, flip the entire column.
     * 4. Iterate over the modified matrix.
     * a. For each element, add it to score by left shifting it by the place value
     * of the current column.
     * 5. Return score, which stores the highest possible score of the matrix.
     */
    public int matrixScore(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Set first column
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) {
                // Flip row
                for (int j = 0; j < n; j++) {
                    grid[i][j] = 1 - grid[i][j];
                }
            }
        }

        // Optimize columns except first column
        for (int j = 1; j < n; j++) {
            int countZero = 0;
            // Count zeros
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 0) {
                    countZero++;
                }
            }
            // Flip the column if there are more zeros for better score
            if (countZero > m - countZero) {
                for (int i = 0; i < m; i++) {
                    grid[i][j] = 1 - grid[i][j];
                }
            }
        }

        // Calculate the final score considering bit positions
        int score = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Left shift bit by place value of column to find column contribution
                int columnScore = grid[i][j] << (n - j - 1);
                // Add contribution to score
                score += columnScore;
            }
        }

        // return final result
        return score;
    }
}
