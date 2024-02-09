package com.example.solution.Q1001_Q1500;

public class P1143_Longest_Common_Subsequence {
    /**
     * Reference:
     * https://leetcode.com/problems/longest-common-subsequence/editorial/?envType=daily-question&envId=2024-01-19
     */

    /**
     * Strategy:
     * 1.
     */
    private int solve(int text1Pointer, int text2Pointer, String text1, String text2, int[][] memo) {
        // Check whether or not we've already solved this subproblem.
        // This also covers the base cases where p1 == text1.length
        // or p2 == text2.length.
        if (memo[text1Pointer][text2Pointer] != -1) {
            return memo[text1Pointer][text2Pointer];
        }

        // Recursive cases.
        int answer = 0;
        if (text1.charAt(text1Pointer) == text2.charAt(text2Pointer)) {
            answer = 1 + solve(text1Pointer + 1, text2Pointer + 1, text1, text2, memo);
        } else {
            int moveText1 = solve(text1Pointer + 1, text2Pointer, text1, text2, memo);
            int moveText2 = solve(text1Pointer, text2Pointer + 1, text1, text2, memo);
            answer = Math.max(moveText1, moveText2);
        }

        // Add the best answer to the memo before returning it.
        return memo[text1Pointer][text2Pointer] = answer;
    }

    private int runTopDown(String text1, String text2) {
        // Make the memo big enough to hold the cases where the pointers
        // go over the edges of the strings.
        int[][] memo = new int[text1.length() + 1][text2.length() + 1];

        // We need to initialise the memo array to -1's so that we know
        // whether or not a value has been filled in. Keep the base cases
        // as 0's to simplify the later code a bit.
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                memo[i][j] = -1;
            }
        }

        return solve(0, 0, text1, text2, memo);
    }

    /**
     * Strategy:
     * 1.
     */
    private int runBottomUp(String text1, String text2) {
        // Make a grid of 0's with text2.length() + 1 columns
        // and text1.length() + 1 rows.
        int[][] dpGrid = new int[text1.length() + 1][text2.length() + 1];

        // Iterate up each column, starting from the last one.
        for (int col = text2.length() - 1; col >= 0; col--) {
            for (int row = text1.length() - 1; row >= 0; row--) {
                // If the corresponding characters for this cell are the same...
                if (text1.charAt(row) == text2.charAt(col)) {
                    dpGrid[row][col] = 1 + dpGrid[row + 1][col + 1];
                    // Otherwise they must be different...
                } else {
                    dpGrid[row][col] = Math.max(dpGrid[row + 1][col], dpGrid[row][col + 1]);
                }
            }
        }

        // The original problem's answer is in dp_grid[0][0]. Return it.
        return dpGrid[0][0];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        return runTopDown(text1, text2);
        // return runBottomUp(text1, text2);
    }
}
