package com.example.katana.Q001_Q500;

public class P387_First_Unique_Character {
    public int firstUniqChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            int firstOccurrence = s.indexOf(currentChar);
            int lastOccurrence = s.lastIndexOf(currentChar);
            if (firstOccurrence == lastOccurrence) {
                return i;
            }
        }
        return -1;
    }
}
