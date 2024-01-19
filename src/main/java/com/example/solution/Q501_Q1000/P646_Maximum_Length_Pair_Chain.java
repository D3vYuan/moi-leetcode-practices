package com.example.solution.Q501_Q1000;

import java.util.Arrays;

public class P646_Maximum_Length_Pair_Chain {
    /**
     * Reference:
     * https://leetcode.com/problems/maximum-length-of-pair-chain/editorial/?envType=daily-question&envId=2024-01-19
     */

    /**
     * Strategy:
     * 1. Create an integer variable n to store the number of pairs in pairs.
     * 2. Sort pairs based on the first element (we could sort on the second element
     * as well).
     * 3. Create an integer array memo of size n where memo[i] will store the
     * longest length of the chain starting from the ith pair and including it.
     * 4. Create an integer variable ans = 0.
     * 5. Select every pair from i = 0 to n - 1 as the starting pair of the chain
     * and take the maximum out of it in ans. To find the longest chain starting
     * from the pair at index i, we call the recursive method longestPairChain which
     * takes four parameters: i of the starting pair, pairs, n, and memo. We perform
     * the following in this method:
     * a. If we have already computed the length of the longest chain starting from
     * pair at i, we simply return memo[i].
     * b. Set memo[i] = 1 as the current pair can always be selected as the only
     * pair in the chain.
     * c. Iterate over all the pairs from j = i + 1 to n - 1 and recursively find
     * the longest chain that can be formed by going to the jth pair. We perform
     * memo[i] = max(memo[i], 1 + longestPairChain(j, pairs, n, memo)) to figure and
     * store the length of the longest chain starting with ith pair.
     * 6. Return ans.
     */
    private int dp(int i, int[][] pairs, int n, int[] memo) {
        if (memo[i] != 0) {
            return memo[i];
        }
        memo[i] = 1;

        for (int j = i + 1; j < n; j++) {
            if (pairs[i][1] < pairs[j][0]) {
                memo[i] = Math.max(memo[i], 1 + dp(j, pairs, n, memo));
            }
        }

        return memo[i];
    }

    private int topDown(int[][] pairs) {
        int n = pairs.length;
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int[] memo = new int[n];

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp(i, pairs, n, memo));
        }

        return ans;
    }

    /**
     * Strategy:
     * 1. Create an integer variable n to store the number of pairs in pairs.
     * 2. Sort pairs based on the first element (we could sort on the second element
     * as well).
     * 3. Create an integer array dp of size n where dp[i] will store the longest
     * length of the chain starting from the ith pair and including it. We
     * initialize all the elements in dp to 1.
     * 4. Create an integer variable ans = 1 that stores the answer to the problem.
     * As we have at least one pair in the input, we initialize it to 1.
     * 5. We iterate using two loops. The outer loops run from i = n - 1 to 0 and
     * the inner loop runs from j = i + 1 to n - 1:
     * a. If jth pair can be selected as the next pair after ith pair, i.e.,
     * pairs[i][1] < pairs[j][0], we update dp[i] = max(dp[i], 1 + dp[j]).
     * b. After the completion of the inner loop, we update our answer with ans =
     * max(ans, dp[i]).
     * 6. Return ans.
     */
    private int bottomUp(int[][] pairs) {
        int n = pairs.length;
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int ans = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (pairs[i][1] < pairs[j][0]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public int findLongestChain(int[][] pairs) {
        return topDown(pairs);
        // return bottomUp(pairs);
    }
}
