package com.example.solution.Q2501_Q3000;

public class P2582_Pass_The_Pillow {
    /**
     * Reference:
     * https://leetcode.com/problems/pass-the-pillow/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. fullRounds = time / (n - 1) calculates how many complete rounds of passing
     * occur.
     * 2. extraTime = time % (n - 1) calculates the remaining time after complete
     * rounds.
     * 3. Check if fullRounds % 2 == 0:
     * a. If true, calculate the position as extraTime + 1.
     * b. If false, calculate the position as n - extraTime.
     * 4. Return the position determined in the above step, which indicates the
     * person holding the pillow after time seconds.
     */

    public int passThePillow(int n, int time) {
        // Calculate the number of complete rounds of pillow passing
        int fullRounds = time / (n - 1);

        // Calculate the remaining time after complete rounds
        int extraTime = time % (n - 1);

        // Determine the position of the person holding the pillow
        // If fullRounds is even, the pillow is moving forward.
        // If fullRounds is odd, the pillow is moving backward.
        if (fullRounds % 2 == 0) {
            return extraTime + 1; // Position when moving forward
        } else {
            return n - extraTime; // Position when moving backward
        }
    }
}
