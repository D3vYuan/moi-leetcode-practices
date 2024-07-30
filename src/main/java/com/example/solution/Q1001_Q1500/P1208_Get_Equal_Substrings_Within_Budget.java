package com.example.solution.Q1001_Q1500;

public class P1208_Get_Equal_Substrings_Within_Budget {
    /**
     * Reference: https://leetcode.com/problems/get-equal-substrings-within-budget/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize the variables:
     * a. maxLen to 0'; this will be the maximum length of a substring with a cost less than or equal to maxCost we have seen so far.
     * b. start to 0; this is the left end of the current substring.
     * c. currCost to 0; this will be the cost of converting the current window substring in s to t.
     * 
     * 2. Iterate over the indices from 0 to N - 1 and for each index i:
     * a. Add the cost to convert s[i] to t[i] to the variable currCost
     * b. Keep removing the elements from the left end by decrementing the cost required for the character at index start until currCost becomes less than or equal to maxCost.
     * c. Compare the length of the current window i - start + 1 with the maxLen and update it accordingly.
     * 
     * 3. Return maxLen.
     */
 

    public int equalSubstring(String s, String t, int maxCost) {
        int N = s.length();
        
        int maxLen = 0;
        // Starting index of the current substring
        int start = 0;
        // Cost of converting the current substring in s to t
        int currCost = 0;
        
        for (int i = 0; i < N; i++) {
            // Add the current index to the substring
            currCost += Math.abs(s.charAt(i) - t.charAt(i));
            
            // Remove the indices from the left end till the cost becomes less than allowed
            while (currCost > maxCost) {
                currCost -= Math.abs(s.charAt(start) - t.charAt(start));
                start++;
            }
            
            maxLen = Math.max(maxLen, i - start + 1);
        }
        
        return maxLen;
    }
}
