
package com.example.solution.Q001_Q500;

import java.util.ArrayList;
import java.util.List;

public class P131_Palindrome_Partitioning {
    /**
     * Reference:
     * https://leetcode.com/problems/palindrome-partitioning/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Explanation:
     * 1. Choose: Choose the potential candidate. Here, our potential candidates are
     * all substrings that could be generated from the given string.
     * 2. Constraint: Define a constraint that must be satisfied by the chosen
     * candidate. In this case, the constraint is that the string must be a
     * palindrome.
     * 3. Goal: We must define the goal that determines if have found the required
     * solution and we must backtrack. Here, our goal is achieved if we have reached
     * the end of the string.
     * 
     * Strategy:
     * 1. Iteratively generate all possible substrings beginning at index
     * start. The index end increments from
     * start until the end of the string.
     * 2. For each of the substrings generated, check if it is a palindrome.
     * 3. If the substring is a palindrome, the substring is a potential candidate.
     * Add the substring to the currentList and perform
     * a depth-first search on the remaining substring. If the current substring
     * ends at index end, end+1\text{end}+1end+1 becomes the
     * start index for the next recursive call.
     * 4. Backtrack if start index is greater than or equal to the
     * string length and add the currentList to the
     * result.
     */

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        dfs(0, result, new ArrayList<String>(), s);
        return result;
    }

    void dfs(
            int start,
            List<List<String>> result,
            List<String> currentList,
            String s) {
        if (start >= s.length())
            result.add(new ArrayList<String>(currentList));
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                // add current substring in the currentList
                currentList.add(s.substring(start, end + 1));
                dfs(end + 1, result, currentList, s);
                // backtrack and remove the current substring from currentList
                currentList.remove(currentList.size() - 1);
            }
        }
    }

    boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--))
                return false;
        }
        return true;
    }
}
