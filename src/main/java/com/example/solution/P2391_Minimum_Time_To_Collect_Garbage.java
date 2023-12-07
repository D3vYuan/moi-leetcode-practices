package com.example.solution;

import java.util.HashMap;
import java.util.Map;

public class P2391_Minimum_Time_To_Collect_Garbage {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-amount-of-time-to-collect-garbage/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy
     * 1. Initialize an array prefixSum of the size travel.length + 1, the $i_{th}$
     * value in this array will store the sum of first i - 1 elements in the array
     * travel.
     * 2. Initialize an empty map garbageLastPos from character to integer, this map
     * will store the last index of the house for the type of garbage equal to the
     * key.
     * 3. Initialize an empty map garbageCount from character to integer, this map
     * will store the count of the type of garbage represented by the key in all the
     * houses.
     * 4. Iterate over the array garbage and iterate over each garbage for each
     * house, increment the count in garbageCount and store the index in the map
     * garbageLastPos.
     * 5. Iterate over each garbage type and for each type (say c) add the
     * garbageCount[c] and prefixSum[garbageLastPos[c]] to the answer variable ans.
     * 6. Return ans.
     */
    public int garbageCollection(String[] garbage, int[] travel) {
        // Array to store the prefix sum in travel.
        int[] prefixSum = new int[travel.length + 1];
        prefixSum[1] = travel[0];
        for (int i = 1; i < travel.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + travel[i];
        }

        // Map to store garbage type to the last house index.
        Map<Character, Integer> garbageLastPos = new HashMap<Character, Integer>();

        // Map to store the total count of each type of garbage in all houses.
        Map<Character, Integer> garbageCount = new HashMap<Character, Integer>();
        for (int i = 0; i < garbage.length; i++) {
            for (char c : garbage[i].toCharArray()) {
                garbageLastPos.put(c, i);
                garbageCount.put(c, garbageCount.getOrDefault(c, 0) + 1);
            }
        }

        String garbageTypes = "MPG";
        int ans = 0;
        for (char c : garbageTypes.toCharArray()) {
            // Add only if there is at least one unit of this garbage.
            if (garbageCount.containsKey(c)) {
                ans += prefixSum[garbageLastPos.get(c)] + garbageCount.get(c);
            }
        }

        return ans;
    }
}
