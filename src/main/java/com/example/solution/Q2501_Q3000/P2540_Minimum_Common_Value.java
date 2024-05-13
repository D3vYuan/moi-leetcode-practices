package com.example.solution.Q2501_Q3000;

import java.util.HashSet;
import java.util.Set;

public class P2540_Minimum_Common_Value {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-common-value/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a set set1 and add the elements from nums1.
     * 2. For each num in nums2:
     * a. If num is in set1, return num. We found a common element. Since nums2 is
     * sorted in ascending order, the first common element is the minimum common
     * element.
     * 3. Return -1 if there are no common elements.
     */
    public int getCommon(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();

        // Add the elements from nums1 to set1
        for (int num : nums1) {
            set1.add(num);
        }

        // Search for each element of nums2 in set1
        // Return the first common element found
        for (int num : nums2) {
            if (set1.contains(num)) {
                return num;
            }
        }

        // Return -1 if there are no common elements
        return -1;
    }

}
