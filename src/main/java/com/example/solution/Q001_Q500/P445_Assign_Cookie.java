package com.example.solution.Q001_Q500;

import java.util.Arrays;

public class P445_Assign_Cookie {
    /**
     * Reference:
     * https://leetcode.com/problems/assign-cookies/editorial/?envType=daily-question&envId=2024-01-17
     * 
     * Strategy:
     * 1. Sort arrays g and s in ascending order.
     * 2. Initialize variable contentChildren = 0 to represent the number of
     * children who receive cookies that meet their greed.
     * 3. Initialize variable cookieIndex = 0 to represent the number of cookies
     * that have been assigned or skipped.
     * 4. While cookieIndex is less than the size of s and contentChildren is less
     * than the size of g:
     * a. If the current cookie's size is greater than or equal to the current
     * child's greed:
     * a1. Increment contentChildren to allocate the cookie.
     * b. Increment cookieIndex to move on to the next cookie.
     * 5. Return contentChildren.
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int contentChildren = 0;
        int cookieIndex = 0;
        while (cookieIndex < s.length && contentChildren < g.length) {
            if (s[cookieIndex] >= g[contentChildren]) {
                contentChildren++;
            }
            cookieIndex++;
        }
        return contentChildren;
    }
}
