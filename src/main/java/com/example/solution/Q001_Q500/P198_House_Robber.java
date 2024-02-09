package com.example.solution.Q001_Q500;

import java.util.Arrays;

public class P198_House_Robber {

    /**
     * Reference:
     * https://leetcode.com/problems/house-robber/editorial/?envType=daily-question&envId=2024-01-19
     */

    /**
     * Strategy:
     * 1. We define a function called robFrom() which takes the index of the house
     * that the robber currently has to examine. Also, this function takes the nums
     * array which is required during the calculations
     * 2. At each step of our recursive call, the robber has two options. He can
     * either rob the current house or not.
     * a. If the robber chooses to rob the current house, then he have to skip the
     * next house i.e robFrom(i + 2, nums). The answer here would be whatever is
     * returned by robFrom(i + 2, nums) in addition to the amount that the robber
     * will get by robbing the current house i.e. nums[i].
     * b. Otherwise, he can simply move on to the house next door and return
     * whatever profit he will make in the sub-problem i.e. robFrom(i + 1, nums).
     * 3. We need to find, cache, and return the maximum of these two options at
     * each step.
     * 4. robFrom(0, nums) will give us the answer to the entire problem.
     */
    private int robFrom(int i, int[] nums, int[] memo) {

        // No more houses left to examine.
        if (i >= nums.length) {
            return 0;
        }

        // Return cached value.
        if (memo[i] > -1) {
            return memo[i];
        }

        // Recursive relation evaluation to get the optimal answer.
        int ans = Math.max(robFrom(i + 1, nums, memo), robFrom(i + 2, nums, memo) + nums[i]);

        // Cache for future use.
        memo[i] = ans;
        return ans;
    }

    private int topDown(int[] nums) {
        int[] memo = new int[100];

        // Fill with sentinel value representing not-calculated recursions.
        Arrays.fill(memo, -1);

        return robFrom(0, nums, memo);
    }

    /**
     * Strategy:
     * 1. We will make use of two variables here called robNext and robNextPlusOne
     * which at any point will represent the optimal solution for maxRobbedAmount[i
     * + 1] and maxRobbedAmount[i + 2]. These are the two values that we need to
     * calculate the current value.
     * 2. We set robNextPlusOne to 0 since this means the robber doesn't have any
     * houses left to rob, thus zero profit. Additionally, we set robNext to nums[N
     * - 1] because in this case there is only one house to rob which is the last
     * house. Robbing it will yield the maximum profit.
     * Note We are assuming that robNextPlusOne is the value of maxRobbedAmount[N]
     * and robNext is maxRobbedAmount[N-1] initially.
     * 3. We iterate from N - 2 down to 0 and set current = max(robNext,
     * robNextPlusOne + nums[i]). Note that this is the same as the dynamic
     * programming solution except that we are making use of our variables and not
     * entries from the table.
     * 4. Set robNextPlusOne = robNext.
     * 5. Set robNext = current. Updating the two variables is important as we
     * iterate down to 0.
     * 6. We return the value in robNext.
     */
    private int bottomUp(int[] nums) {
        int N = nums.length;

        // Special handling for empty array case.
        if (N == 0) {
            return 0;
        }

        int robNext, robNextPlusOne;

        // Base case initializations.
        robNextPlusOne = 0;
        robNext = nums[N - 1];

        // DP table calculations. Note: we are not using any
        // table here for storing values. Just using two
        // variables will suffice.
        for (int i = N - 2; i >= 0; --i) {

            // Same as the recursive solution.
            int current = Math.max(robNext, robNextPlusOne + nums[i]);

            // Update the variables
            robNextPlusOne = robNext;
            robNext = current;
        }

        return robNext;
    }

    public int rob(int[] nums) {
        return topDown(nums);
        // return bottomUp(nums);
    }
}
