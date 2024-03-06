package com.example.solution.Q1501_Q2000;

public class P1750_Minimum_Length_After_Deleting_Similar_Ends {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-length-of-string-after-deleting-similar-ends/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. Initialize two variables, begin to 0 and end to s.length() - 1. begin
     * points to the first index of s and end points to the last index.
     * 2. While begin is less than end and the character at s[begin] equals the
     * character at s[end]:
     * a. Initialize a character c to s[begin].
     * b. While begin is less than or equal to end and s[begin] equals c, increment
     * begin by 1 to delete a prefix character.
     * c. While end is greater than begin and s[end] equals c, decrement end by 1 to
     * delete a suffix character.
     * 3. After processing s, return end - begin + 1, the number of remaining
     * characters.
     */
    public int minimumLength(String s) {
        int begin = 0;
        int end = s.length() - 1;

        // Delete similar ends until the ends differ or they meet in the middle
        while (begin < end && s.charAt(begin) == s.charAt(end)) {
            char c = s.charAt(begin);

            // Delete consecutive occurrences of c from prefix
            while (begin <= end && s.charAt(begin) == c) {
                begin++;
            }

            // Delete consecutive occurrences of c from suffix
            while (end > begin && s.charAt(end) == c) {
                end--;
            }
        }

        // Return the number of remaining characters
        return end - begin + 1;
    }
}