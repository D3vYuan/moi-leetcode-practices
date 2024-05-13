package com.example.solution.Q001_Q500;

import java.util.HashSet;
import java.util.Set;

public class P349_Intersection_Of_Two_Arrays {
    /**
     * Reference:
     * https://leetcode.com/problems/intersection-of-two-arrays/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a set set1 and add all elements of nums1 to it.
     * 2. Initialize a set set2 and add all elements of nums2 to it.
     * 3. Call the built-in set intersection method (either retainAll() in Java, or
     * & operator in Python).
     * 4. Transform the resulting set into an array and return this result.
     */

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (Integer n : nums1) {
            set1.add(n);
        }
        Set<Integer> set2 = new HashSet<>();
        for (Integer n : nums2) {
            set2.add(n);
        }
        set1.retainAll(set2);
        int[] output = new int[set1.size()];
        int idx = 0;
        for (int s : set1) {
            output[idx++] = s;
        }
        return output;
    }
}
