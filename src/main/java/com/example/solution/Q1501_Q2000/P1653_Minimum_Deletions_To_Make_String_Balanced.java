package com.example.solution.Q1501_Q2000;

import java.util.Stack;

public class P1653_Minimum_Deletions_To_Make_String_Balanced {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-deletions-to-make-string-balanced/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize an empty stack char_stack and delete_count to 0.
     * 2. Traverse the string from left to right:
     * a. If the stack is not empty and the top of the stack is 'b' and the current
     * character is 'a', pop the stack and increment delete_count.
     * b. Otherwise, push the current character onto the stack.
     * 3. Return delete_count.
     */

    public int minimumDeletions(String s) {
        int n = s.length();
        Stack<Character> charStack = new Stack<>();
        int deleteCount = 0;

        // Iterate through each character in the string
        for (int i = 0; i < n; i++) {
            // If stack is not empty, top of stack is 'b',
            // and current char is 'a'
            if (!charStack.isEmpty() &&
                    charStack.peek() == 'b' &&
                    s.charAt(i) == 'a') {
                charStack.pop(); // Remove 'b' from stack
                deleteCount++; // Increment deletion count
            } else {
                charStack.push(s.charAt(i)); // Push current character onto stack
            }
        }

        return deleteCount;
    }
}
