package com.example.solution.Q2501_Q3000;

import java.util.Arrays;

public class P2962_Count_Subarrays_Max_Element_Appears_At_Least_K_Times {
    /**
     * Reference:
     * https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialization:
     * a. Initialize variables max_element, ans, start, and max_elements_in_window.
     * b. max_element stores the maximum element in the given array nums.
     * c. ans will be the final count of subarrays meeting the condition.
     * d. start is a pointer for the start of the window.
     * e. max_elements_in_window stores the frequency of the max_element within the
     * current window.
     * 
     * 2. Iterating through the array:
     * a. Iterate through each element in the array using a for loop with index end
     * ranging from 0 to the length of nums.
     * 
     * 3. Counting frequency of max_element in the current window:
     * a. Check if the current element nums[i] is equal to max_element.
     * b. If true, increment max_elements_in_window as it represents the frequency
     * of max_element in the current window.
     * 
     * 4. Sliding window to meet the condition:
     * a. Use a while loop to shrink the window (start pointer) until
     * max_elements_in_window is equal to k.
     * b. Inside the while loop, decrement max_elements_in_window if the element at
     * the window's start (nums[start]) is equal to max_element.
     * c. Increment start to move the window to the right.
     * 
     * 5. Counting subarrays:
     * a. Add start to the ans variable. This is done inside the for loop, so it
     * accumulates the count of subarrays meeting the condition.
     * 
     * 6. Returning the result:
     * a. After the loop completes, return the final count stored in the ans
     * variable.
     */
    public long countSubarrays(int[] nums, int k) {
        int maxElement = Arrays.stream(nums).max().getAsInt();
        long ans = 0;
        int start = 0, maxElementsInWindow = 0;

        for (int end = 0; end < nums.length; end++) {
            if (nums[end] == maxElement) {
                maxElementsInWindow++;
            }
            while (k == maxElementsInWindow) {
                if (nums[start] == maxElement) {
                    maxElementsInWindow--;
                }
                start++;
            }
            ans += start;
        }

        return ans;
    }
}
