package com.example.katana.Q2501_Q3000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P2966_Divide_Array_Max_Difference {
    public int[][] divideArray(int[] nums, int k) {
        List<List<Integer>> dividedArrayList = new ArrayList<>();
        Arrays.sort(nums);
        int subGroupSize = 3;
        int totalGroups = nums.length / subGroupSize;

        int previousNumber = nums[0];
        int currentGroupMin = previousNumber;
        int currentGroupId = 0;

        for (int i = 0; i < totalGroups; i++) {
            dividedArrayList.add(new ArrayList<>());
        }
        dividedArrayList.get(currentGroupId).add(previousNumber);

        for (int i = 1; i < nums.length; i++) {
            int currentNumber = nums[i];
            if (currentNumber - previousNumber > k && dividedArrayList.get(currentGroupId).size() < subGroupSize) {
                return new int[][] {};
            }

            currentGroupId = currentNumber - previousNumber <= k
                    && dividedArrayList.get(currentGroupId).size() < subGroupSize ? currentGroupId
                            : currentGroupId + 1;
            System.out.println(String.format("Adding: %d | Previous: %d | Difference: %d", currentNumber,
                    previousNumber, currentNumber - previousNumber));
            List<Integer> currentGroup = dividedArrayList.get(currentGroupId);
            if (currentGroup.size() == 0) {
                currentGroupMin = currentNumber;
            }

            if (currentNumber - currentGroupMin > k) {
                return new int[][] {};
            }

            currentGroup.add(currentNumber);
            previousNumber = currentNumber;
        }

        int[][] dividedArray = new int[dividedArrayList.size()][subGroupSize];
        for (int i = 0; i < dividedArrayList.size(); i++) {
            List<Integer> subGroupList = dividedArrayList.get(i);
            dividedArray[i] = subGroupList.stream().mapToInt(x -> x).toArray();
        }
        return dividedArray;
    }

}
