package com.example.solution.Q2001_Q2500;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P2191_Sort_Jumbled_Numbers {
    /**
     * Reference:
     * https://leetcode.com/problems/sort-the-jumbled-numbers/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize an array of pairs given by storePairs.
     * 2. Iterate i through the nums array:
     * a. Initialize mappedValue with 0, temp with nums[i] and place with 1.
     * b. If temp is 0, push the value of mapping[0] in storePairs.
     * c. While temp is not equal to 0:
     *      Increment place * mapping[temp%10] to mappedValue.
     *      Multiply place by 10.
     *      Divide temp by 10.
     * d. Push the value of mappedValue and the index in storePairs.
     * 3. Sort the storePairs array.
     * 4. Create an array answer.
     * 5. Iterate through storePairs and append the nums value at the index to the answer.
     * 6. Return the answer array
     */

    public int[] sortJumbled(int[] mapping, int[] nums) {
        List<int[]> storePairs = new ArrayList<int[]>();

        for (int i = 0; i < nums.length; i++) {
            int mappedValue = 0;
            int temp = nums[i];
            int place = 1;

            if (temp == 0) {
                storePairs.add(new int[] { mapping[0], i });
                continue;
            }
            while (temp != 0) {
                mappedValue = place * mapping[temp % 10] + mappedValue;
                place *= 10;
                temp /= 10;
            }
            storePairs.add(new int[] { mappedValue, i });
        }

        Collections.sort(storePairs, (a, b) -> a[0] - b[0]);

        int[] answer = new int[nums.length];
        for (int i = 0; i < storePairs.size(); i++) {
            answer[i] = nums[storePairs.get(i)[1]];
        }

        return answer;
    }
}
