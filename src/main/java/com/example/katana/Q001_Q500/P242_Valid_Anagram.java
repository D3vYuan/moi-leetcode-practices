package com.example.katana.Q001_Q500;

import java.util.HashMap;
import java.util.Map;

public class P242_Valid_Anagram {
    public boolean isAnagram(String s, String t) {
        if (t.length() != s.length()) {
            return false;
        }
        Map<String, Integer> occurrences = new HashMap<>();
        for (String item : s.split("")) {
            occurrences.put(item, occurrences.getOrDefault(item, 0) + 1);
        }

        for (String item : t.split("")) {
            System.out.println(String.format("Checking: %s - remaining %d", item, occurrences.getOrDefault(item, 0)));
            if (occurrences.getOrDefault(item, 0) < 0) {
                return false;
            }
            occurrences.put(item, occurrences.getOrDefault(item, 0) - 1);
        }

        return true;
    }
}
