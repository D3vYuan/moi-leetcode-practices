package com.example.solution;

public class P1758_Minimum_Changes_To_Alternate_Binary_String {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-changes-to-make-alternating-binary-string/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation:
     * 1. Let n be the length of s. There are n indices. Notice that an index i only
     * needs to be fixed for either start0 or start1, but never both.
     * 2. For a given s, if we need start0 operations to create the alternating
     * string that starts with 0, we will need exactly n - start0 operations to
     * create the alternating string that starts with 1
     * 
     * Strategy:
     * 1. Initialize start0 = 0.
     * 2. Iterate i over the indices of s:
     * a. If i % 2 = 0: If s[i] = '1', increment start0
     * b. Else: If s[i] = '0', increment start0.
     * 3. Return the minimum between start0 and s.length - start0
     * 
     */
    public int minOperations(String s) {
        int start0 = 0;

        // Number of correct number with start0 (i.e 01010101)
        // Even index will be 1 and odd index will be 0 for start0
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                if (s.charAt(i) == '1') {
                    start0++;
                }
            } else {
                if (s.charAt(i) == '0') {
                    start0++;
                }
            }
        }

        return Math.min(start0, s.length() - start0);
    }
}