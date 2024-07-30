package com.example.solution.Q501_Q1000;

public class P633_Sum_Of_Squares {
    /**
     * Reference:
     * https://leetcode.com/problems/sum-of-square-numbers/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. The only difference lies in that we need to find an integer, mid in the
     * range [0, c - a^2], such that this number is the square root of c - a^2.
     * 2. Or in other words, we need to find an integer, midmidmid, in the range [0,
     * c - a^2], such that mid × mid = c - a^2
     */
    public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            int b = c - (int) (a * a);
            if (binary_search(0, b, b))
                return true;
        }
        return false;
    }

    public boolean binary_search(long s, long e, int n) {
        if (s > e)
            return false;
        long mid = s + (e - s) / 2;
        if (mid * mid == n)
            return true;
        if (mid * mid > n)
            return binary_search(s, mid - 1, n);
        return binary_search(mid + 1, e, n);
    }
}
