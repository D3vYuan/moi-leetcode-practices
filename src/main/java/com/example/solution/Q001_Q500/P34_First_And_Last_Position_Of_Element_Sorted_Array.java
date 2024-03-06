package com.example.solution.Q001_Q500;

public class P34_First_And_Last_Position_Of_Element_Sorted_Array {

    /**
     * Reference:
     * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. Define a function called findBound which takes three arguments: the array,
     * the target to search for, and a boolean value isFirst which indicates if we
     * are trying to find the first or the last occurrence of target.
     * 2. We use 2 variables to keep track of the subarray that we are scanning.
     * Let's call them begin and end. Initially, begin is set to 0 and end is set to
     * the last index of the array.
     * 3. We iterate until begin is greater than to end.
     * 4. At each step, we calculate the middle element mid = (begin + end) / 2. We
     * use the value of the middle element to decide which half of the array we need
     * to search.
     * a. nums[mid] == target
     * a1. isFirst is true ~ This implies that we are trying to find the first
     * occurrence of the element. If mid == begin or nums[mid - 1] != target, then
     * we return mid as the first occurrence of the target. Otherwise, we update end
     * = mid - 1
     * a2. isFirst is false ~ This implies we are trying to find the last occurrence
     * of the element. If mid == end or nums[mid + 1] != target, then we return mid
     * as the last occurrence of the target. Otherwise, we update begin = mid + 1
     * b. nums[mid] > target ~ We update end = mid - 1 since we must discard the
     * right side of the array as the middle element is greater than target.
     * c. nums[mid] < target ~ We update begin = mid + 1 since we must discard the
     * left side of the array as the middle element is less than target.
     * 5. We return a value of -1 at the end of our function which indicates that
     * target was not found in the array.
     * 6. In the main searchRange function, we first call findBound with isFirst set
     * to true. If this value is -1, we can simply return [-1, -1]. Otherwise, we
     * call findBound with isFirst set to false to get the last occurrence and then
     * return the result.
     */
    public int[] searchRange(int[] nums, int target) {

        int firstOccurrence = this.findBound(nums, target, true);

        if (firstOccurrence == -1) {
            return new int[] { -1, -1 };
        }

        int lastOccurrence = this.findBound(nums, target, false);

        return new int[] { firstOccurrence, lastOccurrence };
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int N = nums.length;
        int begin = 0, end = N - 1;

        while (begin <= end) {

            int mid = (begin + end) / 2;

            if (nums[mid] == target) {

                if (isFirst) {

                    // This means we found our lower bound.
                    if (mid == begin || nums[mid - 1] != target) {
                        return mid;
                    }

                    // Search on the left side for the bound.
                    end = mid - 1;

                } else {

                    // This means we found our upper bound.
                    if (mid == end || nums[mid + 1] != target) {
                        return mid;
                    }

                    // Search on the right side for the bound.
                    begin = mid + 1;
                }

            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }

        return -1;
    }
}
