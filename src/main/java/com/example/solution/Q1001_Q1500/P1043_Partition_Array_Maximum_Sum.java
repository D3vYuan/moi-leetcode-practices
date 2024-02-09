package com.example.solution.Q1001_Q1500;

import java.util.Arrays;

public class P1043_Partition_Array_Maximum_Sum {
    /**
     * Reference:
     * https://leetcode.com/problems/partition-array-for-maximum-sum/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Explanation:
     * 1. we have two options:
     * a. choose this element in the current subarray
     * b. choose to end the current subarray before this element and start another
     * one from this element.
     * 2. There are two key characteristics of this problem that we should note.
     * First, if we choose an element in a subarray, it cannot be reused in another
     * subarray, i.e., each decision we make is affected by the previous decisions
     * we have made. Second, the problem asks us to maximize the sum when choosing
     * the subarrays
     */

    /**
     * Strategy:
     * 1. Initialize an empty array dp with all values as -1 denoting the answer is
     * not calculated yet. Also, initialize N as the length of the array arr.
     * 2. Define the recursive function maxSum() which takes the array arr, an
     * integer k, memoization array dp and the current position as start.
     * 3. Base condition: If the start is more than or equal to the size of arr then
     * return 0.
     * 4. If we have already calculated the result before for this index, i.e.
     * dp[start] is not equal to -1, then return it instead of performing recursion.
     * 5. Iterate over the elements from start to start+k, or the end of the array
     * if there are fewer than k elements left, for each index i:
     * a. Store the maximum we have seen so far in the variable currMax.
     * b. Find the sum with the current index as the ending point of the subarray;
     * this will be equal to currMax * (i - start + 1) + maxSum(arr, k, dp, I + 1).
     * The term i - start + 1 is the length of the subarray from index start to i
     * inclusive.
     * c. Since we need the maximum sum of all our options, we take the max of all
     * the possible sums and store them as ans.
     * 6. After iterating over all possible subarrays, return ans and also store it
     * in the variable dp[start].
     * 7. Call maxSum() and return answer.
     */

    private int maxSum(int[] arr, int k, int[] dp, int start) {
        int totalLength = arr.length;

        if (start >= totalLength) {
            return 0;
        }

        // Return the already calculated answer.
        if (dp[start] != -1) {
            return dp[start];
        }

        int currMax = 0, ans = 0;
        int end = Math.min(totalLength, start + k);

        for (int i = start; i < end; i++) {
            currMax = Math.max(currMax, arr[i]);
            // Store the maximum of all options for the current subarray.
            int takeCurrentMax = currMax * (i - start + 1) + maxSum(arr, k, dp, i + 1);
            ans = Math.max(ans, takeCurrentMax);
        }

        // Store the answer to be reused.
        return dp[start] = ans;
    }

    private int runTopDown(int[] arr, int k) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);

        return maxSum(arr, k, dp, 0);
    }

    /**
     * Strategy:
     * 1. Initialize an empty memoization array dp with all elements as 0. Also,
     * initialize N as the length of the array arr.
     * 2. Iterate over the indices from N - 1 to 0 with start:
     * a. Iterate over the next k elements, or till the end of the array if there
     * are fewer than k elements, using i:
     * b. Store the maximum element so far as the variable currMax.
     * c. Store the value dp[start] as max(dp[start], dp[i + 1] + currMax * (i -
     * start + 1)).
     * 3. Return dp[0] after iterating over all elements.
     */
    private int runBottomUp(int[] arr, int k) {
        int N = arr.length;

        int[] dp = new int[N + 1];
        Arrays.fill(dp, 0);

        for (int start = N - 1; start >= 0; start--) {
            int currMax = 0;
            int end = Math.min(N, start + k);

            for (int i = start; i < end; i++) {
                currMax = Math.max(currMax, arr[i]);
                // Store the maximum of all options for the current subarray.
                dp[start] = Math.max(dp[start], dp[i + 1] + currMax * (i - start + 1));
            }
        }
        return dp[0];
    }

    public int maxSumAfterPartitioning(int[] arr, int k) {
        return runTopDown(arr, k);
        // return runBottomUp(arr, k);
    }
}
