package com.example.solution.Q001_Q500;

public class P97_Interleaving_String {
    /**
     * Reference:
     * https://leetcode.com/problems/interleaving-string/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. we make the function call recursively as
     * is_Interleave(s1,i+1,s2,j,res+s1.charAt(i),s3), in which we have chosen the
     * current character from s1 and then make another function call
     * is_Interleave(s1,i,s2,j+1,res+s2.charAt(j),s3), in which the current
     * character of s2 is chosen. Here, resresres refers to that
     * portion(interleaved) of s1 and s2 which has already been processed. If anyone
     * of these calls return the result as True, it means that atleast one
     * interleaving gives the required result s3. The recursive calls end when both
     * the strings s1 and s2 have been fully processed
     */
    private boolean isInterleave(String s1, int i, String s2, int j, String s3, int k, int[][] memo) {
        if (i == s1.length()) {
            return s2.substring(j).equals(s3.substring(k));
        }
        if (j == s2.length()) {
            return s1.substring(i).equals(s3.substring(k));
        }
        if (memo[i][j] >= 0) {
            return memo[i][j] == 1 ? true : false;
        }
        boolean ans = false;
        if (s3.charAt(k) == s1.charAt(i) && isInterleave(s1, i + 1, s2, j, s3, k + 1, memo)
                || s3.charAt(k) == s2.charAt(j) && isInterleave(s1, i, s2, j + 1, s3, k + 1, memo)) {
            ans = true;
        }
        memo[i][j] = ans ? 1 : 0;
        return ans;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        int memo[][] = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                memo[i][j] = -1;
            }
        }
        return isInterleave(s1, 0, s2, 0, s3, 0, memo);
    }
}
