package com.example.katana;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class P2484_Count_Palindrome_Subsequences {
    // Strategy
    // [1] Using sliding window get all the subsequence
    // [2] Use Memo to Store Processed Data
    // [3] If substring is memo, store to set
    
    private Map<String, Boolean> memo = new HashMap<>();
    private List<String> palindromes = new ArrayList<>();
    private int subStringLength = 5;

    private boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        boolean isPalindrome = true;
        while (start < end) {
            char characterStart = s.charAt(start);
            char characterEnd = s.charAt(end);
            if (characterStart != characterEnd) {
                isPalindrome = false;
                break;
            }
            start++;
            end--;
        }
        return isPalindrome;
    }

    private boolean generateSubsequences(int startIndex, int endIndex, String s) {
        if (endIndex >= s.length()) {
            return false;
        }

        return false;
        // if (endIndex - )
        // String subString =
    }

    public int countPalindromes(String s) {
        if (s.length() <= 0) {
            return 0;
        }

        if (subStringLength == 1) {
            return s.length();
        }

        int stringLength = s.length();
        generateSubsequences(0, 1, s);

        return palindromes.size();
    }
}
