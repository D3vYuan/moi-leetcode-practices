package com.example.solution.Q1501_Q2000;

import java.util.Stack;

public class P2000_Reverse_Prefix_Of_Word {
    /**
     * Reference:
     * https://leetcode.com/problems/reverse-prefix-of-word/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize the following:
     * a. A stack to store characters that need to be reversed.
     * b. A string or list result for building the reversed string.
     * c. A variable index for iterating through the characters in word.
     * 2. Loop through word until index reaches the end of word:
     * a. Push the character word[index] onto the stack.
     * b. If the current character equals ch:
     * b1. Pop each of the characters from the stack and add them to the result
     * b2. Increment index by 1 because we already added ch to the result.
     * b3. Add the rest of the characters from word to result.
     * b4. Return result and convert to a string if necessary.
     * c. Increment index by 1; we have not yet reached ch.
     * 3. Return word, which does not contain ch.
     */
    public String reversePrefix(String word, char ch) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        int index = 0;
        while (index < word.length()) {
            stack.push(word.charAt(index));
            // Found ch
            if (word.charAt(index) == ch) {
                // Add characters through ch to the result in reverse order
                while (!stack.isEmpty()) {
                    result.append(stack.pop());
                }
                index++;
                // Add the rest of the characters to result
                while (index < word.length()) {
                    result.append(word.charAt(index));
                    index++;
                }
                return result.toString();
            }
            index++;
        }

        return word;
    }
}
