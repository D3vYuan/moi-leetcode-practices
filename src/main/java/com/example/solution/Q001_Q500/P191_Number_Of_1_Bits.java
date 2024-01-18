package com.example.solution.Q001_Q500;

public class P191_Number_Of_1_Bits {
    /*
     * Reference:
     * https://leetcode.com/problems/number-of-1-bits/editorial/?envType=daily-
     * question&envId=2023-11-13
     * 
     * Strategy
     * 1. We can check the ith bit of a number using a bit mask. We start with a
     * mask m=1m=1m=1, because the binary representation of 1 is, 0000 0000 0000
     * 0000 0000 0000 0000 0001
     * 2. A logical AND between any number and the mask 1 gives us the least
     * significant bit of this number. To check the next bit, we shift the mask to
     * the left by one.
     */
    public int hammingWeight(int n) {
        int bits = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            System.out.println(String.format("mask: %s", Integer.toBinaryString(mask)));
            if ((n & mask) != 0) {
                System.out.println(String.format("Found 1 bit"));
                bits++;
            }
            mask <<= 1;
        }
        return bits;
    }
}
