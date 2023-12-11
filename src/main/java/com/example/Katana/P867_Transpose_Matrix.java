package com.example.katana;

public class P867_Transpose_Matrix {
    public int[][] transpose(int[][] matrix) {
        int matRow = matrix.length;
        int matCol = matrix[0].length;
        int[][] transposeMatrix = new int[matCol][matRow];

        for (int row = 0; row < transposeMatrix.length; row++) {
            for (int col = 0; col < transposeMatrix[0].length; col++) {
                transposeMatrix[row][col] = matrix[col][row];
            }
        }

        return transposeMatrix;
    }
}
