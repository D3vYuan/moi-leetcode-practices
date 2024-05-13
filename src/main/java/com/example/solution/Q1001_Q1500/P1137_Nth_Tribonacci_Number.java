package com.example.solution.Q1001_Q1500;

import java.util.HashMap;
import java.util.Map;

public class P1137_Nth_Tribonacci_Number {
    /**
     * Reference:
     * https://leetcode.com/problems/n-th-tribonacci-number/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Create a hash map dp to store the value of computed tribonacci numbers,
     * initialized with the base cases dp[0] = 0, dp[1] = 1, dp[2] = 1.
     * 2. Let dfs(i) be the value of ith tribonacci numbers:
     * a. If i is in dp, return dp[i].
     * b. Otherwise, recursively solve answer = dfs(i - 1) + dfs(i - 2) + dfs(i - 3)
     * and set dp[i] = answer. Then return answer.
     * 3. Return dfs(n).
     */
    private Map<Integer, Integer> dp = new HashMap() {
        {
            put(0, 0);
            put(1, 1);
            put(2, 1);
        }
    };

    private int dfs(int i) {
        if (dp.containsKey(i)) {
            return dp.get(i);
        }

        int answer = dfs(i - 1) + dfs(i - 2) + dfs(i - 3);
        dp.put(i, answer);
        return answer;
    }

    public int tribonacci(int n) {
        return dfs(n);
    }
}
