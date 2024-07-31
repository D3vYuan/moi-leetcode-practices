package com.example.solution.Q001_Q500;

public class P344_Reverse_String {
    /**
     * Reference:
     * https://leetcode.com/problems/reverse-string/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Set the pointer left at index 0, and the pointer right at index n - 1,
     * where n is the number of elements in the array.
     * 2. While left < right:
     * a. Swap s[left] and s[right].
     * b. Move the left pointer one step right, and the right pointer one step left.
     */

    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left++] = s[right];
            s[right--] = tmp;
        }
    }
}
