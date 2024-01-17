package com.example.katana;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P2610_Convert_Array_To_2D {
    HashMap<Integer, Integer> adjacentList = new HashMap<>();

    public List<List<Integer>> findMatrix(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            adjacentList.put(nums[i], adjacentList.getOrDefault(nums[i], 0) + 1);
        }

        List<List<Integer>> result = new ArrayList<>();
        while (true) {
            List<Integer> row = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : adjacentList.entrySet()) {
                int value = entry.getKey();
                int count = entry.getValue();
                if (count <= 0) {
                    continue;
                }
                row.add(value);
                entry.setValue(count - 1);
            }

            if (row.isEmpty()) {
                break;
            }
            result.add(row);
        }

        return result;
    }
}
