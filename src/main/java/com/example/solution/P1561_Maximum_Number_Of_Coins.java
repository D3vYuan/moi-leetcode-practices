package com.example.solution;

import java.util.Arrays;

public class P1561_Maximum_Number_Of_Coins {
    /**
     * Reference:
     * https://leetcode.com/problems/maximum-number-of-coins-you-can-get/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation
     * Notice that Bob will always get the nnn smallest piles, and the remaining
     * piles alternate between us and Alice. Of the remaining piles, Alice gets the
     * largest one, then we get the second largest one.
     * 
     * Strategy
     * 1. Sort piles.
     * 2. Initialize ans = 0.
     * 3. Iterate i over the indices of piles, starting from piles.length / 3 and
     * incrementing i by 2 per iteration:
     * a. Add piles[i] to ans.
     * 4. Return ans.
     */

    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int ans = 0;

        for (int i = piles.length / 3; i < piles.length; i += 2) {
            ans += piles[i];
        }

        return ans;
    }
}
