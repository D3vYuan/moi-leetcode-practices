package com.example.katana.Q001_Q500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class P49_Group_Anagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> anagramsGroups = new ArrayList<>();
        if (strs.length <= 0) {
            return new ArrayList<>();
        }

        if (strs.length == 1) {
            anagramsGroups.add(Arrays.asList(strs));
            return anagramsGroups;
        }

        Map<String, List<String>> anagramsMap = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String currentString = strs[i];
            String sortedString = Arrays.stream(currentString.split("")).sorted()
                    .collect(Collectors.joining());
            if (!anagramsMap.containsKey(sortedString)) {
                anagramsMap.put(sortedString, new ArrayList<>());
            }
            System.out.println(String.format("Adding: %s to key %s", currentString, sortedString));
            anagramsMap.get(sortedString).add(currentString);
        }
        anagramsMap.values().stream().forEach(value -> anagramsGroups.add(value));
        return anagramsGroups;
    }
}
