package com.example.solution.Q001_Q500;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Reference:
 * https://leetcode.com/problems/word-break-ii/editorial/?envType=daily-question&envId=2024-05-05
 * 
 * Strategy:
 * wordBreak Function:
 * 1. Convert the wordDict array into an unordered set wordSet for efficient
 * lookups.
 * 2. Initialize an empty unordered map memoization to store the results of
 * subproblems.
 * 3. Call the dfs function with the input string s, wordSet, and memoization.
 * 
 * dfs Function:
 * 1. Check if the answer for the current remainingStr(the remaining part of the
 * string to be processed) are already in memoization. If so, return them.
 * 2. Base Case: If remainingStr is empty, it means that all characters have
 * been processed. An empty string represents a valid sentence so return an
 * array containing the empty string.
 * 3. Initialize an empty array results.
 * 4. Iterate from 1 to the length of remainingStr:
 * a. Extract the substring currentWord from 0 to i to check if it is a valid
 * word.
 * b. If currentWord is found in wordSet:
 * b1. Recursively call dfs with remainingStr.substr(i), wordSet, and
 * memoization.
 * b2. Append currentWord and the recursive results to results(with a space if
 * needed) to form valid sentences.
 * 5. Store the results for remainingStr in memoization.
 * 6. Return results.
 */

public class P140_Word_Break_2 {
    // Main function to break the string into words
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Map<String, List<String>> memoization = new HashMap<>();
        return dfs(s, wordSet, memoization);
    }

    // Depth-first search function to find all possible word break combinations
    private List<String> dfs(
            String remainingStr,
            Set<String> wordSet,
            Map<String, List<String>> memoization) {
        // Check if result for this substring is already memoized
        if (memoization.containsKey(remainingStr)) {
            return memoization.get(remainingStr);
        }

        // Base case: when the string is empty, return a list containing an empty string
        if (remainingStr.isEmpty())
            return Collections.singletonList("");
        List<String> results = new ArrayList<>();
        for (int i = 1; i <= remainingStr.length(); ++i) {
            String currentWord = remainingStr.substring(0, i);
            // If the current substring is a valid word
            if (wordSet.contains(currentWord)) {
                for (String nextWord : dfs(
                        remainingStr.substring(i),
                        wordSet,
                        memoization)) {
                    // Append current word and next word with space in between if next word exists
                    results.add(
                            currentWord + (nextWord.isEmpty() ? "" : " ") + nextWord);
                }
            }
        }
        // Memoize the results for the current substring
        memoization.put(remainingStr, results);
        return results;
    }
}
