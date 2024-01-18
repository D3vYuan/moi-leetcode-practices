package com.example.solution.Q1501_Q2000;

public class P1716_Calculate_Money {

    /**
     * Reference:
     * https://leetcode.com/problems/calculate-money-in-leetcode-bank/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy
     * 1. Initialize the answer ans = 0 and monday = 1.
     * 2. While n > 0:
     * a. Iterate day from 0 until min(n, 7): Add monday + day to ans.
     * b. Subtract 7 from n.
     * c. Increment monday.
     * 3. Return ans.
     */

    public int totalMoney(int n) {
        int ans = 0;
        int monday = 1;

        while (n > 0) {
            for (int day = 0; day < Math.min(n, 7); day++) {
                ans += monday + day;
            }

            n -= 7;
            monday++;
        }

        return ans;
    }
}
