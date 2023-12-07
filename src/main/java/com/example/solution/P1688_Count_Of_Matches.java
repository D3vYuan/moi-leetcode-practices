package com.example.solution;

public class P1688_Count_Of_Matches {
    /**
     * Reference:
     * https://leetcode.com/problems/count-of-matches-in-tournament/description/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy
     * 1. Initialize the answer ans = 0.
     * 2. While n > 1:
     * a. If n % 2 == 0:
     * a1. Add n / 2 to ans.
     * a2. Set n to n / 2.
     * b. Else:
     * b1. Add (n - 1) / 2 to ans.
     * b2. Set n to (n - 1) / 2 + 1.
     * 3. Return ans.
     */

    public int numberOfMatches(int n) {
        int ans = 0;
        while (n > 1) {
            if (n % 2 == 0) {
                ans += n / 2;
                n = n / 2;
            } else {
                ans += (n - 1) / 2;
                n = (n - 1) / 2 + 1;
            }
        }
        return ans;
    }
}
