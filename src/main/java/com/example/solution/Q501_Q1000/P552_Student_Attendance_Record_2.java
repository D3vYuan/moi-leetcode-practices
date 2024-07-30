package com.example.solution.Q501_Q1000;

import java.util.Arrays;

public class P552_Student_Attendance_Record_2 {
    /**
     * Reference:
     * https://leetcode.com/problems/student-attendance-record-ii/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialization:
     * a. Create a constant MOD equal to 1000000007.
     * b. Create the cache memo as a 3D vector.
     * 2. Define a function eligible_combinations(n, total_absences,
     * consecutive_lates):
     * a. If the combination is not eligible for the award (total_absences>= 2 or
     * consecutive_lates >= 3), return 0.
     * b. If we created a combination eligible for award (n == 0), return 1.
     * c. If the sub-problem has been solved earlier, then, return the stored result
     * from the cache.
     * d. Initialize a variable count to 0.
     * e. Recursively call the function for three choices and perform the modular
     * addition of the respective recursive call result with count:
     * e1. Choose 'P' for the current position: count = eligible_combinations(n - 1,
     * total_absences, 0).
     * e2. Choose 'A' for the current position: count = (count +
     * eligible_combinations(n - 1, total_absences + 1, 0)) % MOD.
     * e3. Choose 'L' for the current position: count = (count +
     * eligible_combinations(n - 1, total_absences, consecutive_lates + 1)) % MOD.
     * f. Store the result in the cache and return count.
     * 3. In checkRecord(n) function:
     * a. Initialize an empty cache memo with dimensions (n + 1) x 2 x 3 and fill it
     * with -1 indicating we have not stored the result of sub-problems (as counts
     * will always be 0 or more).
     * b. Return the result given by eligible_combinations function with initial
     * parameters (n, 0, 0) indicating no absence and no consecutive late count in
     * the initial combination.
     */

    private final int MOD = 1000000007;
    // Cache to store sub-problem results.
    private int[][][] memo;

    // Recursive function to return the count of combinations of length 'n' eligible
    // for the award.
    private int eligibleCombinations(
            int n,
            int totalAbsences,
            int consecutiveLates) {
        // If the combination has become not eligible for the award,
        // then we will not count any combinations that can be made using it.
        if (totalAbsences >= 2 || consecutiveLates >= 3) {
            return 0;
        }
        // If we have generated a combination of length 'n' we will count it.
        if (n == 0) {
            return 1;
        }
        // If we have already seen this sub-problem earlier, we return the stored
        // result.
        if (memo[n][totalAbsences][consecutiveLates] != -1) {
            return memo[n][totalAbsences][consecutiveLates];
        }

        int count = 0;
        // We choose 'P' for the current position.
        count = eligibleCombinations(n - 1, totalAbsences, 0) % MOD;
        // We choose 'A' for the current position.
        count = (count + eligibleCombinations(n - 1, totalAbsences + 1, 0)) %
                MOD;
        // We choose 'L' for the current position.
        count = (count +
                eligibleCombinations(n - 1, totalAbsences, consecutiveLates + 1)) %
                MOD;

        // Return and store the current sub-problem result in the cache.
        return memo[n][totalAbsences][consecutiveLates] = count;
    }

    public int checkRecord(int n) {
        // Initialize the cache.
        memo = new int[n + 1][2][3];
        for (int[][] array2D : memo) {
            for (int[] array1D : array2D) {
                Arrays.fill(array1D, -1);
            }
        }
        // Return count of combinations of length 'n' eligible for the award.
        return eligibleCombinations(n, 0, 0);
    }
}
