package com.example.solution.Q001_Q500;

import java.util.Arrays;

public class P242_Valid_Anagram {
    /**
     * Reference:
     * https://leetcode.com/problems/valid-anagram/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy:
     * 1.
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }
}
