package com.example.solution;

import java.util.HashMap;
import java.util.Map;

public class P1624_Largest_Substring {
    /**
     * Reference:
     * https://leetcode.com/problems/largest-substring-between-two-equal-characters/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy:
     * 1. Initialize a hash map firstIndex and the answer ans = -1.
     * 2. Iterate i over the indices of s:
     * a. If s[i] is in firstIndex:
     * a1. Update ans with i - firstIndex[s[i]] - 1 if it is larger.
     * b. Otherwise, set firstIndex[s[i]] = i.
     * 3. Return ans.
     */
    public int maxLengthBetweenEqualCharacters(String s) {
        Map<Character, Integer> firstIndex = new HashMap();
        int ans = -1;

        for (int i = 0; i < s.length(); i++) {
            if (firstIndex.containsKey(s.charAt(i))) {
                ans = Math.max(ans, i - firstIndex.get(s.charAt(i)) - 1);
            } else {
                firstIndex.put(s.charAt(i), i);
            }
        }

        return ans;
    }
}
