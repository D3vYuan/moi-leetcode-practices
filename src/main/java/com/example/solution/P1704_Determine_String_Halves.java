package com.example.solution;

public class P1704_Determine_String_Halves {

    /**
     * Reference:https://leetcode.com/problems/determine-if-string-halves-are-alike/editorial/?envType=daily-question&envId=2024-01-17
     * 
     * Strategy:
     * 1. Step 1: Initialize a function that counts vowels.
     * 2. Step 2: Count the number of vowels of the first half of s (i.e., a) and
     * the second half of s (i.e., b) with that function.
     * 3. Step 3: Return if the number of vowels equals.
     * 
     */
    public boolean halvesAreAlike(String s) {
        int n = s.length();
        return countVowel(0, n / 2, s) == countVowel(n / 2, n, s);
    }

    private int countVowel(int start, int end, String s) {
        String vowels = "aeiouAEIOU";
        int answer = 0;
        for (int i = start; i < end; i++) {
            if (vowels.indexOf(s.charAt(i)) != -1) {
                answer++;
            }
        }
        return answer;
    }
}
