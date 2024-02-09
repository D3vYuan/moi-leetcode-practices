package com.example.solution.Q1001_Q1500;

import java.util.ArrayList;
import java.util.List;

public class P1291_Sequential_Digits {
    /**
     * Reference:
     * https://leetcode.com/problems/sequential-digits/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. Initialize sample string "123456789". This string contains all integers
     * that have sequential digits as substrings. Let's implement a sliding window
     * algorithm to generate them.
     * 2. Iterate over all possible string lengths: from the length of low to the
     * length of high.
     * a. For each length iterate over all possible start indexes: from 0 to 10 -
     * length.
     * a1. Construct the number from digits inside the sliding window of current
     * length.
     * a2. Add this number in the output list nums, if it's greater than low and
     * less than high.
     * 3. Return nums.
     * 
     */
    public List<Integer> sequentialDigits(int low, int high) {
        String sample = "123456789";
        int n = 10;
        List<Integer> nums = new ArrayList();

        int lowLen = String.valueOf(low).length();
        int highLen = String.valueOf(high).length();
        for (int length = lowLen; length < highLen + 1; ++length) {
            for (int start = 0; start < n - length; ++start) {
                int num = Integer.parseInt(sample.substring(start, start + length));
                if (num >= low && num <= high)
                    nums.add(num);
            }
        }
        return nums;
    }
}
