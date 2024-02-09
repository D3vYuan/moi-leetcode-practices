package com.example.katana.Q001_Q500;

import java.util.Stack;

public class P150_Reverse_Polish_Notation {
    private String calculate(String left, String right, String operator) {
        int leftInteger = Integer.valueOf(left);
        int rightInteger = Integer.valueOf(right);
        int value = 0;
        System.out.println(String.format("Left: %s | Right: %s | Operator: %s", left, right, operator));
        switch (operator) {
            case "+":
                value = leftInteger + rightInteger;
                break;
            case "-":
                value = leftInteger - rightInteger;
                break;
            case "*":
                value = leftInteger * rightInteger;
                break;
            case "/":
                value = leftInteger / rightInteger;
                break;
            default:
                break;
        }

        return String.valueOf(value);
    }

    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        String operations = "+-*/";
        for (int i = 0; i < tokens.length; i++) {
            String currentToken = tokens[i];
            if (!operations.contains(currentToken)) {
                stack.push(currentToken);
            } else {
                String right = stack.pop();
                String left = stack.pop();
                String value = calculate(left, right, currentToken);
                System.out.println(String.format("Left: %s | Right: %s | Operator: %s | Value: %s", left, right,
                        currentToken, value));
                stack.push(value);
            }
        }
        return Integer.valueOf(stack.pop());
    }
}
