package com.example.solution.Q501_Q1000;

import java.util.HashMap;

public class P746_Min_Cost_Climbing_Stairs {

    /**
     * Reference:
     * https://leetcode.com/problems/min-cost-climbing-stairs/?envType=daily-question&envId=2024-01-19
     */
    public int minCostClimbingStairs(int[] cost) {
        return runTopDown(cost);
        // runBottomUp(cost);
    }

    /**
     * Strategy:
     * 1. Define an array minimumCost, where minimumCost[i] represents the minimum
     * cost of reaching the ith step. The array should be one element longer than
     * costs and start with all elements set to 0.
     * a. The reason the array should contain one additional element is because we
     * will treat the top floor as the step to reach.
     * 2. Iterate over the array starting at the 2nd index. The problem statement
     * says we are allowed to start at the 0th or 1st step, so we know the minimum
     * cost to reach those steps is 0.
     * 3. For each step, apply the recurrence relation - minimumCost[i] =
     * min(minimumCost[i - 1] + cost[i - 1], minimumCost[i - 2] + cost[i - 2]). As
     * you can see, as we populate minimumCost, it becomes possible to solve future
     * subproblems. For example, before solving the 5th and 6th steps we are
     * required to solve the 4th step.
     * 4. At the end, return the final element of minimumCost. Remember, we are
     * treating this "step" as the top floor that we need to reach.
     */
    private int runBottomUp(int[] cost) {
        // The array's length should be 1 longer than the length of cost
        // This is because we can treat the "top floor" as a step to reach
        int minimumCost[] = new int[cost.length + 1];

        // Start iteration from step 2, since the minimum cost of reaching
        // step 0 and step 1 is 0
        for (int i = 2; i < minimumCost.length; i++) {
            int takeOneStep = minimumCost[i - 1] + cost[i - 1];
            int takeTwoSteps = minimumCost[i - 2] + cost[i - 2];
            minimumCost[i] = Math.min(takeOneStep, takeTwoSteps);
        }

        // The final element in minimumCost refers to the top floor
        return minimumCost[minimumCost.length - 1];
    }

    /**
     * Strategy:
     * 1. Define a hash map memo, where memo[i] represents the minimum cost of
     * reaching the ith step.
     * 2. Define a function minimumCost, where minimumCost(i) will determine the
     * minimum cost to reach the ith step.
     * 3. In our function minimumCost, first check the base cases: return 0 when i
     * == 0 or i == 1. Next, check if the argument i has already been calculated and
     * stored in our hash map memo. If so, return memo[i]. Otherwise, use the
     * recurrence relation to calculate memo[i], and then return memo[i].
     * 4. Simply call and return minimumCost(cost.length). Once again, we can make
     * use of the trick from approach 1 where we treat the top floor as an extra
     * "step". Since cost is 0-indexed, cost.length will be an index 1 step above
     * the last element of cost.
     */
    private int minimumCost(int i, int[] cost, HashMap<Integer, Integer> memo) {
        // Base case, we are allowed to start at either step 0 or step 1
        if (i <= 1) {
            return 0;
        }

        // Check if we have already calculated minimumCost(i)
        if (memo.containsKey(i)) {
            return memo.get(i);
        }

        // If not, cache the result in our hash map and return it
        int downOne = cost[i - 1] + minimumCost(i - 1, cost, memo);
        int downTwo = cost[i - 2] + minimumCost(i - 2, cost, memo);
        memo.put(i, Math.min(downOne, downTwo));
        return memo.get(i);
    }

    private int runTopDown(int[] cost) {
        HashMap<Integer, Integer> memo = new HashMap<Integer, Integer>();
        return minimumCost(cost.length, cost, memo);
    }
}
