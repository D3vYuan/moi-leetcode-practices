package com.example.solution.Q1001_Q1500;

public class P1347_Minimum_Steps_Make_Anagram {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/editorial/?envType=daily-question&envId=2024-01-15
     * 
     * Explanation:
     * 1. One thing to observe here is that we do not need to touch the instances of
     * a character that are present in both strings. For example, if the two strings
     * are s = ba and t = aa, we do not need to change one of the a characters in
     * both two strings.
     * 2. The character instance which is in t but not in s can be replaced with a
     * character that is present in s. To find the minimum characters required to
     * make t and s anagrams, we can find the count of characters in t which are not
     * present in s.
     * 3. To find this, we can record the frequency of each character in both
     * strings s and t, and calculate the frequency difference of each character
     * (freq in t - freq in s). One important thing to note is that this difference
     * can be positive or negative, for example, if s = bba and t = baa, the
     * frequency difference of a is 1 (t has 2 occurrences of a while s has 1, 2 - 1
     * = 1) and the frequency difference of b is -1 (t has 1 occurrence of b while s
     * has 2, 1 - 2 = -1). However, we only need to focus on the positive value
     * which implies that there are more instances of this character in t, why?
     * This is because the two values (the sum of the positive and negative
     * differences) are equal in absolute value! The positive value comes from the
     * character in t that needs to be replaced, the negative value comes from the
     * character in s that waits for the corresponding replacement in t
     * 
     * Strategy:
     * 1. Initialize an array count of size 26, all indices point to 0 initially to
     * denote the frequency of each character.
     * 2. Iterate over the integer from 0 to the last index in s or t, for each
     * index i:
     * a. Increment the frequency of character t[i] in the array count.
     * b. Decrement the frequency of character s[i] in the array count.
     * 3. Initialize the variable ans to 0
     * 4. Iterate over the integers from 0 to 25, and for each positive frequency
     * difference, add it to the variable ans.
     * 5. Return ans.
     * 
     */
    public int minSteps(String s, String t) {
        int[] count = new int[26];
        // Storing the difference of frequencies of characters in t and s.
        for (int i = 0; i < s.length(); i++) {
            count[t.charAt(i) - 'a']++;
            count[s.charAt(i) - 'a']--;
        }

        int ans = 0;
        // Adding the difference where string t has more instances than s.
        // Ignoring where t has fewer instances as they are redundant and
        // can be covered by the first case.
        for (int i = 0; i < 26; i++) {
            ans += Math.max(0, count[i]);
        }

        return ans;
    }
}
