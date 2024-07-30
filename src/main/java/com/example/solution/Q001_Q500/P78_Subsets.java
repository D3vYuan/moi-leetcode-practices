package com.example.solution.Q001_Q500;

import java.util.ArrayList;
import java.util.List;

public class P78_Subsets {
    /**
     * Reference:
     * https://leetcode.com/problems/subsets/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1.
     */

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        output.add(new ArrayList<Integer>());

        for (int num : nums) {
            List<List<Integer>> newSubsets = new ArrayList<>();
            for (List<Integer> curr : output) {
                newSubsets.add(
                        new ArrayList<Integer>(curr) {
                            {
                                add(num);
                            }
                        });
            }
            for (List<Integer> curr : newSubsets) {
                output.add(curr);
            }
        }
        return output;
    }
}
