package com.example.solution.Q001_Q500;

public class P75_Sort_Colors {
    /**
     * Reference:
     * https://leetcode.com/problems/sort-colors/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialise the rightmost boundary of zeros: p0 = 0. During the algorithm
     * execution nums[idx < p0] = 0.
     * 2. Initialise the leftmost boundary of twos: p2 = n - 1. During the algorithm
     * execution nums[idx > p2] = 2.
     * 3. Initialise the index of the current element to consider: curr = 0.
     * 4. While curr <= p2 :
     * a. If nums[curr] = 0: swap currth and p0th elements and move both pointers to
     * the right.
     * b. If nums[curr] = 2: swap currth and p2th elements. Move pointer p2 to the
     * left.
     * c. If nums[curr] = 1: move pointer curr to the right.
     */

    public void sortColors(int[] nums) {
        // For all idx < i : nums[idx < i] = 0
        // j is an index of elements under consideration
        int p0 = 0, curr = 0;

        // For all idx > k : nums[idx > k] = 2
        int p2 = nums.length - 1;

        int tmp;
        while (curr <= p2) {
            if (nums[curr] == 0) {
                // Swap p0-th and curr-th elements
                // i++ and j++
                tmp = nums[p0];
                nums[p0++] = nums[curr];
                nums[curr++] = tmp;
            } else if (nums[curr] == 2) {
                // Swap k-th and curr-th elements
                // p2--
                tmp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2--] = tmp;
            } else
                curr++;
        }
    }
}
