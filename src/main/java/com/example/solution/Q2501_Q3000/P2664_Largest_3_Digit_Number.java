package com.example.solution.Q2501_Q3000;

import java.util.Arrays;
import java.util.List;

public class P2664_Largest_3_Digit_Number {
    /**
     * Reference:
     * https://leetcode.com/problems/largest-3-same-digit-number-in-string/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy
     * 1. Create a sameDigitNumbers array containing all the same 3-digit numbers
     * from "999" to "000" in decreasing order.
     * 2. Create a method contains(sameDigitNumber, num) to check whether the string
     * num only contains sameDigitNumber.
     * a. In this method, iterate over string num from index idx = 0 till num.size()
     * - 3 and return true if for any index idx, characters at indices idx, (idx +
     * 1), and (idx + 2) are sameDigitNumber. Otherwise, return false.
     * 3. Iterate over each sameDigitNumber of the sameDigitNumbers array, if for
     * any sameDigitNumber, contains(sameDigitNumber, num) returns true, return
     * string sameDigitNumber.
     * 4. Otherwise, return an empty string.
     * 
     */

    private List<String> sameDigitNumbers = Arrays.asList("999", "888", "777", "666", "555", "444", "333", "222", "111",
            "000");

    // Check whether the 'num' string contains the 'sameDigitNumber' string or not.
    private boolean contains(String sameDigitNumber, String num) {
        for (int index = 0; index <= num.length() - 3; ++index) {
            if (num.charAt(index) == sameDigitNumber.charAt(0) &&
                    num.charAt(index + 1) == sameDigitNumber.charAt(1) &&
                    num.charAt(index + 2) == sameDigitNumber.charAt(2)) {
                return true;
            }
        }
        return false;
    }

    public String largestGoodInteger(String num) {
        // Iterate on all 'sameDigitNumbers' and check if the string 'num' contains it.
        for (String sameDigitNumber : sameDigitNumbers) {
            if (contains(sameDigitNumber, num)) {
                // Return the current 'sameDigitNumbers'.
                return sameDigitNumber;
            }
        }
        // No 3 consecutive same digits are present in the string 'num'.
        return "";
    }
}
