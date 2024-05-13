package com.example.solution.Q001_Q500;

public class P58_Length_Of_Last_Word {
    /**
     * Reference:
     * https://leetcode.com/problems/length-of-last-word/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. The trick is that we could define a condition, i.e. the precise moment
     * that we should start to count the length of the word.
     */

    public int lengthOfLastWord(String s) {
        int p = s.length(), length = 0;
        while (p > 0) {
            p--;
            // we're in the middle of the last word
            if (s.charAt(p) != ' ') {
                length++;
            }
            // here is the end of last word
            else if (length > 0) {
                return length;
            }
        }
        return length;
    }
}
