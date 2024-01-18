package com.example.solution.Q1501_Q2000;

public class P1793_Maximum_Score_Good_Subarray {

    /**
     * Reference:
     * 
     * Explanation:
     * 1. The optimal subarray must contain index k, so it makes sense to consider
     * the subarray with only nums[k] as a starting point.
     * 2. We can either add an element to the left or an element to the right. Let's
     * say we have two pointers, left and right that represent our subarray. Which
     * direction should we go?
     * 3. If we move left, it's equivalent to adding nums[left - 1] to our subarray.
     * 4. If we move right, it's equivalent to adding nums[right + 1] to our
     * subarray.
     * 5. We should move in the direction of the greater element.
     * 6. At each step, we update currMin which is initially set to nums[k], and try
     * to update ans which is also initially set to nums[k].
     * 7. We can update ans with currMin * (right - left + 1) if it is larger.
     * 
     * Strategy:
     * 1. Initialize n = nums.length, left = k, right = k, ans = nums[k], and
     * currMin = nums[k].
     * 2. While left > 0 or right < n - 1:
     * a. Compare nums[left - 1] with nums[right + 1]:
     * a1. If nums[right + 1] is greater, increment right and update currMin with
     * nums[right] if it is lower.
     * a2. Otherwise, decrement left and update currMin with nums[left] if it is
     * lower.
     * b. Update ans with currMin * (right - left + 1) if it is greater.
     * 3. Return ans.
     * 
     */
    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        int left = k;
        int right = k;
        int ans = nums[k];
        int currentMin = nums[k];

        while (left > 0 || right < n - 1) {
            int currentLeft = (left > 0) ? nums[left - 1] : 0;
            int currentRight = (right < n - 1) ? nums[right + 1] : 0;

            if (currentLeft < currentRight) {
                right++;
                currentMin = Math.min(currentMin, nums[right]);
            } else {
                left--;
                currentMin = Math.min(currentMin, nums[left]);
            }

            ans = Math.max(ans, currentMin * (right - left + 1));
        }

        return ans;
    }
}
