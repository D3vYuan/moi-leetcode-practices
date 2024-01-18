package com.example.katana.Q1501_Q2000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class P1630_Arithmetic_Subarrays {

    private boolean isArithmetic(int[] values) {
        if (values.length == 1) {
            return true;
        }

        Arrays.sort(values);
        int previousDiff = Integer.MIN_VALUE;
        for (int i = 1; i < values.length; i++) {
            int currentDiff = Math.abs(values[i] - values[i - 1]);
            if (previousDiff == Integer.MIN_VALUE) {
                previousDiff = currentDiff;
                continue;
            }

            if (currentDiff != previousDiff) {
                return false;
            }
        }

        return true;
    }

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            int start = l[i];
            int end = r[i];
            int[] subArray = Arrays.copyOfRange(nums, start, end + 1);
            boolean isArithmetic = isArithmetic(subArray);

            String arrDesc = Arrays.stream(subArray).mapToObj(String::valueOf).collect(Collectors.joining(","));
            System.out.println(String.format("Array: %s - %s", arrDesc, isArithmetic));

            result.add(isArithmetic);
        }

        return result;
    }
}
