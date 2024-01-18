package com.example.solution.Q1001_Q1500;

public class P1458_Max_Dot_Product_Two_Subsequences {

    /**
     * Strategy:
     * 1. Check the following special cases:
     * a. If max(nums1) < 0 and min(nums2) > 0, then return max(nums1) * min(nums2).
     * b. If min(nums1) > 0 and max(nums2) < 0, then return min(nums1) * max(nums2).
     * 2. Create a 2d table dp of size (nums1.length + 1) * (nums2.length + 1).
     * 3. Iterate i from nums1.length - 1 until 0:
     * a. Iterate j from nums2.length - 1 until 0:
     * a1. Set use = nums1[i] * nums2[j] + dp[i + 1][j + 1]. This is the dot product
     * from using the current numbers.
     * a2. Find maximum of use, dp[i + 1][j], dp[i][j + 1]. Store it in dp[i][j].
     * 4. Return dp[0][0], the answer to the original problem.
     */
    private int bottomUp(int[] nums1, int[] nums2) {
        int firstMax = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;

        for (int num : nums1) {
            firstMax = Math.max(firstMax, num);
            firstMin = Math.min(firstMin, num);
        }

        for (int num : nums2) {
            secondMax = Math.max(secondMax, num);
            secondMin = Math.min(secondMin, num);
        }

        if (firstMax < 0 && secondMin > 0) {
            return firstMax * secondMin;
        }

        if (firstMin > 0 && secondMax < 0) {
            return firstMin * secondMax;
        }

        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = nums1.length - 1; i >= 0; i--) {
            for (int j = nums2.length - 1; j >= 0; j--) {
                int use = nums1[i] * nums2[j] + dp[i + 1][j + 1];
                dp[i][j] = Math.max(use, Math.max(dp[i + 1][j], dp[i][j + 1]));
            }
        }

        return dp[0][0];
    }

    /**
     * Reference:
     * https://leetcode.com/problems/max-dot-product-of-two-subsequences/editorial/?envType=daily-question&envId=2024-01-17
     * 
     * Explanation:
     * 1. Base Case: when i == nums1.length or j == nums2.length. In this case, one
     * of the arrays has been exhausted and it is impossible to have any dot
     * product. Thus, we will return 0.
     * 2. we calculate a given state dp(i, j)? There are 3 options at each state.
     * a. multiply the numbers at nums[i] and nums[j] together. This will give us
     * nums1[i] * nums2[j], and then we move to the next indices. Thus, this option
     * gives us a dot product of nums1[i] * nums2[j] + dp(i + 1, j + 1)
     * b. move forward in nums1. This gives us a dot product of dp(i + 1, j)
     * c. move forward in nums2. This gives us a dot product of dp(i, j + 1)
     * 3. When all the elements in nums1 are negative and all the elements in nums2
     * are positive (or vice-versa), and no matter what operation is performed we
     * get a negative value, then we would prefer to not perform any operation and
     * get 0
     * 
     * Strategy:
     * 1. Check the following special cases:
     * a. If max(nums1) < 0 and min(nums2) > 0, then return max(nums1) * min(nums2).
     * b. If min(nums1) > 0 and max(nums2) < 0, then return min(nums1) * max(nums2).
     * 2. Define a memoized function dp(i, j):
     * a. If i == nums1.length or j == nums2.length, then return 0.
     * b. Set use = nums1[i] * nums2[j] + dp(i + 1, j + 1). This is the dot product
     * from using the current numbers.
     * c. Return the maximum of use, dp(i + 1, j), dp(i, j + 1).
     * 3. Return dp(0, 0), the answer to the original problem.
     */
    int[][] memo;

    private int dp(int i, int j, int[] nums1, int[] nums2) {
        if (i == nums1.length || j == nums2.length) {
            return 0;
        }

        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int use = nums1[i] * nums2[j] + dp(i + 1, j + 1, nums1, nums2);
        memo[i][j] = Math.max(use, Math.max(dp(i + 1, j, nums1, nums2), dp(i, j + 1, nums1, nums2)));
        return memo[i][j];
    }

    private int topDown(int[] nums1, int[] nums2) {
        int firstMax = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;

        for (int num : nums1) {
            firstMax = Math.max(firstMax, num);
            firstMin = Math.min(firstMin, num);
        }

        for (int num : nums2) {
            secondMax = Math.max(secondMax, num);
            secondMin = Math.min(secondMin, num);
        }

        if (firstMax < 0 && secondMin > 0) {
            return firstMax * secondMin;
        }

        if (firstMin > 0 && secondMax < 0) {
            return firstMin * secondMax;
        }

        memo = new int[nums1.length][nums2.length];
        return dp(0, 0, nums1, nums2);
    }

    public int maxDotProduct(int[] nums1, int[] nums2) {
        return topDown(nums1, nums2);
        // return bottomUp(nums1, nums2);
    }
}
