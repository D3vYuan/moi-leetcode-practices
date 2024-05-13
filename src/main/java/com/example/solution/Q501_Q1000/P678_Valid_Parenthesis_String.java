package com.example.solution.Q501_Q1000;

import java.util.Stack;

public class P678_Valid_Parenthesis_String {
    /**
     * Reference:
     * https://leetcode.com/problems/valid-parenthesis-string/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize two stacks: openBrackets to store indices of open brackets '(',
     * and asterisks to store indices of asterisks '*'.
     * 2. Iterate through the string s character by character:
     * a. If the current character is '(', push its index onto the openBrackets
     * stack.
     * b. If the current character is '*', push its index onto the asterisks stack.
     * c. If the current character is ')':
     * c1. If openBrackets is not empty, pop an element from it (removing the
     * matching open bracket).
     * c2. If asterisks is not empty, pop an element from asterisks (using an
     * asterisk to balance the closing bracket).
     * c3. If neither an open bracket nor an asterisk is available, return false.
     * 3. After iterating through the entire string, check if any remaining open
     * brackets and asterisks can balance each other:
     * a. While both openBrackets and asterisks are not empty:
     * a1. If the top element of openBrackets (representing an open bracket index)
     * is greater than the top element of asterisks (representing an asterisk
     * index), it means the open bracket appears after the asterisk, which cannot be
     * balanced, so return false.
     * a2. Otherwise, pop elements from both openBrackets and asterisks stacks
     * (matching an open bracket with an asterisk).
     * 4. If after the above step, openBrackets is empty, it means all open brackets
     * have been matched or balanced, so return true. Otherwise, return false
     * (unmatched open brackets are remaining).
     */
    public boolean checkValidString(String s) {
        // Stacks to store indices of open brackets and asterisks
        Stack<Integer> openBrackets = new Stack<>();
        Stack<Integer> asterisks = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // If current character is an open bracket, push its index onto the stack
            if (ch == '(') {
                openBrackets.push(i);
            }
            // If current character is an asterisk, push its index onto the stack
            else if (ch == '*') {
                asterisks.push(i);
                // current character is a closing bracket ')'
            } else {
                // If there are open brackets available, use them to balance the closing bracket
                if (!openBrackets.empty()) {
                    openBrackets.pop();
                    // If no open brackets are available, use an asterisk to balance the closing
                    // bracket
                } else if (!asterisks.isEmpty()) {
                    asterisks.pop();
                } else {
                    return false;
                }
            }
        }

        // Check if there are remaining open brackets and asterisks that can balance
        // them
        while (!openBrackets.isEmpty() && !asterisks.isEmpty()) {
            // If an open bracket appears after an asterisk, it cannot be balanced, return
            // false
            if (openBrackets.pop() > asterisks.pop()) {
                return false; // '*' before '(' which cannot be balanced.
            }
        }

        // If all open brackets are matched and there are no unmatched open brackets
        // left, return true
        return openBrackets.isEmpty();
    }
}
