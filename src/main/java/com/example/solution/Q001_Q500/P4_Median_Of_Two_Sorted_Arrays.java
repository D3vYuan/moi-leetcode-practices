package com.example.solution.Q001_Q500;

public class P4_Median_Of_Two_Sorted_Arrays {
    /**
     * Reference:
     * https://leetcode.com/problems/median-of-two-sorted-arrays/editorial/?envType=daily-question&envId=2024-01-17
     * 
     * Strategy:
     * 1. Get the total size of two arrays m + n
     * a. If m + n is odd, we are looking for the (m + n) / 2-th element.
     * b. If m + n is even, we are looking for the average of the (m + n) / 2-th and
     * the (m + n) / 2 + 1-th elements.
     * 2. Set two pointers p1 and p2 at the beginning of arrays nums1 and nums2.
     * 3. If both p1 and p2 are in bounds of the arrays, compare the values at p1
     * and p2:
     * a. If nums1[p1] is smaller than nums2[p2], we move p1 one place to the right.
     * b. Otherwise, we move p2 one place to the right.
     * c. If p1 is outside nums1, just move p2 one place to the right.
     * d. If p2 is outside nums2, just move p1 one place to the right.
     * 4. Get the target elements and calculate the median:
     * a. If m + n is odd, repeat step 3 by (m + n + 1) / 2 times and return the
     * element from the last step.
     * b. If m + n is even, repeat step 3 by (m + n) / 2 + 1 times and return the
     * average of the elements from the last two steps.
     */

    private int p1 = 0, p2 = 0;

    // Get the smaller value between nums1[p1] and nums2[p2] and move the pointer
    // forwards.
    private int getMin(int[] nums1, int[] nums2) {
        if (p1 < nums1.length && p2 < nums2.length) {
            return nums1[p1] < nums2[p2] ? nums1[p1++] : nums2[p2++];
        } else if (p1 < nums1.length) {
            return nums1[p1++];
        } else if (p2 < nums2.length) {
            return nums2[p2++];
        }
        return -1;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if ((m + n) % 2 == 0) {
            for (int i = 0; i < (m + n) / 2 - 1; ++i) {
                int tmp = getMin(nums1, nums2);
            }
            return (double) (getMin(nums1, nums2) + getMin(nums1, nums2)) / 2;
        } else {
            for (int i = 0; i < (m + n) / 2; ++i) {
                int tmp = getMin(nums1, nums2);
            }
            return getMin(nums1, nums2);
        }
    }
}
