package com.example.solution.Q1501_Q2000;

import java.util.Stack;

public class P1544_Make_The_String_Great {
    /**
     * Reference:
     * https://leetcode.com/problems/make-the-string-great/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize an empty stack stack.
     * 2. For each character currChar in s:
     * a. If currChar pairs with the last character in stack, remove the character
     * at the top of stack.
     * b. Otherwise, add currChar to stack.
     * 3. Once we have finished iterating, return the string concatenated by all the
     * remaining characters in stack.
     */

    public String makeGood(String s) {
        // Use stack to store the visited characters.
        Stack<Character> stack = new Stack<>();

        // Iterate over 's'.
        for (char currChar : s.toCharArray()) {
            // If the current character make a pair with the last character in the stack,
            // remove both of them. Otherwise, we the add current character to stack.
            if (!stack.isEmpty() && Math.abs(stack.lastElement() - currChar) == 32)
                stack.pop();
            else
                stack.add(currChar);
        }

        // Returns the string concatenated by all characters left in the stack.
        StringBuilder ans = new StringBuilder();
        for (char currChar : stack)
            ans.append(currChar);
        return ans.toString();
    }
}
