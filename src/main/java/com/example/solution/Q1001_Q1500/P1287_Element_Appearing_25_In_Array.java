package com.example.solution.Q1001_Q1500;

public class P1287_Element_Appearing_25_In_Array {
    /**
     * Reference:
     * https://leetcode.com/problems/element-appearing-more-than-25-in-sorted-array/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation
     * 1. The answer block has a length greater than n / 4, and thus it must overlap
     * at least one of the following positions in the array:
     * a. A quarter of the way through at index n / 4.
     * b. Halfway through at index n / 2.
     * c. Three-quarters of the way through at index 3n / 4.
     * 2. We will only consider the elements at each of these indices as candidates
     * since one of them must be the answer.
     * a. For a given candidate, we can find its frequency by identifying its block
     * size. To identify its block size, we find
     * the leftmost index in which candidate appears as left and the rightmost
     * indexin which candidate appears as right. Then, the size of the block is
     * right - left + 1.
     * b. We can calculate left and right using binary search.
     * 
     * Strategy
     * 1. Set n = arr.length.
     * 2. Create the array candidates with elements arr[n / 4], arr[n / 2], arr[3 *
     * n / 4].
     * 3. Set target = n / 4.
     * 4. For each candidate in candidates:
     * a. Calculate the leftmost index of candidate as left using binary search.
     * b. Calculate the rightmost index of candidate as right using binary search.
     * c. If right - left + 1 > target, return candidate.
     * 5. The code should never reach this point since it's guaranteed an answer
     * exists. Return anything.
     * 
     */
    public int findSpecialInteger(int[] arr) {
        int n = arr.length;
        int[] candidates = { arr[n / 4], arr[n / 2], arr[3 * n / 4] };
        int target = n / 4;

        for (int candidate : candidates) {
            int left = lower_bound(arr, candidate);
            int right = upper_bound(arr, candidate) - 1;
            if (right - left + 1 > target) {
                return candidate;
            }
        }

        return -1;
    }

    public int upper_bound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int lower_bound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

}
