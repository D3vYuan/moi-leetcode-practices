package com.example.katana.Q1501_Q2000;

public class P1716_Calculate_Money {
    public int totalMoney(int n) {
        // Mod 7
        int nextCapital = 1;
        int initialCapital = 0;
        int sum = 0;
        int startCount = 1;

        while (startCount <= n) {
            if (startCount % 7 == 1) {
                initialCapital = initialCapital + 1;
                nextCapital = initialCapital;
            } else {
                nextCapital = nextCapital + 1;
            }
            System.out.println(String.format("Adding: %d", nextCapital));
            sum += nextCapital;
            startCount++;
        }

        return sum;
    }
}
