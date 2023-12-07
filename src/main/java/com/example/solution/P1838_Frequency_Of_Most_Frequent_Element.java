package com.example.solution;

import java.util.Arrays;

public class P1838_Frequency_Of_Most_Frequent_Element {
    /**
     * Reference:
     * https://leetcode.com/problems/frequency-of-the-most-frequent-element/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation
     * We will use a sliding window over the sorted nums. For each element
     * nums[right], we will treat target as this element and try to make every
     * element in our window equal to target.
     * 
     * The size of the window is right - left + 1. That means we would have a final
     * sum of (right - left + 1) * target. If we track the sum of our window in a
     * variable curr, then we can calculate the required operations as (right - left
     * + 1) * target - curr. If it requires more than k operations, we must shrink
     * our window. Like in all sliding window problems, we will use a while loop to
     * shrink our window by incrementing left until k operations are sufficient.
     * 
     * Once the while loop ends, we know that we can make all elements in the window
     * equal to target. We can now update our answer with the current window size.
     * The final answer will be the largest valid window we find after iterating
     * right over the entire input.
     * 
     * Strategy
     * 1. Sort nums.
     * 2. Initialize the following integers:
     * a. left = 0, the left pointer.
     * b. ans = 0, the best answer we have seen so far.
     * c. curr = 0, the sum of the elements currently in our window.
     * 3. Iterate right over the indices of nums:
     * a. Consider target = nums[right].
     * b. Add target to curr.
     * c. While the size of the window right - left + 1 multiplied by target, minus
     * curr is greater than k:
     * c1. Subtract nums[left] from curr.
     * c2. Increment left.
     * d. Update ans with the current window size if it is larger.
     * 4. Return ans.
     */
    public int check(int i, int k, int[] nums, long[] prefix) {
        int target = nums[i];
        int left = 0;
        int right = i;
        int best = i;

        while (left <= right) {
            int mid = (left + right) / 2;
            long count = i - mid + 1;
            long finalSum = count * target;
            long originalSum = prefix[i] - prefix[mid] + nums[mid];
            long operationsRequired = finalSum - originalSum;

            if (operationsRequired > k) {
                left = mid + 1;
            } else {
                best = mid;
                right = mid - 1;
            }
        }

        return i - best + 1;
    }

    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        long[] prefix = new long[nums.length];
        prefix[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            prefix[i] = nums[i] + prefix[i - 1];
        }

        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(ans, check(i, k, nums, prefix));
        }

        return ans;

    }
}
