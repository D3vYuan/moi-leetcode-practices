package com.example.solution;

import java.util.HashSet;
import java.util.Set;

public class P1980_Unique_Binary_String {
    /**
     * Reference:
     * https://leetcode.com/problems/find-unique-binary-string/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy
     * 1. Create integers, a hash set containing all the elements of nums in their
     * base-10 integer form.
     * 2. Initialize n = nums.length.
     * 3. Iterate num from 0 to n:
     * a. If num is not in integers, convert it to a binary string of length n and
     * return it.
     * 4. The code should never reach this point. Return anything.
     */
    public String findDifferentBinaryString(String[] nums) {
        Set<Integer> integers = new HashSet<>();
        for (String num : nums) {
            int intNum = Integer.parseInt(num, 2);
            integers.add(intNum);
            System.out.println(String.format("Processing: %s == %d", num, intNum));
        }

        int n = nums.length;
        for (int num = 0; num <= n; num++) {
            if (!integers.contains(num)) {
                String ans = Integer.toBinaryString(num);
                while (ans.length() < n) {
                    ans = "0" + ans;
                }
                System.out.println(String.format("Found: Missing Number %d == %s", num, ans));
                return ans;
            } else {
                System.out.println(String.format("Skipping: Present Number %d", num));
            }
        }
        return "";
    }
}
