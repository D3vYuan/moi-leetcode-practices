package com.example.solution.Q1001_Q1500;

import java.util.HashMap;
import java.util.Map;

public class P1074_Number_Submatrices_Sum_To_Target {
    /**
     * Reference:
     * https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. Initialize the result: count = 0.
     * 2. Compute the number of rows: r = len(matrix) and number of columns: c =
     * len(matrix[0]).
     * 3. Compute 2D prefix sum ps. To simplify the code, we allocate one more row
     * and one more column, reserving row 0 and column 0 for zero values. This way,
     * we avoid computing the first row and the first column separately.
     * 4. Iterate over the rows: r1 from 1 to r, and r2 from r1 to r:
     * a. Inside this double loop, the left and right row boundaries are fixed. Now
     * it's time to initialize a hashmap: 1D prefix sum -> number of matrices which
     * use [r1, r2] rows and sum to this prefix sum. The sum of the empty matrix is
     * equal to zero: h[0] = 1.
     * b. Iterate over the columns from 1 to c + 1. At each step:
     * b1. Compute current 1D prefix sum curr_sum using previously computed 2D
     * prefix sum ps: curr_sum = ps[r2][col] - ps[r1 - 1][col].
     * b2. The number of times the sum curr_sum - target occurred, defines the
     * number of matrices that use r1 ... r2 rows and sum to target. Increment the
     * count: count += h[curr_sum - target].
     * b3. Add the current 1D prefix sum into the hashmap.
     * 5. Return count.
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;

        // compute 2D prefix sum
        int[][] ps = new int[row + 1][col + 1];
        for (int i = 1; i < row + 1; i++) {
            for (int j = 1; j < col + 1; j++) {
                ps[i][j] = ps[i - 1][j] + ps[i][j - 1] - ps[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }

        int count = 0, currSum;
        Map<Integer, Integer> h = new HashMap<>();
        // reduce 2D problem to 1D one
        // by fixing two rows r1 and r2 and
        // computing 1D prefix sum for all matrices using [r1..r2] rows

        for (int r1 = 1; r1 < row + 1; r1++) {
            for (int r2 = r1; r2 < row + 1; r2++) {
                h.clear();
                h.put(0, 1);
                for (int c = 1; c < col + 1; c++) {
                    // current 1D prefix sum
                    currSum = ps[r2][c] - ps[r1 - 1][c];

                    // add subarrays which sum up to (currSum - target)
                    count += h.getOrDefault(currSum - target, 0);

                    // save current prefix sum
                    h.put(currSum, h.getOrDefault(currSum, 0) + 1);
                }
            }
        }

        return count;
    }
}
