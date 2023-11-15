package com.example.solution;

import java.util.Arrays;

public class P1846_Maximum_Element_After_Decreasing_Arranging {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        int ans = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= ans + 1) {
                ans++;
            }
        }

        return ans;
    }
}
