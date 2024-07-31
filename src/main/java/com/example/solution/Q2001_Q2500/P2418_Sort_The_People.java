package com.example.solution.Q2001_Q2500;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P2418_Sort_The_People {
    /**
     * Reference:
     * https://leetcode.com/problems/sort-the-people/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize numberOfPeople to the length of the names array, which is also
     * the length of the heights array.
     * 2. Initialize a map heightToNameMap to map each height with a name.
     * 3. Add each height and their corresponding name to heightToNameMap.
     * 4. Sort the heights array.
     * 5. Initialize an array sortedNames to store the resultant sorted names.
     * 6. Loop over each index i in sortedNames from the end. For index
     * numberOfPeople - i - 1, add the name associated with heights[i] from
     * heightToNameMap.
     */

    public String[] sortPeople(String[] names, int[] heights) {
        int numberOfPeople = names.length;

        // Create a map to store height-name pairs
        Map<Integer, String> heightToNameMap = new HashMap<>();

        // Populate the map with height as key and name as value
        for (int i = 0; i < numberOfPeople; i++) {
            heightToNameMap.put(heights[i], names[i]);
        }

        Arrays.sort(heights);

        String[] sortedNames = new String[numberOfPeople];

        // Populate sortedNames array in descending order of height
        for (int i = numberOfPeople - 1; i >= 0; i--) {
            sortedNames[numberOfPeople - i - 1] = heightToNameMap.get(
                    heights[i]);
        }

        return sortedNames;
    }
}
