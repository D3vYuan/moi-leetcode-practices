package com.example.solution.Q1501_Q2000;

public class P1611_Minimum_One_Bit_Operations_Make_Integer_Zero {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-one-bit-operations-to-make-integers-zero/description/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy
     * 1. If n == 0, return 0.
     * 2. Initialize k = 0, curr = 1. Here, curr represents 2^k
     * 3. While curr * 2 <= n:
     * a. Multiply curr by 2.
     * b. Increment k.
     * 4. Return 2k+1−1−minimumOneBitOperations(n⊕curr)
     */
    public int minimumOneBitOperations(int n) {
        if (n == 0) {
            return 0;
        }

        int k = 0;
        int curr = 1;

        while (curr * 2 <= n) {
            curr *= 2;
            k++;
        }

        return (1 << (k + 1)) - 1 - minimumOneBitOperations(n ^ curr);
    }
}
