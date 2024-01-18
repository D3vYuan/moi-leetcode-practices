package com.example.katana.Q1501_Q2000;

public class P1685_Sum_Of_Absolute_Differences_Sorted_Array {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int[] differences = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = 0; j < nums.length; j++) {
                sum += Math.abs(nums[i] - nums[j]);
                System.out.println(String.format("i: %d | j: %d | differences: %d", nums[i], nums[j], sum));
            }
            differences[i] = sum;
        }
        return differences;
    }
}
