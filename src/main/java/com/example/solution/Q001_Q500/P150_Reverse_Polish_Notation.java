package com.example.solution.Q001_Q500;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

public class P150_Reverse_Polish_Notation {
    /**
     * Reference:
     * https://leetcode.com/problems/evaluate-reverse-polish-notation/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. Visit each operator, in linear order. Finding these can be done with a
     * linear search of the original list.
     * 2. Get the 2 most recently seen numbers that haven't yet been replaced. These
     * could be tracked using a Stack.
     */

    private static final Map<String, BiFunction<Integer, Integer, Integer>> OPERATIONS = new HashMap<>();

    static {
        OPERATIONS.put("+", (a, b) -> a + b);
        OPERATIONS.put("-", (a, b) -> a - b);
        OPERATIONS.put("*", (a, b) -> a * b);
        OPERATIONS.put("/", (a, b) -> a / b);
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (!OPERATIONS.containsKey(token)) {
                stack.push(Integer.valueOf(token));
                continue;
            }

            int number2 = stack.pop();
            int number1 = stack.pop();

            BiFunction<Integer, Integer, Integer> operation;
            operation = OPERATIONS.get(token);
            int result = operation.apply(number1, number2);
            stack.push(result);
        }

        return stack.pop();
    }
}
