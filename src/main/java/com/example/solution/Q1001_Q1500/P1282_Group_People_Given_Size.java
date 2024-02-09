package com.example.solution.Q1001_Q1500;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P1282_Group_People_Given_Size {
    /**
     * Reference:
     * https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. Initialize an empty list of lists ans to store the groups' indices.
     * 2. Create a hash map szToGroup where the keys are integers representing group
     * sizes, and the values are the arrays of the corresponding indices in the
     * group.
     * 3. Iterate over the array groupSizes, for each index i:
     * a. Insert the index i into the list szToGroup[groupSizes[i]].
     * b. If the size of the list becomes equal to groupSizes[i], store it in the
     * answer ans. Also, clear the array for the key groupSizes[i] in the map
     * szToGroup.
     * 4. Return ans.
     */
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> ans = new ArrayList<>();

        // A map from group size to the list of indices that are there in the group.
        Map<Integer, List<Integer>> szToGroup = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            if (!szToGroup.containsKey(groupSizes[i])) {
                szToGroup.put(groupSizes[i], new ArrayList<>());
            }

            List<Integer> group = szToGroup.get(groupSizes[i]);
            group.add(i);

            // When the list size equals the group size, empty it and store it in the
            // answer.
            if (group.size() == groupSizes[i]) {
                ans.add(group);
                szToGroup.remove(groupSizes[i]);
            }
        }

        return ans;
    }
}
