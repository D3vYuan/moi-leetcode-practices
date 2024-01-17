package com.example.katana;

import java.util.HashMap;
import java.util.Map;

public class P1347_Minimum_Steps_Make_Anagram {
    public int minSteps(String s, String t) {
        Map<Character, Integer> tMapping = new HashMap<>();

        for (char item : t.toCharArray()) {
            tMapping.put(item, tMapping.getOrDefault(item, 0) + 1);
        }

        int steps = 0;
        for (char item : s.toCharArray()) {
            if (!tMapping.containsKey(item) || tMapping.get(item) <= 0) {
                steps += 1;
            } else {
                tMapping.put(item, tMapping.get(item) - 1);
            }
        }
        return steps;
    }
}
