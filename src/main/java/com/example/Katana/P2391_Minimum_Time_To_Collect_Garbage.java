package com.example.katana;

import java.util.HashMap;
import java.util.Map;

public class P2391_Minimum_Time_To_Collect_Garbage {
    public int garbageCollection(String[] garbage, int[] travel) {
        Map<String, Integer> lastOccurrences = new HashMap<>();
        Map<String, Integer> totalOccurrences = new HashMap<>();

        // Compute Travel Distance
        int[] travelCost = new int[travel.length + 1];
        travelCost[0] = 0;
        for (int i = 0; i < travel.length; i++) {
            travelCost[i + 1] = travel[i] + travelCost[i];
        }

        // Compute Last and Total Occurrence
        for (int i = 0; i < garbage.length; i++) {
            String currentGarbage = garbage[i];
            for (String item : currentGarbage.split("")) {
                if (!lastOccurrences.containsKey(item)) {
                    lastOccurrences.put(item, 0);
                }

                if (!totalOccurrences.containsKey(item)) {
                    totalOccurrences.put(item, 0);
                }

                lastOccurrences.put(item, i);
                totalOccurrences.put(item, totalOccurrences.get(item) + 1);
            }
        }

        // Compute Distance for each type "P", "M", "G"
        String[] garbageTypes = new String[] { "P", "M", "G" };
        int totalDistance = 0;
        for (int i = 0; i < garbageTypes.length; i++) {
            String garbageType = garbageTypes[i];
            int garbageLastOccurrences = lastOccurrences.getOrDefault(garbageType, 0);
            int garbageTotalOccurrences = totalOccurrences.getOrDefault(garbageType, 0);

            int garbageLastOccurrencesDistance = travelCost[garbageLastOccurrences];
            int garbageTotalOccurrencesDistance = garbageTotalOccurrences;

            totalDistance += garbageLastOccurrencesDistance + garbageTotalOccurrencesDistance;
        }

        return totalDistance;
    }
}
