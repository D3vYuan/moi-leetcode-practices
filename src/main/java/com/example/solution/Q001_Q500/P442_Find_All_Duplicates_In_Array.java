package com.example.solution.Q001_Q500;

import java.util.ArrayList;
import java.util.List;

public class P442_Find_All_Duplicates_In_Array {
    /**
     * Reference:
     * https://leetcode.com/problems/find-all-duplicates-in-an-array/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a variable n to the length of nums.
     * 2. Use cycle sort to place elements at the index that corresponds to their
     * value.
     * a. Initialize a variable i to 0.
     * b. Iterate through the elements in nums:
     * b1. Set a variable correctIdx to nums[i] - 1.
     * b2. If the nums[i]does not equal nums[correctIdx], swap the element at
     * nums[i] with the element at nums[correctIdx].
     * b3. Otherwise, increment i.
     * 3. Initialize an array duplicates to store the answer.
     * 4. Add duplicate numbers to the answer array.
     * a. Iterate through sorted nums using a for loop and the iterator i:
     * a1. If nums[i] does not equal i + 1, add nums[i] to duplicates.
     * 5. Return duplicates.
     */
    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;

        // Use cycle sort to place elements
        // at corresponding index to value
        int i = 0;
        while (i < n) {
            int correctIdx = nums[i] - 1;
            if (nums[i] != nums[correctIdx]) {
                swap(nums, i, correctIdx);
            } else {
				i++;
			}
        }

        // Any elements not at the index that corresponds to their value are duplicates
        List<Integer> duplicates = new ArrayList<>();
        for (i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                duplicates.add(nums[i]);
            }
        }

        return duplicates;
    }

    // Swaps two elements in nums
    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
