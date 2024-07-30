package com.example.solution.Q1001_Q1500;

import java.util.Stack;

public class P1190_Reverse_Substrings_Between_Each_Parantheses {
    /**
     * Reference:
     * https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize an empty stack openParenthesesIndices to track reversal start
     * points and an empty string result to build the output.
     * 2. For each character currentChar in the input string:
     * a. If '(', push result's length to openParenthesesIndices to mark a potential
     * reversal start.
     * b. If ')', pop from openParenthesesIndices and reverse result from the popped
     * index to perform the required reversal.
     * c. Otherwise, append currentChar to result to build the string.
     * 3. Return result as the final string with all reversals applied.
     */

    public String reverseParentheses(String s) {
        Stack<Integer> openParenthesesIndices = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (char currentChar : s.toCharArray()) {
            if (currentChar == '(') {
                // Store the current length as the start index for future reversal
                openParenthesesIndices.push(result.length());
            } else if (currentChar == ')') {
                int start = openParenthesesIndices.pop();
                // Reverse the substring between the matching parentheses
                reverse(result, start, result.length() - 1);
            } else {
                // Append non-parenthesis characters to the processed string
                result.append(currentChar);
            }
        }

        return result.toString();
    }

    private void reverse(StringBuilder sb, int start, int end) {
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start++, sb.charAt(end));
            sb.setCharAt(end--, temp);
        }
    }
}
