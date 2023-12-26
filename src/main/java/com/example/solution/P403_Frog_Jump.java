package com.example.solution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P403_Frog_Jump {
    /**
     * References:
     * https://leetcode.com/problems/frog-jump/editorial/?envType=daily-question&envId=2023-11-13
     * 
     */

    Map<Integer, Integer> mark = new HashMap<>();
    int dp[][] = new int[2001][2001];

    private boolean solve(int[] stones, int index, int prevJump) {
        // Reach last stone
        if (index == stones.length - 1) {
            return true;
        }

        // Return from dp if already solve
        if (dp[index][prevJump] != -1) {
            return dp[index][prevJump] == 1;
        }

        boolean ans = false;
        for (int nextJump = prevJump - 1; nextJump <= prevJump + 1; nextJump++) {
            if (nextJump > 0 && mark.containsKey(stones[index] + nextJump)) {
                ans = ans || solve(stones, mark.get(stones[index] + nextJump), nextJump);
            }
        }

        // Store result in dp
        dp[index][prevJump] = (ans ? 1 : 0);
        return ans;
    }

    /*
     * Strategy:
     * 1.Create a map mark between the stone position to its index .
     * 2. Start with index as 0 and prevJump as 0. Our first jump would always be of
     * the 1 step, as the possible jumps are {-1, 0, 1}. And we will only consider
     * positive steps jumps to make sure the frog always jumps in the forward
     * direction.
     * 3. If index = N - 1, it implies that the frog has reached the end stone, and
     * hence we can return 1.
     * 4. Iterate over the three possibilities {prevJump - 1, prevJump, prevJump +
     * 1} as nextJump, and do the following:
     * a. The position of the frog currently is stones[index], after the jump it
     * would be stones[index] + nextJump.
     * b. Check if nextJump is greater than 0 because the frog cannot jump in the
     * backward direction, and staying at the same index won't change the outcome.
     * c. Check if, at this next position, there is a stone or not. If the entry in
     * mark is non-zero, it implies that the stone is there.
     * d. If all these conditions satisfy, then make the recursive call with
     * prevJump = nextJump and index = mark[stones[index] + nextJump].
     * 5. Find the logical OR from all three possibilities and store it in the
     * variable ans, initialized to false. Also, store this answer in the array dp
     * so that the next time we come across the same states we don't have to go into
     * recursion.
     * 6. Return ans.
     */

    private boolean solveTopDown(int[] stones) {
        // Mark to see if stones exist in location
        for (int i = 0; i < stones.length; i++) {
            mark.put(stones[i], i);
        }

        // Mark not calculated yet
        for (int i = 0; i < stones.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        return solve(stones, 0, 0);
    }

    /*
     * Strategy:
     * 1. Create a map mark between the stone position to its index .
     * 2. Initialize dp[0][0] to true.
     * 3. Iterate over the indices from 0 to N - 1, and over the previous jump
     * prevJump from 0 to N - 1, do the following for each pair:
     * a. If the value of dp[currIndex][prevJump] is true, then check the three
     * following possible positions of the frog, considering the previous jump as
     * prevJump
     * b. If the stones exist, mark the value at the corresponding indices in dp to
     * true.
     * 4. In the end, check if any value with index as N−1 is true; if yes,
     * return true; otherwise, false.
     */

    private boolean sovleBottomUp(int[] stones) {
        HashMap<Integer, Integer> mark = new HashMap<Integer, Integer>();
        boolean dp[][] = new boolean[2001][2001];

        for (int i = 0; i < stones.length; i++) {
            mark.put(stones[i], i);
        }

        dp[0][0] = true;
        for (int index = 0; index < stones.length; index++) {
            for (int prevJump = 0; prevJump <= stones.length; prevJump++) {
                // If stone exists, mark value with position and jump as 1
                if (dp[index][prevJump]) {
                    int currentStone = stones[index];
                    if (mark.containsKey(currentStone + prevJump)) {
                        int nextStonePosition = mark.get(currentStone + prevJump);
                        dp[nextStonePosition][prevJump] = true;
                    }

                    if (mark.containsKey(currentStone + prevJump + 1)) {
                        int nextStonePosition = mark.get(currentStone + prevJump + 1);
                        dp[nextStonePosition][prevJump + 1] = true;
                    }

                    if (mark.containsKey(currentStone + prevJump - 1)) {
                        int nextStonePosition = mark.get(currentStone + prevJump - 1);
                        dp[nextStonePosition][prevJump - 1] = true;
                    }
                }
            }
        }

        // If any value with index as n-1 is true, return true
        for (int index = 0; index < stones.length; index++) {
            if (dp[stones.length - 1][index]) {
                return true;
            }
        }

        return false;
    }

    public boolean canCross(int[] stones) {
        // return solveTopDown(stones);
        return sovleBottomUp(stones);
    }
}
