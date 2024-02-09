package com.example.solution.Q001_Q500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P49_Group_Anagrams {
    /**
     * Reference:
     * https://leetcode.com/problems/group-anagrams/editorial/?envType=daily-question&envId=2024-01-19
     *
     * Strategy:
     * 1. Maintain a map ans: {String -> List} where each key K is a sorted string,
     * and each value is the list of strings from the initial input that when
     * sorted, are equal to K.
     * 2. In Java, we will store the key as a string, eg. code
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0)
            return new ArrayList<>();

        Map<String, List<String>> ans = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key))
                ans.put(key, new ArrayList<>());
            ans.get(key).add(s);
        }
        return new ArrayList<>(ans.values());
    }
}
