package com.example.solution.Q001_Q500;

import java.util.LinkedList;

public class P402_Remove_K_Digits {
    /**
     * Reference:
     * https://leetcode.com/problems/remove-k-digits/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Iterating the sequence of digits from left to right, the main loop can be
     * broken down as follows:
     * a. for each digit, if the digit is less than the top of the stack, i.e. the
     * left neighbor of the digit, then we pop the stack, i.e. removing the left
     * neighbor. At the end, we push the digit to the stack.
     * b. . we repeat the above step (1) until any of the conditions does not hold
     * any more, e.g. the stack is empty (no more digits left). or in another case,
     * we have already removed k digits, therefore mission accomplished.
     */
    public String removeKdigits(String num, int k) {
        LinkedList<Character> stack = new LinkedList<Character>();

        for (char digit : num.toCharArray()) {
            while (stack.size() > 0 && k > 0 && stack.peekLast() > digit) {
                stack.removeLast();
                k -= 1;
            }
            stack.addLast(digit);
        }

        /* remove the remaining digits from the tail. */
        for (int i = 0; i < k; ++i) {
            stack.removeLast();
        }

        // build the final string, while removing the leading zeros.
        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        for (char digit : stack) {
            if (leadingZero && digit == '0')
                continue;
            leadingZero = false;
            ret.append(digit);
        }

        /* return the final string */
        if (ret.length() == 0)
            return "0";
        return ret.toString();
    }
}
