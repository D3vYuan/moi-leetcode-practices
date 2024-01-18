package com.example.katana.Q1501_Q2000;

public class P1903_Largest_Odd_Number {
    public String largestOddNumber(String num) {
        int end = num.length() - 1;
        while (end >= 0) {
            int lastDigit = num.charAt(end);
            if (lastDigit % 2 == 1) {
                return num.substring(0, end + 1);
            }
            end--;
        }
        return "";
    }
}
