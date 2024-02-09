package com.example.katana.Q001_Q500;

import java.util.Arrays;

public class P198_House_Robber {
    private int dp(int currentHouse, int[] nums, int[] memo) {
        if (currentHouse >= nums.length) {
            return 0;
        }

        if (memo[currentHouse] != -1) {
            return memo[currentHouse];
        }

        int rob = nums[currentHouse] + dp(currentHouse + 2, nums, memo); // skip a house
        int noRob = dp(currentHouse + 1, nums, memo); // go to next house
        System.out.println(String.format("Rob: %d | No Rob: %d", rob, noRob));
        memo[currentHouse] = Math.max(rob, noRob);
        return memo[currentHouse];
    }

    public int rob(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dp(0, nums, memo);
    }
}
