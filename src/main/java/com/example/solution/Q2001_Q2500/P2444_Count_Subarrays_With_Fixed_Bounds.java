package com.example.solution.Q2001_Q2500;

public class P2444_Count_Subarrays_With_Fixed_Bounds {
    /**
     * Reference:
     * https://leetcode.com/problems/count-subarrays-with-fixed-bounds/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize three indices minPosition, maxPosition and leftBound as -1 and
     * set answer as 0.
     * 2. Iterate over nums, for each index i:
     * a. If nums[i] is out of the range [minK, maxK], update leftBound = i.
     * b. If nums[i] equals minK, update minPosition = i.
     * c. If nums[i] equals maxK, update maxPosition = i.
     * The number of valid subarrays ending at index i equals min(minPosition,
     * maxPosition) - leftBound. If the result is negative, it means there is no
     * valid subarray ending at i. Increment answer by the number of valid
     * subarrays.
     * 3. Return answer once the iteration stops.
     */
    public long countSubarrays(int[] nums, int minK, int maxK) {
        // minPosition, maxPosition: the MOST RECENT positions of minK and maxK.
        // leftBound: the MOST RECENT value outside the range [minK, maxK].
        long answer = 0;
        int minPosition = -1, maxPosition = -1, leftBound = -1;

        // Iterate over nums, for each number at index i:
        for (int i = 0; i < nums.length; ++i) {
            // If the number is outside the range [minK, maxK], update the most recent
            // leftBound.
            if (nums[i] < minK || nums[i] > maxK)
                leftBound = i;

            // If the number is minK or maxK, update the most recent position.
            if (nums[i] == minK)
                minPosition = i;
            if (nums[i] == maxK)
                maxPosition = i;

            // The number of valid subarrays equals the number of elements between leftBound
            // and
            // the smaller of the two most recent positions (minPosition and maxPosition).
            answer += Math.max(0, Math.min(maxPosition, minPosition) - leftBound);
        }
        return answer;
    }
}
