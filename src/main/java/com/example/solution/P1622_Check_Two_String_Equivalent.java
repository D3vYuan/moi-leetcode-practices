package com.example.solution;

public class P1622_Check_Two_String_Equivalent {
    /**
     * Reference:
     * https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy
     * 1. Iterate over strings present in the array word1, append each string to a
     * string word1Combined.
     * 2. Iterate over strings present in the array word2, append each string to a
     * string word2Combined.
     * 3. Compare the above strings and return true if both are the same, otherwise
     * return false.
     */
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        // Creates a new string by combining all the strings in word1.
        StringBuilder word1Combined = new StringBuilder();
        for (String s : word1) {
            word1Combined.append(s);
        }
        // Creates a new string by combining all the strings in word2.
        StringBuilder word2Combined = new StringBuilder();
        for (String s : word2) {
            word2Combined.append(s);
        }
        // Returns true if both string are the same.
        return word1Combined.toString().compareTo(word2Combined.toString()) == 0;
    }
}
