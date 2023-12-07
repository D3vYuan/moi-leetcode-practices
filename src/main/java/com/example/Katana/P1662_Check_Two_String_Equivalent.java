package com.example.katana;

public class P1662_Check_Two_String_Equivalent {
    public String combineString(String[] word) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length; i++) {
            sb.append(word[i]);
        }
        return sb.toString();
    }

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        String word1Combined = combineString(word1);
        String word2Combined = combineString(word2);

        if (word1Combined.length() != word2Combined.length()) {
            return false;
        }

        for (int i = 0; i < word1Combined.length(); i++) {
            if (word1Combined.charAt(i) != word2Combined.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
