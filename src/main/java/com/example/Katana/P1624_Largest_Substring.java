package com.example.katana;

import java.util.HashMap;
import java.util.Map;

public class P1624_Largest_Substring {
    public int maxLengthBetweenEqualCharacters(String s) {
        Map<String, Integer> startIndexes = new HashMap<>();
        int longestSubString = -1;
        for (int i = 0; i < s.length(); i++) {
            String currentCharacter = s.substring(i, i + 1);
            if (startIndexes.containsKey(currentCharacter)) {
                int previousStartIndex = startIndexes.get(currentCharacter);
                System.out.println(String.format("Found: Character [%s] | Start [%d] | End [%d] | Length [%d]",
                        currentCharacter, previousStartIndex, i, i - previousStartIndex - 1));
                longestSubString = Math.max(longestSubString, i - previousStartIndex - 1);
                continue;
            }
            startIndexes.put(currentCharacter, i);
        }

        return longestSubString;
    }
}
