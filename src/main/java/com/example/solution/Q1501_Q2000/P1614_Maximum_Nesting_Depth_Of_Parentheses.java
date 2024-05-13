package com.example.solution.Q1501_Q2000;

import java.util.Stack;

public class P1614_Maximum_Nesting_Depth_Of_Parentheses {
    /**
     * Reference:
     * https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a variable ans to 0. This will store the maximum nesting depth
     * so far.
     * 2. Initialize an empty stack st.
     * 3. Iterate over the characters in the string s, for each character c:
     * a. If c is equal to (, add it to the stack st.
     * b. If c is equal to ), pop one element from the stack st.
     * c. Update ans as the max of ans and st.size().
     * 4. Return ans.
     */

    public int maxDepth(String s) {
        int ans = 0;

        Stack<Character> st = new Stack<Character>();
        for (Character c : s.toCharArray()) {
            if (c == '(') {
                st.push(c);
            } else if (c == ')') {
                st.pop();
            }

            ans = Math.max(ans, st.size());
        }

        return ans;
    }
}
