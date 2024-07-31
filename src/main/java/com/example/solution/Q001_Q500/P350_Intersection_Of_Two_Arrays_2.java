package com.example.solution.Q001_Q500;

import java.util.Arrays;
import java.util.HashMap;

public class P350_Intersection_Of_Two_Arrays_2 {
    /**
     * Reference:
     * https://leetcode.com/problems/intersection-of-two-arrays-ii/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. If nums1 is larger than nums2, swap the arrays.
     * 2. For each element in nums1:
     * a. Add it to the hash map m.
     * Increment the count if the element is already there.
     * 3. Initialize the insertion pointer (k) with zero.
     * 4. Iterate along nums2:
     * a. If the current number is in the hash map and count is positive:
     * Copy the number into nums1[k], and increment k.
     * Decrement the count in the hash map.
     * 5. Return first k elements of nums1.
     */

    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        HashMap<Integer, Integer> m = new HashMap<>();
        for (int n : nums1) {
            m.put(n, m.getOrDefault(n, 0) + 1);
        }
        int k = 0;
        for (int n : nums2) {
            int cnt = m.getOrDefault(n, 0);
            if (cnt > 0) {
                nums1[k++] = n;
                m.put(n, cnt - 1);
            }
        }
        return Arrays.copyOfRange(nums1, 0, k);
    }
}
