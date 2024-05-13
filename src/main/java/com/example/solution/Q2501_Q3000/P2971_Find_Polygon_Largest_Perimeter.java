package com.example.solution.Q2501_Q3000;

import java.util.Arrays;

public class P2971_Find_Polygon_Largest_Perimeter {
    /**
     * Reference:
     * https://leetcode.com/problems/find-polygon-with-the-largest-perimeter/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Sort the input array nums in ascending order.
     * 2. Initialize variables previous_elements_sum to 0 and ans to -1.
     * 3. Iterate through each element num in the sorted array nums.
     * 4. Check if the current element num is less than the sum of previous
     * elements. If true, we have encountered a valid combination of sides.
     * 5. If the current num is a valid side, update ans to the sum of the current
     * num and previous_elements_sum.
     * 6. Update previous_elements_sum by adding the current element num.
     * 7. After iterating through all elements, the method returns the largest
     * possible perimeter stored in ans.
     */
    public long largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        long previousElementsSum = 0;
        long ans = -1;
        for (int num : nums) {
            if (num < previousElementsSum) {
                ans = num + previousElementsSum;
            }
            previousElementsSum += num;
        }
        return ans;
    }
}
