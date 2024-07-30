package com.example.solution.Q1001_Q1500;

public class P1482_Minimum_Number_Of_Days_To_Make_M_Bouquets {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize start to 0 and end to the highest value in the array bloomDay.
     * 2. Do the following while the search space (start to end) doesn't become
     * empty:
     * a. Initialize mid to start + end / 2.
     * b. Find the number of bouquets possible on day mid using a helper function
     * getNumOfBouquets as follows:
     * b1. Initialize the variable numOfBouquets to 0.
     * b2. Iterate over the array bloomDay and for each index i
     * b2a. If the value bloomDay[i] is less than or equal to mid, increment the
     * count; else, reset it to 0.
     * b2b. If the value of count is equal to k, make a bouquet by incrementing
     * numOfBouquets and reset count to 0.
     * b2c. Return numOfBouquets.
     * b3. If numOfBouquets is more than or equal to m store mid as an answer in
     * ans. Shift to the left of the search space by setting end to mid - 1.
     * b4. Otherwise, shift to the right of the search space by setting start to mid
     * + 1.
     * 3. Return ans.
     */

    // Return the number of maximum bouquets that can be made on day mid.
    private int getNumOfBouquets(int[] bloomDay, int mid, int k) {
        int numOfBouquets = 0;
        int count = 0;

        for (int i = 0; i < bloomDay.length; i++) {
            // If the flower is bloomed, add to the set. Else reset the count.
            if (bloomDay[i] <= mid) {
                count++;
            } else {
                count = 0;
            }

            if (count == k) {
                numOfBouquets++;
                count = 0;
            }
        }

        return numOfBouquets;
    }

    public int minDays(int[] bloomDay, int m, int k) {
        int start = 0;
        int end = 0;
        for (int day : bloomDay) {
            end = Math.max(end, day);
        }

        int minDays = -1;
        while (start <= end) {
            int mid = (start + end) / 2;

            if (getNumOfBouquets(bloomDay, mid, k) >= m) {
                minDays = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return minDays;
    }
}
