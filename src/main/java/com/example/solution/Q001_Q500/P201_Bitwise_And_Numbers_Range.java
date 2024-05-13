package com.example.solution.Q001_Q500;

public class P201_Bitwise_And_Numbers_Range {
    /**
     * Reference:
     * https://leetcode.com/problems/bitwise-and-of-numbers-range/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. The idea is that we shift both numbers to the right, until the numbers
     * become equal, i.e. the numbers are reduced into their common prefix. Then we
     * append zeros to the common prefix in order to obtain the desired result, by
     * shifting the common prefix to the left.
     * 2. We reduce both numbers into their common prefix, by doing right shift
     * iteratively. During the iteration, we keep the count on the number of shift
     * operations we perform.
     * 3. With the common prefix, we restore it to its previous position, by left
     * shifting.
     */
    public int rangeBitwiseAnd(int left, int right) {
        int shift = 0;
        // find the common 1-bits
        while (left < right) {
            left >>= 1;
            right >>= 1;
            ++shift;
        }
        return left << shift;
    }
}
