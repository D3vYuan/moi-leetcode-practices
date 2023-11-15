package com.example.solution;

import java.util.HashSet;
import java.util.Set;

public class P1930_Unique_Length_3_Palindromic_Subsequence {
    public int countPalindromicSubsequence(String s) {
        Set<Character> letters = new HashSet();
        for (Character c : s.toCharArray()) {
            letters.add(c);
        }

        int ans = 0;
        // For each character, find the first and last occurrence
        for (Character letter : letters) {
            int i = -1;
            int j = 0;

            for (int k = 0; k < s.length(); k++) {
                System.out.println(String.format("Comparing: %s vs %s", s.charAt(k), letter));
                if (s.charAt(k) == letter) {
                    if (i == -1) {
                        i = k;
                    }

                    j = k;
                }
            }

            Set<Character> between = new HashSet();
            for (int k = i + 1; k < j; k++) {
                System.out.println(String.format("Adding: %s from %s", s.charAt(k), s.substring(i, j + 1)));
                between.add(s.charAt(k));
            }

            ans += between.size();
        }

        return ans;
    }
}
