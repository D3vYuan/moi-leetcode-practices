package com.example.solution.Q501_Q1000;

public class P713_Subarray_Product_Less_Than_K {
    /**
     * Reference:
     * https://leetcode.com/problems/subarray-product-less-than-k/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Check if k is less than or equal to 1. In this case, no subarrays can have
     * a product less than k, so return 0.
     * 2. Initialize the variables totalCount to 0, to store the final count of
     * subarrays with a product less than k, and product to 1, representing the
     * product of elements within the window (initially empty).
     * 3. Use two pointers, left and right, to define the sliding window. Iterate
     * through the nums array using a for loop until right reaches the end.
     * a. Inside the loop, multiply the current product by the element at the right
     * pointer (nums[right]). This effectively includes the new element in the
     * window.
     * b. While the current product is greater than or equal to k, the window needs
     * to shrink to exclude elements that make the product exceed k.
     * b1. Divide the product by the element at the left pointer (nums[left]).
     * b2. Increment left by 1 to move the window one position to the right,
     * effectively excluding the leftmost element.
     * c. Update the totalCount by adding the number of valid subarrays with the
     * current window size, which is right - left + 1.
     * 4. Return the totalCount.
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // Handle edge cases where k is 0 or 1 (no subarrays possible)
        if (k <= 1)
            return 0;

        int totalCount = 0;
        int product = 1;

        // Use two pointers to maintain a sliding window
        for (int left = 0, right = 0; right < nums.length; right++) {
            // Expand the window by including the element at the right pointer
            product *= nums[right];

            // Shrink the window from the left while the product is greater than or equal to
            // k
            while (product >= k) {
                // Remove the element at the left pointer from the product
                product /= nums[left++];
            }

            // Update the total count by adding the number of valid subarrays with the
            // current window size
            totalCount += right - left + 1; // right - left + 1 represents the current window size
        }

        return totalCount;
    }
}
