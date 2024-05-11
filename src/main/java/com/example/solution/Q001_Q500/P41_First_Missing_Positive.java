package com.example.solution.Q001_Q500;

public class P41_First_Missing_Positive {
    /**
     * Reference:
     * https://leetcode.com/problems/first-missing-positive/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a variable n to the length of nums.
     * 2. Use cycle sort to place positive elements smaller than n at the correct
     * index.
     * a. Initialize a variable i to 0.
     * b. Iterate through the elements in nums:
     * b1. Set a variable correctIdx to nums[i] - 1.
     * b2. If the nums[i] is greater than zero, less than or equal to n, and does
     * not equal nums[correctIdx], swap the element at nums[i] with the element at
     * nums[correctIdx].
     * b3. Otherwise, increment i.
     * 3. Iterate through sorted nums and return the smallest missing positive
     * number.
     * a. For each element in nums, if nums[i] does not equal i + 1, return i + 1,
     * the smallest missing positive number.
     * 4. Return n + 1, the smallest missing positive number when each number in
     * nums is in the correct position.
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // Use cycle sort to place positive elements smaller than n
        // at the correct index
        int i = 0;
        while (i < n) {
            int correctIdx = nums[i] - 1;
            if (nums[i] > 0 && nums[i] <= n && nums[i] != nums[correctIdx]) {
                swap(nums, i, correctIdx);
            } else {
                i++;
            }
        }

        // Iterate through nums
        // return smallest missing positive integer
        for (i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // If all elements are at the correct index
        // the smallest missing positive number is n + 1
        return n + 1;
    }

    // Swaps two elements in nums
    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
