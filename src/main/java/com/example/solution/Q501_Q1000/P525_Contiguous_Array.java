package com.example.solution.Q501_Q1000;

import java.util.HashMap;
import java.util.Map;

public class P525_Contiguous_Array {
    /**
     * Reference:
     * https://leetcode.com/problems/contiguous-array/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. This approach relies on the same premise as the previous approach. But, we
     * need not use an array of size 2n+1, since it isn't necessary
     * that we'll encounter all the count values possible. Thus, we make
     * use of a HashMap map to store the entries in the form of
     * (index,count). We make an entry for a
     * count in the map whenever the count is encountered
     * first, and later on use the corresponding index to find the length of the
     * largest subarray with equal no. of zeros and ones when the same
     * count is encountered again.
     */
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);
            if (map.containsKey(count)) {
                maxlen = Math.max(maxlen, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxlen;
    }
}
