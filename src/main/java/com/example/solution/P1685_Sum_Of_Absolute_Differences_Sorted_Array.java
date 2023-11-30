package com.example.solution;

public class P1685_Sum_Of_Absolute_Differences_Sorted_Array {
    /**
     * References:https://leetcode.com/problems/sum-of-absolute-differences-in-a-sorted-array/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation
     * Because the input is given sorted, let's try to split the problem into two
     * parts. For a given num at index i, the answer for this index is the sum of:
     * - The sum of absolute differences between num and all numbers less than num.
     * - The sum of absolute differences between num and all numbers greater than
     * num.
     * 
     * If we made all the numbers equal to 8, they would have a sum equal to 8 times
     * the count of numbers leftCount. In this specific example, they would have a
     * sum of 3 * 8 = 24. In general, for an index i, there are i elements less than
     * nums[i], so we have leftCount = i and these numbers would have a sum of
     * leftCount * nums[i].
     * 
     * Their current sum is leftSum = 1 + 4 + 6 = 11. Thus, we can find the sum of
     * absolute differences for these elements as leftTotal = 24 - 11 = 13. In
     * general, we have leftTotal = leftCount * nums[i] - leftSum
     * 
     * In this example, there are 3, so they would have a sum of 8 * 3 = 24. In
     * general, for an index i, there are rightCount = n - 1 - i elements on its
     * right, and they would have a sum of rightCount * nums[i] if we reduced them
     * all.
     * 
     * In our example, they currently have a sum of rightSum = 12 + 18 + 21 = 51.
     * Thus, the sum of absolute differences is 51 - 24 = 27. In general, we can
     * find the sum of absolute differences as rightTotal = rightSum - rightCount *
     * nums[i]
     * 
     * Strategy
     * Let n be the length of nums.
     * 1. Create a prefix sum of nums.
     * 2. Initialize the answer list ans.
     * 3. Iterate i over the indices of nums:
     * a. Calculate leftSum using prefix.
     * b. Calculate rightSum using prefix.
     * c. Calculate leftCount = i.
     * d. Calculate rightCount = n - 1 - i.
     * e. Calculate leftTotal = leftCount * nums[i] - leftSum.
     * f. Calculate rightTotal = rightSum - rightCount * nums[i].
     * g. Add leftTotal + rightTotal to ans.
     * 4. Return ans.
     */

    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        prefix[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        int[] ans = new int[n];
        for (int i = 0; i < nums.length; i++) {
            int leftSum = prefix[i] - nums[i];
            int rightSum = prefix[n - 1] - prefix[i];

            int leftCount = i;
            int rightCount = n - 1 - i;

            int leftTotal = leftCount * nums[i] - leftSum;
            int rightTotal = rightSum - rightCount * nums[i];

            System.out.println(String.format(
                    "sum (left): %d | sum (right): %d | count (left): %d | count (right): %d | total (left): %d | total (right): %d | ans: %d",
                    leftSum, rightSum, leftCount, rightCount, leftTotal, rightTotal, leftTotal + rightTotal));

            ans[i] = leftTotal + rightTotal;
        }

        return ans;
    }
}
