package com.example.solution.Q501_Q1000;

import java.util.Arrays;

public class P826_Most_Profit_Assigning_Work {
    /**
     * Reference:
     * https://leetcode.com/problems/most-profit-assigning-work/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize maxAbility as the maximum ability in the worker array.
     * 2. Initialize an array jobs of size maxAbility.
     * 3. Iterate a variable i from 0 to difficulty.size - 1:
     * a. If the difficulty at the current index i is less than or equal to the
     * worker's ability:
     * a1. Store the profit at index i at the difficulty[i] index of jobs array. If
     * a value already exists, take the maximum of both values.
     * 4. Iterate through all values in jobs:
     * a. Store the maximum of current and previous jobs values in the current jobs
     * index.
     * 5. Iterate through all abilities in the worker array:
     * a. Store maxProfit as jobs[ability] where ability denotes the ability of the
     * current worker.
     * b. Increment maxProfit to netProfit.
     * 6. Return netProfit.
     */

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        // Find maximum ability in the worker array.
        int maxAbility = Arrays.stream(worker).max().getAsInt();
        int[] jobs = new int[maxAbility + 1];

        for (int i = 0; i < difficulty.length; i++) {
            if (difficulty[i] <= maxAbility) {
                jobs[difficulty[i]] = Math.max(jobs[difficulty[i]], profit[i]);
            }
        }

        // Take maxima of prefixes.
        for (int i = 1; i <= maxAbility; i++) {
            jobs[i] = Math.max(jobs[i], jobs[i - 1]);
        }

        int netProfit = 0;
        for (int ability : worker) {
            netProfit += jobs[ability];
        }
        return netProfit;
    }
}
