package com.example.solution.Q1001_Q1500;

import java.util.ArrayList;
import java.util.List;

public class P1380_Lucky_Numbers_In_Matrix {
    /**
     * Reference:
     * https://leetcode.com/problems/lucky-numbers-in-a-matrix/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Iterate over each row and store the minimum of the ith row at the ith
     * position in the list rowMin.
     * 2. Iterate over each column and store the maximum of the ith column at the
     * ith position in the list colMax.
     * 3. Iterate over each integer in the matrix and for each integer at (i, j),
     * check if the integer is equal to rowMin[i] and colMax[j]. If yes, add it to
     * the list luckyNumbers.
     * 4. Return luckyNumbers.
     */
    public List<Integer> luckyNumbers(int[][] matrix) {
        int N = matrix.length, M = matrix[0].length;

        List<Integer> rowMin = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int rMin = Integer.MAX_VALUE;
            for (int j = 0; j < M; j++) {
                rMin = Math.min(rMin, matrix[i][j]);
            }
            rowMin.add(rMin);
        }

        List<Integer> colMax = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int cMax = Integer.MIN_VALUE;
            for (int j = 0; j < N; j++) {
                cMax = Math.max(cMax, matrix[j][i]);
            }
            colMax.add(cMax);
        }

        List<Integer> luckyNumbers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == rowMin.get(i) && matrix[i][j] == colMax.get(j)) {
                    luckyNumbers.add(matrix[i][j]);
                }
            }
        }

        return luckyNumbers;
    }

}
