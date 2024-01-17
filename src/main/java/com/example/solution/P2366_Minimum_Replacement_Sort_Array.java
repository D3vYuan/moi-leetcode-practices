package com.example.solution;

public class P2366_Minimum_Replacement_Sort_Array {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-replacements-to-sort-the-array/editorial/?envType=daily-question&envId=2024-01-17
     * 
     * Explanation:
     * 1. If nums[i] is divisible by nums[i + 1], we break nums[i] into multiple
     * elements of value nums[i + 1].
     * 2. If nums[i] is not divisible by nums[i + 1], we break nums[i] into
     * nums_elements = nums[i] / nums[i + 1] + 1 sorted elements, with the the
     * smallest element being nums[i + 1] / nums_elements. For example, if nums[i] =
     * 7 and nums[i + 1] = 3, we replace [7] with [2, 2, 3] by two replacement
     * operations.
     * 3. simplified by nums_elements = (nums[i] + nums[i + 1] - 1) / nums[i + 1].
     * Regardless of whether nums[i] is divisible as nums[i + 1] or not, we will
     * always obtain the correct result.
     * 
     * Solution:
     * 1. Set answer as 0, and set n as the length of nums.
     * 2. Iterate over nums backward from nums[n - 2], as we don't need to replace
     * nums[n - 1].
     * a. If nums[i] <= nums[i + 1], move on to the next element nums[i - 1].
     * b. If nums[i] is divisible by nums[i + 1], break nums[i] into nums_elements =
     * num[i] / nums[i + 1] elements, otherwise, break num[i] into nums_elements =
     * nums[i] / nums[i + 1] + 1 elements. This requires num_elements - 1
     * replacement operations. Hence, we increment answer by num_elements - 1.
     * c. The largest possible nums[i] after the operations is nums[i] /
     * num_elements, update nums[i] as nums[i] / num_elements.
     * 3. Return answer once the iteration is complete.
     * 
     */
    public long minimumReplacement(int[] nums) {
        long answer = 0;
        int n = nums.length;

        // Start from the second last element, as the last one is always sorted.
        for (int i = n - 2; i >= 0; i--) {
            // No need to break if they are already in order.
            if (nums[i] <= nums[i + 1]) {
                continue;
            }

            // Count how many elements are made from breaking nums[i].
            long numElements = (long) (nums[i] + nums[i + 1] - 1) / (long) nums[i + 1];

            // It requires numElements - 1 replacement operations.
            answer += numElements - 1;

            // Maximize nums[i] after replacement.
            nums[i] = nums[i] / (int) numElements;
        }

        return answer;
    }
}
