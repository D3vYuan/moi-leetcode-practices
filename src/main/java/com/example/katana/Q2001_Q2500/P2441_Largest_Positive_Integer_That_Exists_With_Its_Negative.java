package com.example.katana.Q2001_Q2500;

import java.util.Arrays;

public class P2441_Largest_Positive_Integer_That_Exists_With_Its_Negative {
    public int findMaxK(int[] nums) {
        int largestPositiveInteger = Integer.MIN_VALUE;
        Arrays.sort(nums);
        int start = 0;
        int last = nums.length - 1;

        while (start < last) {
            int currentSmallest = nums[start];
            int currentLargest = nums[last];
            int negativeEquivalent = -1 * currentLargest;

            System.out.println(String.format("Comparing: Index %d (%d) | Index %d (%d)", start, currentSmallest, last,
                    currentLargest));
            if (currentSmallest == negativeEquivalent) {
                largestPositiveInteger = Math.max(currentLargest, largestPositiveInteger);
                start++;
                last--;
            } else if (currentSmallest > negativeEquivalent) {
                last--;
            } else {
                start++;
            }
        }

        return largestPositiveInteger == Integer.MIN_VALUE ? -1 : largestPositiveInteger;
    }
}
