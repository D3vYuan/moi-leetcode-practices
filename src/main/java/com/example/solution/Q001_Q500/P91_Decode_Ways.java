package com.example.solution.Q001_Q500;

import java.util.HashMap;
import java.util.Map;

public class P91_Decode_Ways {
    /**
     * Reference:
     * https://leetcode.com/problems/decode-ways/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy:
     * 1.Enter recursion with the given string i.e. start with index 0.
     * 2. For the terminating case of the recursion we check for the end of the
     * string. If we have reached the end of the string we return 1.
     * 3. Every time we enter recursion it's for a substring of the original string.
     * For any recursion if the first character is 0 then terminate that path by
     * returning 0. Thus this path won't contribute to the number of ways.
     * 4. Memoization helps to reduce the complexity which would otherwise be
     * exponential. We check the dictionary memo to see if the result for the given
     * substring already exists.
     * 5. If the result is already in memo we return the result. Otherwise the
     * number of ways for the given string is determined by making a recursive call
     * to the function with index + 1 for next substring string and index + 2 after
     * checking for valid 2-digit decode. The result is also stored in memo with key
     * as current index, for saving for future overlapping subproblems.
     * 
     */

    private int solve(int index, String s, Map<Integer, Integer> dp) {
        // Have we already seen this substring?
        if (dp.containsKey(index)) {
            return dp.get(index);
        }

        // If you reach the end of the string
        // Return 1 for success.
        if (index == s.length()) {
            return 1;
        }

        // If the string starts with a zero, it can't be decoded
        if (s.charAt(index) == '0') {
            return 0;
        }

        if (index == s.length() - 1) {
            return 1;
        }

        int ans = solve(index + 1, s, dp); // For solving [0-9]
        if (Integer.parseInt(s.substring(index, index + 2)) <= 26) { // For solving [10-19], [20-26]
            ans += solve(index + 2, s, dp);
        }

        // Save for memoization
        dp.put(index, ans);

        return ans;
    }

    public int solveTopDown(String s) {
        Map<Integer, Integer> dp = new HashMap<>();
        return solve(0, s, dp);
    }

    /**
     * 
     * Strategy:
     * 1. If the string s is empty or null we return the result as 0.
     * 2. Initialize dp array. dp[0] = 1 to provide the baton to be passed.
     * 3. If the first character of the string is zero then no decode is possible
     * hence initialize dp[1] to 0, otherwise the first character is valid to pass
     * on the baton, dp[1] = 1.
     * 4. Iterate the dp array starting at index 2. The index i of dp is the i-th
     * character of the string s, that is character at index i-1 of s.
     * 5. We check if valid single digit decode is possible. This just means the
     * character at index s[i-1] is non-zero. Since we do not have a decoding for
     * zero. If the valid single digit decoding is possible then we add dp[i-1] to
     * dp[i]. Since all the ways up to (i-1)-th character now lead up to i-th
     * character too.
     * 6. We check if valid two digit decode is possible. This means the substring
     * s[i-2]s[i-1] is between 10 to 26. If the valid two digit decoding is possible
     * then we add dp[i-2] to dp[i].
     * 7. Once we reach the end of the dp array we would have the number of ways of
     * decoding string s.
     */
    public int solveBottomUp(String s) {
        // DP array to store subproblem results
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;

        // Ways to decode a string of size 1 is 1. Unless the string is '0'.
        // '0' doesn't have a single digit decode.
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i < dp.length; i++) {
            // Check if successful single digit decode is possible.
            if (s.charAt(i - 1) != '0') {
                dp[i] = dp[i - 1];
            }

            // Check if successful two digit decode is possible.
            int twoDigit = Integer.valueOf(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[s.length()];
    }

    public int numDecodings(String s) {
        return solveTopDown(s);
        // return solveBottomUp(s);
    }
}
