package com.example.solution.Q001_Q500;

import java.util.HashMap;

public class P387_First_Unique_Character {
    /**
     * Reference:
     * https://leetcode.com/problems/first-unique-character-in-a-string/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 
     * 
     */
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        int n = s.length();
        // build hash map : character and how often it appears
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // find the index
        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }
}
