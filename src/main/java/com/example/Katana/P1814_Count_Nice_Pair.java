package com.example.katana;

import java.util.Arrays;

public class P1814_Count_Nice_Pair {

    public Integer reverseInteger(int number) {
        String numberValue = String.valueOf(number);
        StringBuilder sb = new StringBuilder();
        for (int i = numberValue.length() - 1; i >= 0; i--) {
            sb.append(numberValue.charAt(i));
        }
        return sb.toString().length() == 0 ? 0 : Integer.valueOf(sb.toString());
    }

    public boolean isNicePair(int i, int j, int[] nums) {
        boolean firstRuleMatch = i < j;
        if (firstRuleMatch) {
            boolean secondRuleMatch = false;
            int i_number = nums[i];
            int j_number = nums[j];
            int i_number_reverse = reverseInteger(i_number);
            int j_number_reverse = reverseInteger(j_number);

            System.out.println(String.format("i: %d | reverse_i: %d", i_number, i_number_reverse));
            System.out.println(String.format("j: %d | reverse_j: %d", j_number, j_number_reverse));

            secondRuleMatch = i_number + j_number_reverse == j_number + i_number_reverse;
            return secondRuleMatch;
        } else {
            return false;
        }
    }

    public int countNicePairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (i == j) {
                    continue;
                }

                boolean isNice = isNicePair(i, j, nums);
                System.out.println(String.format("Comparing: %d (%d) | %d (%d) - %s", i, nums[i], j, nums[j], isNice));
                if (isNice) {
                    count += 1;
                }
            }
        }
        return count;
    }
}
