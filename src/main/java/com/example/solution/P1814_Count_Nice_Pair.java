package com.example.solution;

import java.util.HashMap;
import java.util.Map;

public class P1814_Count_Nice_Pair {
    /**
     * Reference:
     * https://leetcode.com/problems/count-nice-pairs-in-an-array/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation
     * 1. let's rearrange the formula so that all terms involving x are on one side
     * and all terms involving y are on the other: x - rev(x) == y - rev(y)
     * 2. To reverse the digits of a given integer num as described by the problem,
     * we can initialize an integer result = 0 as the reversed number. We then
     * continuously take the last digit of num using the modulo operator % and
     * append it to result as the least significant digit, this could be done by
     * multiplying result by 10 and adding the last digit. Then we remove the last
     * digit from num by dividing it by 10. The process above continues until num
     * becomes 0, at which point, result contains the reversed integer.
     * 3. the problem becomes "how many pairs in arr are equal?". This can be solved
     * using a counting trick with a hash map. We will iterate over arr and keep a
     * hash map dic (short for dictionary) that keeps track of how many times we
     * have seen a number. For each num we iterate over, we check how many times we
     * have already seen num. Each num we had already seen earlier can be paired
     * with the current num to form a pair.
     * 
     * Strategy
     * 1. Implement the function rev as described by the problem description.
     * 2. Create arr, where arr[i] = nums[i] - rev(nums[i]).
     * 3. Initialize an empty hash map dic and the answer variable, ans.
     * 4. Iterate over each num in arr:
     * a. Add dic[num] to ans.
     * b. Increment dic[num].
     * 5. Return ans.
     * 
     */

    public int reverse(int num) {
        int result = 0;
        while (num > 0) {
            result = result * 10 + num % 10;
            num /= 10;
        }
        return result;
    }

    public int countNicePairs(int[] nums) {
        int[] differences = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            differences[i] = nums[i] - reverse(nums[i]);
        }

        Map<Integer, Integer> count = new HashMap<>();
        int ans = 0;
        int MOD = (int) 1e9 + 7;

        for (int difference : differences) {
            ans = (ans + count.getOrDefault(difference, 0)) % MOD;
            count.put(difference, count.getOrDefault(difference, 0) + 1);
        }

        return ans;
    }
}