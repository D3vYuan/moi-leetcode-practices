package com.example.solution.Q501_Q1000;

public class P647_Palindromic_Substrings {
    /**
     * Reference:
     * https://leetcode.com/problems/palindromic-substrings/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. Define the dynamic programming state. This is the result that gets reused
     * in further computations.
     * Define our state dp(i, j), which tells us whether the substring composed of
     * the ith to the jth characters of the input string, is a palindrome or not.
     * Thus, the answer to our problem lies in counting all substrings whose state
     * is true.
     * 2. Identify the base cases. There are essentially two base-cases:
     * a. Single letter substrings are palindromes by definition. i.e. dp(i,i)=true
     * b. Double letter substrings composed of the same character are palindromes.
     * i.e.
     * dp(i,i+1)={true if si = si+1
     * false otherwise}
     * 3. Identify the optimal substructure. A string is considered a palindrome if:
     * a. Its first and last characters are equal, and
     * b. The rest of the string (excluding the boundary characters) is also a
     * palindrome.
     * This optimal substructure can be formulated into a recurrence rule:
     * dp(i,j)={true if dp(i+1,j−1)∧(si=sj)
     * false otherwise
     * 4. Identify overlapping sub-problems and compute them only once. The optimal
     * substructure mentioned above ensures that the state for a string depends only
     * on the state for a single substring. If we compute (and save) the states for
     * all smaller strings first, larger strings can be processed by reusing
     * previously saved states. The base cases that we have identified already
     * define states for single and double letter strings. We can use those to
     * compute states for three character (and subsequently larger) strings.
     * 5. The answer is found by counting all states that evaluate true. Since each
     * state tells whether a unique substring is a palindrome or not, counting true
     * states provides us the number of palindromic substrings.
     * 
     */
    public int countSubstrings(String s) {
        int n = s.length(), ans = 0;

        if (n <= 0)
            return 0;

        boolean[][] dp = new boolean[n][n];

        // Base case: single letter substrings
        for (int i = 0; i < n; ++i, ++ans)
            dp[i][i] = true;

        // Base case: double letter substrings
        for (int i = 0; i < n - 1; ++i) {
            dp[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
            ans += (dp[i][i + 1] ? 1 : 0);
        }

        // All other cases: substrings of length 3 to n
        for (int len = 3; len <= n; ++len)
            for (int i = 0, j = i + len - 1; j < n; ++i, ++j) {
                dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
                ans += (dp[i][j] ? 1 : 0);
            }

        return ans;
    }
}
