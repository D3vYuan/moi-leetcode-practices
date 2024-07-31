package com.example.solution.Q3001_Q3500;

public class P3110_Score_Of_String {
    /**
     * Reference:
     * https://leetcode.com/problems/score-of-a-string/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a variable score to 0 to store the cumulative sum.
     * 2. Iterate over all indices from 0 to length - 1 of the input string. For
     * each index, calculate the absolute difference between the ASCII values of the
     * character at the current index and the character at the next index. Add this
     * difference to the score.
     * 3. Return the score after the loop completes.
     */
    public int scoreOfString(String s) {
        int score = 0;
        // Iterate over all indices from 0 to the second-to-last index
        // Calculate and accumulate the absolute difference of ASCII values
        // between adjacent characters
        for (int i = 0; i < s.length() - 1; i++) {
            score += Math.abs(s.charAt(i) - s.charAt(i + 1));
        }
        return score;
    }

}
