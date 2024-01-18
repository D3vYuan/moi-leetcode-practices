package com.example.solution.Q2501_Q3000;

import java.util.ArrayList;
import java.util.List;

public class P2610_Convert_Array_To_2D {

    /**
     * Reference:
     * https://leetcode.com/problems/convert-an-array-into-a-2d-array-with-conditions/editorial/?envType=daily-question&envId=2024-01-17
     * 
     * Strategy:
     * 1. Create an array freq of size nums.size() + 1 to store the frequency of
     * integers in the array nums.
     * 2. Create an empty 2D array ans to store the answer array.
     * 3. Iterate over the array nums and for each integer c:
     * a. If the frequency of the integer is greater than or equal to the current
     * rows count in ans, then add a row to ans.
     * b. Insert the integer c at the row freq[c].
     * c. Increment the frequency of c in freq.
     * 4. Return ans.
     */
    public List<List<Integer>> findMatrix(int[] nums) {
        int freq[] = new int[nums.length + 1];

        ArrayList<List<Integer>> ans = new ArrayList<>();
        for (int c : nums) {
            if (freq[c] >= ans.size()) {
                ans.add(new ArrayList<>());
            }

            // Store the integer in the list corresponding to its current frequency.
            ans.get(freq[c]).add(c);
            freq[c]++;
        }

        return ans;
    }
}
