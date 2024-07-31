package com.example.solution.Q1001_Q1500;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P1122_Relative_Sort_Array {
    /**
     * Reference:
     * https://leetcode.com/problems/relative-sort-array/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize an empty result array and an empty remaining array.
     * 2. Initialize an unordered map (countMap) with elements from arr2 as keys and
     * initial count as 0.
     * 3. Iterate through arr1.
     * a. If the element is present in countMap (i.e., present in arr2).
     * Increment the count in countMap for that element.
     * b. Else (element not present in arr2).
     * Add the element to the remaining array.
     * 4. Sort the remaining array.
     * a. Iterate through arr2.
     * For each element in arr2.
     * Add the element to the result array, countMap[element] times.
     * 5. Iterate through the remaining array.
     * a. Add all elements from the remaining array to the result array.
     * 6. Return the result array.
     */

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> countMap = new HashMap<>();
        List<Integer> remaining = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        // Initialize count map with relative order elements
        for (int value : arr2) {
            countMap.put(value, 0);
        }

        // Count occurrences of elements in target array
        for (int value : arr1) {
            if (countMap.containsKey(value)) {
                countMap.put(value, countMap.get(value) + 1);
            } else {
                remaining.add(value);
            }
        }

        // Sort the remaining elements
        Collections.sort(remaining);

        // Add elements as per relative order
        for (int value : arr2) {
            for (int j = 0; j < countMap.get(value); j++) {
                result.add(value);
            }
        }

        // Add remaining elements
        result.addAll(remaining);

        // Convert ArrayList to array
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
