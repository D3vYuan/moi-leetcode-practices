package com.example.solution.Q1501_Q2000;

public class P1903_Largest_Odd_Number {
    /**
     * Reference:
     * https://leetcode.com/problems/largest-odd-number-in-string/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy
     * 1. Iterate i starting from nums.length - 1 to 0:
     * a.. Cast nums[i] to an integer and take its value mod 2. If the result is not
     * 0:
     * b.. Return the substring of nums starting at index 0 and ending with index i.
     * 4. Return "".
     */
    public String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            if (Character.getNumericValue(num.charAt(i)) % 2 != 0) {
                return num.substring(0, i + 1);
            }
        }

        return "";
    }
}
