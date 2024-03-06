package com.example.solution.Q2001_Q2500;

public class P2108_First_Palindromic_In_Array {
    /**
     * Reference:
     * https://leetcode.com/problems/find-first-palindromic-string-in-the-array/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. Define the method isPalindrome() which returns true if the provided string
     * s is a palindrome and false otherwise:
     * a. Keep one pointer of left start = 0 and one on the right end end = s.size()
     * - 1.
     * b. Keep iterating over the string until start <= end.
     * c. If the characters at start and end are not the same then return false.
     * d. Increment start and decrement end.
     * e. Return true after iterating over all the characters.
     * 2. Iterate over each string in words from left to right and call
     * isPalindrome() for each string and return the first one for which the method
     * returns true.
     * 3. After the loop terminates, return an empty string. If the loop terminates
     * without finding and returning a palindrome, it means words has no
     * palindromes.
     */
    private boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;

        while (start <= end) {
            // Return false if the characters are not the same.
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }

            start++;
            end--;
        }

        return true;
    }

    public String firstPalindrome(String[] words) {
        for (String s : words) {
            if (isPalindrome(s)) {
                return s;
            }
        }

        return "";
    }
}
