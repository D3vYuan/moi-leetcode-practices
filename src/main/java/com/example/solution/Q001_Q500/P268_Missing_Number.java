package com.example.solution.Q001_Q500;

import java.util.HashSet;
import java.util.Set;

public class P268_Missing_Number {
    /**
     * Reference:
     * https://leetcode.com/problems/missing-number/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1.
     */
    public int missingNumber(int[] nums) {
        Set<Integer> numSet = new HashSet<Integer>();
        for (int num : nums)
            numSet.add(num);

        int expectedNumCount = nums.length + 1;
        for (int number = 0; number < expectedNumCount; number++) {
            if (!numSet.contains(number)) {
                return number;
            }
        }
        return -1;
    }
}
