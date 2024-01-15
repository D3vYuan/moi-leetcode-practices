package com.example.katana;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class P1897_Redistribute_Characters {
    public boolean makeEqual(String[] words) {
        Map<String, Integer> mappings = new HashMap<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                String character = word.substring(i, i + 1);
                mappings.put(character, mappings.getOrDefault(character, 0) + 1);
            }
        }

        int characterLength = words.length;
        Optional<Integer> inconsistentNumber = mappings.values().stream()
                .filter(number -> number % characterLength != 0)
                .findAny();
        return !inconsistentNumber.isPresent();
    }
}
