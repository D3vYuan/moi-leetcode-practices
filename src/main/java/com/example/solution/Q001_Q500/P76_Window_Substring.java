package com.example.solution.Q001_Q500;

import java.util.HashMap;
import java.util.Map;

public class P76_Window_Substring {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-window-substring/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. We start with two pointers, left and right initially pointing to the first
     * element of the string S.
     * 2. We use the right pointer to expand the window until we get a desirable
     * window i.e. a window that contains all of the characters of T.
     * 3. Once we have a window with all the characters, we can move the left
     * pointer ahead one by one. If the window is still a desirable one we keep on
     * updating the minimum window size.
     * 4. If the window is not desirable any more, we repeat step 2 onwards.
     */
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }

        // Dictionary which keeps a count of all the unique characters in t.
        Map<Character, Integer> dictT = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(t.charAt(i), count + 1);
        }

        // Number of unique characters in t, which need to be present in the desired
        // window.
        int required = dictT.size();

        // Left and Right pointer
        int lPointer = 0, rPointer = 0;

        // formed is used to keep track of how many unique characters in t
        // are present in the current window in its desired frequency.
        // e.g. if t is "AABC" then the window must have two A's, one B and one C.
        // Thus formed would be = 3 when all these conditions are met.
        int formed = 0;

        // Dictionary which keeps a count of all the unique characters in the current
        // window.
        Map<Character, Integer> windowCounts = new HashMap<>();

        // ans list of the form (window length, left, right)
        int[] ans = { -1, 0, 0 };

        while (rPointer < s.length()) {
            // Add one character from the right to the window
            char c = s.charAt(rPointer);
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);

            // If the frequency of the current character added equals to the
            // desired count in t then increment the formed count by 1.
            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }

            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (lPointer <= rPointer && formed == required) {
                c = s.charAt(lPointer);
                // Save the smallest window until now.
                if (ans[0] == -1 || rPointer - lPointer + 1 < ans[0]) {
                    ans[0] = rPointer - lPointer + 1;
                    ans[1] = lPointer;
                    ans[2] = rPointer;
                }

                // The character at the position pointed by the
                // `Left` pointer is no longer a part of the window.
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                    formed--;
                }

                // Move the left pointer ahead, this would help to look for a new window.
                lPointer++;
            }

            // Keep expanding the window once we are done contracting.
            rPointer++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}
