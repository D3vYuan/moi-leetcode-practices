package com.example.solution.Q001_Q500;

import java.util.HashMap;
import java.util.Map;

public class P260_SIngle_Number_3 {
    /**
     * Reference:
     * https://leetcode.com/problems/single-number-iii/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Build a hashmap: element -> its frequency. Return only the elements with
     * the frequency equal to 1.
     */

    public int[] singleNumber(int[] nums) {
        Map<Integer, Integer> hashmap = new HashMap();
        for (int n : nums)
            hashmap.put(n, hashmap.getOrDefault(n, 0) + 1);

        int[] output = new int[2];
        int idx = 0;
        for (Map.Entry<Integer, Integer> item : hashmap.entrySet())
            if (item.getValue() == 1)
                output[idx++] = item.getKey();

        return output;
    }
}
