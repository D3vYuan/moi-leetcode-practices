package com.example.solution.Q501_Q1000;

public class P945_Minimum_Increment_Make_Array_Unique {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-increment-to-make-array-unique/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize variables:
     * a. n as the length of nums.
     * b. max to store the maximum value in nums.
     * c. minIncrements to store the total number of increments needed.
     * 2. Find the maximum value in nums.
     * 3. Create an array frequencyCount to store the frequency of each element.
     * 4. Loop over nums and populate frequencyCount.
     * 5. Loop over the frequencyCount array. For each element:
     * a. If the frequency is less than or equal to one, continue with the next
     * iteration.
     * b. Add the duplicates to the frequency of the next element.
     * c. Set the frequency of the current element to one.
     * d. Update minIncrements to account for the movement of the duplicates.
     * 6. Return minIncrements.
     */

    public int minIncrementForUnique(int[] nums) {
        int n = nums.length;
        int max = 0;
        int minIncrements = 0;

        // Find maximum value in array to determine range of frequencyCount array
        for (int val : nums) {
            max = Math.max(max, val);
        }

        // Create a frequencyCount array to store the frequency of each value in nums
        int[] frequencyCount = new int[n + max];

        // Populate frequencyCount array with the frequency of each value in nums
        for (int val : nums) {
            frequencyCount[val]++;
        }

        // Iterate over the frequencyCount array to make all values unique
        for (int i = 0; i < frequencyCount.length; i++) {
            if (frequencyCount[i] <= 1)
                continue;

            // Determine excess occurrences, carry them over to the next value,
            // ensure single occurrence for current value, and update minIncrements.
            int duplicates = frequencyCount[i] - 1;
            frequencyCount[i + 1] += duplicates;
            frequencyCount[i] = 1;
            minIncrements += duplicates;
        }

        return minIncrements;
    }
}
