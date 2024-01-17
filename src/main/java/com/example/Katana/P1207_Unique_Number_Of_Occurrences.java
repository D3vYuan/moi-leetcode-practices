package com.example.katana;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P1207_Unique_Number_Of_Occurrences {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> occurrences = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            occurrences.put(arr[i], occurrences.getOrDefault(arr[i], 0) + 1);
        }

        Map<Integer, Integer> count = new HashMap<>();
        List<Integer> occurrencesCount = new ArrayList<>(occurrences.values());
        for (int i = 0; i < occurrencesCount.size(); i++) {
            Integer currentCount = occurrencesCount.get(i);
            if (count.containsKey(currentCount)) {
                return false;
            }
            count.put(currentCount, count.getOrDefault(currentCount, 0) + 1);
        }
        return true;
    }
}
