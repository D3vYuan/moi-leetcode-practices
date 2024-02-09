package com.example.katana.Q501_Q1000;

import java.util.Arrays;
import java.util.stream.Collectors;

public class P645_Set_Mismatch {
    public int[] findErrorNums(int[] nums) {
        Arrays.sort(nums);

        String arrDesc = Arrays.stream(nums).mapToObj(String::valueOf).collect(Collectors.joining(","));
        System.out.println(String.format("Sorted: %s", arrDesc));

        int missing = -1;
        int duplicated = -1;

        int count = 0;
        while (count < nums.length) {
            int previousNumber = count == 0 ? 0 : nums[count - 1];
            int currentNumber = nums[count];

            if (currentNumber - previousNumber > 1) {
                missing = previousNumber + 1;
                System.out.println(String.format("Missing: %d", missing));
            }

            if (currentNumber - previousNumber == 0) {
                duplicated = nums[count];
                System.out.println(String.format("Duplicated: %d", duplicated));
            }

            if (missing != -1 && duplicated != -1) {
                break;
            }
            count++;
        }

        missing = missing == -1 ? nums[nums.length - 1] + 1 : missing;

        return new int[] { duplicated, missing };
    }
}
