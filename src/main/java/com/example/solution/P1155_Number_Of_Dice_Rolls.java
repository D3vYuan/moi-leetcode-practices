package com.example.solution;

public class P1155_Number_Of_Dice_Rolls {
    /**
     * Reference:
     * https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation:
     * 1. To do this, we can iterate over the numbers 1 to k for each dice and add
     * it to a variable to keep the current sum value and recursively move to the
     * next dice. In the end, after iterating over all the dice, we can check if the
     * current sum is equal to the target. If they are equal, we will increment the
     * variable representing the number of possible ways.
     * 2. The first parameter is the index of the die that we are currently
     * considering as we traverse the dice. Secondly, we need to keep track of the
     * sum of values that we chose for the previous dice.
     * 
     * Strategy:
     * 1. Start with:
     * a. The dice index diceIndex is 0; this is the index of the die we are
     * currently considering.
     * b. The sum of numbers on the previous dice currSum as 0.
     * 2. Initialize the variable ways to 0. Iterate over the values from 1 to k,
     * for each value i:
     * a. If the current die can have value i, i.e., currSum after adding i will not
     * exceed the target value. Then update the value of currSum and recursively
     * move to the next die. Add the value returned by this recursive call to ways
     * b. Else, if this value is not possible, then break from the loop as the
     * greater values will not satisfy the above condition as well.
     * 3. Base cases:
     * a. If we have iterated over all the dice, i.e., diceIndex = n, then check if
     * the currSum is equal to target or not.
     * 4. Return the value ways and also store it in the memoization table memo
     * corresponding to the current state, which is defined by diceIndex and
     * currSum.
     * 
     */

    final int MOD = 1000000007;

    private int solve(Integer[][] dp, int diceIndex, int numDice, int diceSum, int target, int diceFaces) {
        // All the n dice are traversed, the sum must be equal to target for valid
        // combination
        if (diceIndex == numDice) {
            return diceSum == target ? 1 : 0;
        }

        // We have already calculated the answer so no need to go into recursion
        if (dp[diceIndex][diceSum] != null) {
            return dp[diceIndex][diceSum];
        }

        int ways = 0;
        // Iterate over the possible face value for current dice
        for (int i = 1; i <= Math.min(diceFaces, target - diceSum); i++) {
            ways = (ways + solve(dp, diceIndex + 1, numDice, diceSum + i, target, diceFaces)) % MOD;
        }

        dp[diceIndex][diceSum] = ways;
        return dp[diceIndex][diceSum];
    }

    public int solveTopDown(int n, int k, int target) {
        Integer[][] dp = new Integer[n + 1][target + 1];
        return solve(dp, 0, n, 0, target, k);
    }

    /**
     * Strategy:
     * 1. Initialize memo[n][target] to 1 and all other values to 0.
     * 2. Iterate over the diceIndex from n - 1 to 0; for each dice value, iterate
     * over the currSum from 0 to target. For each such state corresponding to
     * diceIndex and currSum:
     * a. Initialize ways to 0.
     * b. Iterate over values from 1 to K as i and for each valid value of i (adding
     * i to currSum doesn't exceed target) add the value memo[diceIndex + 1][currSum
     * + i] to ways.
     * c. Assign the ways to memo[diceIndex][currSum].
     * 3. Return the value memo[0][0].
     * 
     */
    public int solveBottomUp(int n, int k, int target) {
        int[][] dp = new int[n + 1][target + 1];
        // initialize the base case
        dp[n][target] = 1;

        for (int diceIndex = n - 1; diceIndex >= 0; diceIndex--) {
            for (int diceSum = 0; diceSum <= target; diceSum++) {
                int ways = 0;
                for (int i = 1; i <= Math.min(k, target - diceSum); i++) {
                    ways = (ways + dp[diceIndex + 1][diceSum + i]) % MOD;
                }

                dp[diceIndex][diceSum] = ways;
            }
        }

        return dp[0][0];
    }

    public int numRollsToTarget(int n, int k, int target) {
        return solveTopDown(n, k, target);
        // solveBottomUp(n, k, target);
    }
}
