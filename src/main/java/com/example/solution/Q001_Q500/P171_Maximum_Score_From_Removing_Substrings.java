package com.example.solution.Q001_Q500;

import java.util.Stack;

public class P171_Maximum_Score_From_Removing_Substrings {
    /**
     * Reference:
     * https://leetcode.com/problems/maximum-score-from-removing-substrings/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Main Method maximumGain:
     * a. Initialize totalScore to 0 to keep track of the accumulated points.
     * b. Determine highPriorityPair based on which of x or y is larger. If x > y,
     * it's "ab", otherwise "ba".
     * c. Set lowPriorityPair as the opposite of highPriorityPair.
     * d. Call removeSubstring with the original string and highPriorityPair.
     * e. Calculate the number of removed pairs (removedPairsCount) by comparing the
     * lengths of the original and processed strings, divided by 2.
     * f. Add to totalScore the product of removed pairs and the higher of x and y.
     * g. Call removeSubstring again with the result of the first pass and
     * lowPriorityPair.
     * h. Calculate the number of removed pairs in this second pass.
     * i. Add to totalScore the product of removed pairs and the lower of x and y.
     * j. Return totalScore.
     * 
     * 2. Helper Method removeSubstring:
     * a. Define a method removeSubstring which takes the input string input and the
     * substring to remove targetPair as parameters.
     * b. Initialize a stack charStack to store characters during processing.
     * c. Iterate over each character in input:
     * If the top of the stack and the current character combine to form the target
     * string, pop from the stack.
     * Else, push the current character onto the stack.
     * d. Form a string by popping each character in the stack, reverse it, and
     * return it.
     */

    public int maximumGain(String s, int x, int y) {
        int totalScore = 0;
        String highPriorityPair = x > y ? "ab" : "ba";
        String lowPriorityPair = highPriorityPair.equals("ab") ? "ba" : "ab";

        // First pass: remove high priority pair
        String stringAfterFirstPass = removeSubstring(s, highPriorityPair);
        int removedPairsCount = (s.length() - stringAfterFirstPass.length()) / 2;

        // Calculate score from first pass
        totalScore += removedPairsCount * Math.max(x, y);

        // Second pass: remove low priority pair
        String stringAfterSecondPass = removeSubstring(
                stringAfterFirstPass,
                lowPriorityPair);
        removedPairsCount = (stringAfterFirstPass.length() -
                stringAfterSecondPass.length()) /
                2;

        // Calculate score from second pass
        totalScore += removedPairsCount * Math.min(x, y);

        return totalScore;
    }

    private String removeSubstring(String input, String targetPair) {
        Stack<Character> charStack = new Stack<>();

        // Iterate through each character in the input string
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            // Check if current character forms the target pair with the top of the stack
            if (currentChar == targetPair.charAt(1) &&
                    !charStack.isEmpty() &&
                    charStack.peek() == targetPair.charAt(0)) {
                charStack.pop(); // Remove the matching character from the stack
            } else {
                charStack.push(currentChar);
            }
        }

        // Reconstruct the remaining string after removing target pairs
        StringBuilder remainingChars = new StringBuilder();
        while (!charStack.isEmpty()) {
            remainingChars.append(charStack.pop());
        }
        return remainingChars.reverse().toString();
    }
}
