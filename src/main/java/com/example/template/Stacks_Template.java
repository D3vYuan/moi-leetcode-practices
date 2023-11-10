package com.example.template;

import java.util.Stack;

public class Stacks_Template {
    public int fn(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;

        for (int num : arr) {
            // for monotonic decreasing, just flip the > to <
            while (!stack.empty() && stack.peek() > num) {
                // do logic
                stack.pop();
            }

            stack.push(num);
        }

        return ans;
    }
}
