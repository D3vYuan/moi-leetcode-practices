package com.example.solution.Q1001_Q1500;

import java.util.PriorityQueue;

public class P1425_Constrained_Subsequence_Sum {
    /**
     * Reference:
     * https://leetcode.com/problems/constrained-subsequence-sum/?envType=daily-question&envId=2024-01-17
     * 
     * Explanation:
     * 1. in an array of positive integers, we should always take the entire array.
     * The tricky part comes in when we have negative integers.
     * 2. The question now is, how do we know when it is worth it to take negative
     * numbers? In this case, taking the -5 allowed us to take the first element of
     * 16. This results in a net gain of 11. Anytime we have a positive net gain, we
     * should consider taking this element because it can contribute to a positive
     * sum and potentially increase the sum of subsequent subsequences
     * 3. We will iterate over the input from left to right. At each index i, we
     * will consider the maximum possible sum of a subsequence that includes and
     * ends at nums[i]. Let's call this value curr. How do we calculate curr for a
     * given index i? We want the maximum possible sum of a subsequence that ends
     * within the last k indices. We will then add nums[i] to this sum.
     * 4. we are only concerned with the maximum sum, we could use a max heap. The
     * max heap would store dp[j] for all j in the last k indices. We can easily
     * calculate curr by simply checking the top of this heap
     * 5. We need to make sure we don't use elements of the heap that are more than
     * k away from the current index. Before we calculate curr, we pop from the top
     * of the heap if it is outside our range
     * 6. When the top of the heap is negative, it indicates that selecting this
     * subsequence would result in a sum less than 0. Every element in the array to
     * the left of the current index should be abandoned - any "bridge" would not be
     * worth taking. It's better to discard these subsequences altogether and reset
     * the sum to 0.
     * 
     *
     * Strategy:
     * 1. Initialize a max heap with (nums[0], 0). Also initialize the answer ans =
     * nums[0].
     * 2. Iterate i over the indices of nums, starting from i = 1:
     * a. While i minus the index (second element) at the top of heap is greater
     * than k, pop from heap.
     * b. Set curr to the value (first element) at the top of heap, plus nums[i].
     * Note that if the value at the top of heap is negative, we should take 0
     * instead.
     * c. Update ans with curr if it is larger.
     * d. Push (curr, i) to heap.
     * 3. Return ans.
     */
    public int constrainedSubsetSum(int[] nums, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        heap.add(new int[] { nums[0], 0 });
        int ans = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // exceed the gap
            while (i - heap.peek()[1] > k) {
                heap.remove();
            }

            int curr = Math.max(0, heap.peek()[0]) + nums[i];
            ans = Math.max(ans, curr);
            heap.add(new int[] { curr, i });
        }
        return ans;
    }
}
