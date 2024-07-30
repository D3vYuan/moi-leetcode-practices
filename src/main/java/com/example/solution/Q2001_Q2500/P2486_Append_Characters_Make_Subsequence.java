package com.example.solution.Q2001_Q2500;

public class P2486_Append_Characters_Make_Subsequence {
    /**
     * Reference: https://leetcode.com/problems/append-characters-to-string-to-make-subsequence/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialise two pointers first and longestPrefix with 0.
     * 2. Iterate while first is less than s.length and longestPrefix is less than t.length:
     * a. If the s[first] is equal to t[longestPrefix]:
     *      Increment longestPrefix by 1.
     * b. Increment first by 1.
     * 3. Return the difference of t.length and longestPrefix.
     */

    public int appendCharacters(String s, String t) {
        int first = 0, longestPrefix = 0;

        while (first < s.length() && longestPrefix < t.length()) {
            if (s.charAt(first) == t.charAt(longestPrefix)) {
                // Since at the current position both the characters are equal,
                // increment longestPrefix by 1
                longestPrefix++;
            }
            first++;
        }

        // The number of characters appended is given by the difference in
        // length of t and longestPrefix
        return t.length() - longestPrefix;
    }
}
