package com.example.katana.Q501_Q1000;

public class P746_Min_Cost_Climbing_Stairs {
    private int dp(int currentStep, int remainingStep, int[] cost, int[] memo) {
        if (currentStep >= cost.length || remainingStep < 0) {
            return 0;
        }

        if (memo[currentStep] != 0) {
            return memo[currentStep];
        }

        int take1Step = cost[currentStep] + dp(currentStep + 1, remainingStep - 1, cost, memo);
        int take2Step = cost[currentStep] + dp(currentStep + 2, remainingStep - 2, cost, memo);

        memo[currentStep] = Math.min(take1Step, take2Step);
        return memo[currentStep];
    }

    public int minCostClimbingStairs(int[] cost) {
        int[] memo = new int[cost.length];
        int startFrom0 = dp(0, cost.length, cost, memo);
        int startFrom1 = dp(1, cost.length, cost, memo);
        return Math.min(startFrom0, startFrom1);
    }
}
