package com.example.solution.Q2501_Q3000;

import java.util.Arrays;

public class P2864_Maximum_Odd_BInary_Number {
    /**
     * Reference:
     * https://leetcode.com/problems/maximum-odd-binary-number/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Sort the input string s in ascending order.
     * 2. Reverse the bits in substring [0,N−2][0, N-2][0,N−2].
     * 3. Return the resulting string.
     */
    public String maximumOddBinaryNumber(String s) {
        char[] arr = s.toCharArray();
        int N = arr.length;

        Arrays.sort(arr);

        // Reverse order for the first N - 1 elements of the array
        // Because we want to keep a 1 at the last index
        // The last element of the array is index N - 1, and the second to last element
        // is at N - 2
        int secondLast = N - 2;
        for (int i = 0; i < N / 2; i++) {
            char temp = arr[i];
            arr[i] = arr[secondLast - i];
            arr[secondLast - i] = temp;
        }

        // Return result
        return new String(arr);
    }
}
