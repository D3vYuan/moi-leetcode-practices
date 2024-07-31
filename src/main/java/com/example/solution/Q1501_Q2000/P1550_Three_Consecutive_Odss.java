package com.example.solution.Q1501_Q2000;

public class P1550_Three_Consecutive_Odss {
    /**
     * Reference:
     * https://leetcode.com/problems/three-consecutive-odds/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a variable consecutiveOdds to store the number of consecutive
     * odd numbers during the loop.
     * 2. Loop through the given array:
     * a. If the current element is odd, increment consecutiveOdds.
     * b. Otherwise, reset consecutiveOdds to 0.
     * c. If consecutiveOdds is equal to 3, return true.
     * 3. Return false, indicating no three consecutive odds were found.
     */

    public boolean threeConsecutiveOdds(int[] arr) {
        int consecutiveOdds = 0;

        // Loop through each element in the array
        for (int i = 0; i < arr.length; i++) {
            // Increment the counter if the number is odd,
            // else reset the counter
            if (arr[i] % 2 == 1) {
                consecutiveOdds++;
            } else {
                consecutiveOdds = 0;
            }

            // Check if there are three consecutive odd numbers
            if (consecutiveOdds == 3) {
                return true;
            }
        }

        return false;
    }
}
