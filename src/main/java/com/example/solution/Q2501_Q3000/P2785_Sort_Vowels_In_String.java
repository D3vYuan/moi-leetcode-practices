package com.example.solution.Q2501_Q3000;

import java.util.ArrayList;
import java.util.Collections;

public class P2785_Sort_Vowels_In_String {
    // Returns true if the character is a vowel.
    boolean isVowel(Character c) {
        return c == 'a' || c == 'e' || c == 'o' || c == 'u' || c == 'i'
                || c == 'A' || c == 'E' || c == 'O' || c == 'U' || c == 'I';
    }

    public String sortVowels(String s) {
        ArrayList<Character> temp = new ArrayList<>();

        // Store the vowels in the temporary string.
        for (char c : s.toCharArray()) {
            if (isVowel(c)) {
                temp.add(c);
            }
        }

        // Sort the temporary string characters in ascending order.
        Collections.sort(temp);

        StringBuilder ans = new StringBuilder();
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            // If the character is a vowel, replace it with the character in the string
            // temp.
            if (isVowel(s.charAt(i))) {
                ans.append(temp.get(j));
                j++;
            } else {
                ans.append(s.charAt(i));
            }
        }

        return ans.toString();
    }
}
