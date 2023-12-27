package com.example.solution;

import java.util.Arrays;

public class P1326_Minimum_Taps_To_Water_Garden {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation:
     * 1. Let dp be an array of length n+1, where n is the length of the garden. The
     * value dp[i] represents the minimum number of taps needed to water the garden
     * from position 0 to position i.
     * 2. initialize this array with a large value like infinity for each position.
     * By doing so, we indicate that no taps have been applied yet, and we can
     * consider these positions as "unreachable" until we find a tap that can water
     * them.
     * 3. we have a base case dp[0]. We set i=0 in the DP definition and obtain that
     * dp[0] is the minimum number of taps needed to water the garden from position
     * 0 to position 0 (the part of garden of zero length). Since we do not have to
     * open any taps to water a part of zero length, thus dp[0]=0.
     * 4. we iterate through each tap, one by one. For each tap, we identify its
     * leftmost and rightmost positions that can be reached and watered. The
     * leftmost position of the ith tap is tap_start=max⁡(0,i−ranges[i]) and the
     * rightmost one is tap_end=min⁡(n,i+ranges[i]).
     * 5. Thus if we open these dp[j] taps and the ith tap (dp[j]+1 taps in total),
     * we will water the part of the garden from position 0 to position tap_end. It
     * means that we can update dp[tap_end] with dp[j]+1
     * 6. At each position j, we compare dp[j]+1 (the minimum number of taps needed
     * at j plus one) with the current value of dp[tap_end]. If dp[j]+1 is smaller
     * than dp[tap_end], we update dp[tap_end] with dp[j]+1. By doing so, we ensure
     * that dp[tap_end] holds the optimal minimum number of taps needed to reach
     * tap_end from the previous positions
     * 7. After processing all the taps, we check the number of taps needed at the
     * last position of the garden. If the value is still infinity, it means that
     * the garden cannot be watered, and we return −1 to indicate this
     * 
     * Strategy:
     * 1. Declare an array dp of size n+1. Initialize it with infinite values (in
     * code, we use a large number 10^9 to represent infinity).
     * 2. Set dp[0] to 0 (the base case of the DP).
     * 3. Iterate i from 0 to n (through each tap from left to right).
     * a. Calculate the leftmost position reachable by the current tap as
     * tap_start=max⁡(0,i−ranges[i]).
     * b. And the rightmost position tap_end=min⁡(n,i+ranges[i]).
     * c. Iterate through the positions j from tap_start to tap_end (within the
     * tap's reach).
     * c1. Update dp[tap_end] with dp[j]+1 if it's smaller.
     * 4. If dp[n] is infinite, it means that it's impossible to water the entire
     * garden and we return −1.
     * 5. Return dp[n].
     * 
     */
    public int minTaps(int n, int[] ranges) {
        // Define an infinite value
        final int INF = Integer.MAX_VALUE - 1;

        // Create an array to store the minimum number of taps needed for each position
        int[] dp = new int[n + 1];
        Arrays.fill(dp, INF);

        // Initialize the starting position of the garden
        dp[0] = 0;

        for (int i = 0; i <= n; i++) {
            // Calculate the leftmost position reachable by the current tap
            int tapStart = Math.max(0, i - ranges[i]);

            // Calculate the rightmost position reachable by the current tap
            int tapEnd = Math.min(n, i + ranges[i]);

            for (int j = tapStart; j <= tapEnd; j++) {
                // Update with minimum number of taps
                dp[tapEnd] = Math.min(dp[tapEnd], dp[j] + 1);
            }
        }

        // Check if the garden can be watered completely
        if (dp[n] == INF) {
            // Garden cannot be watered
            return -1;
        }

        // Return the minimum number of taps needed to water the entire garden
        return dp[n];
    }
}
