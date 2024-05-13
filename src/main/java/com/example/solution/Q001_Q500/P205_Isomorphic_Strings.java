package com.example.solution.Q001_Q500;

import java.util.Arrays;

public class P205_Isomorphic_Strings {
    /**
     * Reference:
     * https://leetcode.com/problems/isomorphic-strings/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. We define a dictionary mapping_s_t which will be used to map characters in
     * string s to characters in string t and another dictionary mapping_t_s which
     * will be used to map characters in string t to characters in string s.
     * 2. Next, we iterate over the two strings one character at a time.
     * 3. Let's assume the character in the first string is c1 and the corresponding
     * character in the second string is c2.
     * a. If c1 does not have a mapping in mapping_s_t and c2 does not have a
     * mapping in mapping_t_s, we add the corresponding mappings in both the
     * dictionaries and move on to the next character.
     * b. At this point, we expect both the character mappings to exist in the
     * dictionaries and their values should be mapping_s_t[c1] = c2 and
     * mapping_t_s[c2] = c1. If either of these conditions fails (c1 is not in the
     * dictionary, c2 is not in the dictionary, unexpected mapping), we return
     * false.4
     * 4. Return true once both the strings have been exhausted
     */
    public boolean isIsomorphic(String s, String t) {
        int[] mappingDictStoT = new int[256];
        Arrays.fill(mappingDictStoT, -1);

        int[] mappingDictTtoS = new int[256];
        Arrays.fill(mappingDictTtoS, -1);

        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            // Case 1: No mapping exists in either of the dictionaries
            if (mappingDictStoT[c1] == -1 && mappingDictTtoS[c2] == -1) {
                mappingDictStoT[c1] = c2;
                mappingDictTtoS[c2] = c1;
            }

            // Case 2: Either mapping doesn't exist in one of the dictionaries or Mapping
            // exists and
            // it doesn't match in either of the dictionaries or both
            else if (!(mappingDictStoT[c1] == c2 && mappingDictTtoS[c2] == c1)) {
                return false;
            }
        }

        return true;

    }

}
