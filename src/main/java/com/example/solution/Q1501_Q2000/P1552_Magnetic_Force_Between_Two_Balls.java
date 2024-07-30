package com.example.solution.Q1501_Q2000;

import java.util.Arrays;

public class P1552_Magnetic_Force_Between_Two_Balls {

    /**
     * Reference:
     * https://leetcode.com/problems/magnetic-force-between-two-balls/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Create a helper function called canPlaceBalls which takes in the gap x,
     * positions array position, and the number of balls m as parameters.
     * a. Initialize, prevBallPos to position[0], ballsPlaced count to 1.
     * b. Iterate on all positions from index i = 0 till position.size() - 1 or if
     * we placed all m balls:
     * b1. Place the ball at the current position position[i] if it maintains a gap
     * of x with the previous ball.
     * b2. Update prevBallPos to position[i].
     * b3. Increment ballsPlaced count by 1.
     * c. Return if ballsPlaced is equal to m.
     * 2. Initialize answer to 0, denoting maximum minimum magnetic force, and n to
     * position array's size.
     * 3. Sort the position array.
     * 4. Initilize the initial search space for the gap:
     * a. low to 1.
     * b. high to ceil(position[n - 1] / (m - 1)).
     * 5. Start a while loop until the search space is exhausted, i.e. till low <=
     * high, at each iteration:
     * a. Calculate the mid = low + (high - low) / 2.
     * b. If we can place all the balls at a gap of mid, then update answer = mid,
     * and discard the left half search space, left = mid + 1.
     * c. Otherwise, discard the right half search space, right = mid - 1.
     */

    // Check if we can place 'm' balls at 'position'
    // with each ball having at least 'x' gap.
    private boolean canPlaceBalls(int x, int[] position, int m) {
        // Place the first ball at the first position.
        int prevBallPos = position[0];
        int ballsPlaced = 1;

        // Iterate on each 'position' and place a ball there if we can place it.
        for (int i = 1; i < position.length && ballsPlaced < m; ++i) {
            int currPos = position[i];
            // Check if we can place the ball at the current position.
            if (currPos - prevBallPos >= x) {
                ballsPlaced += 1;
                prevBallPos = currPos;
            }
        }
        // If all 'm' balls are placed, return 'true'.
        return ballsPlaced == m;
    }

     public int maxDistance(int[] position, int m) {
        int answer = 0;
        int n = position.length;
        Arrays.sort(position);

        // Initial search space.
        int low = 1;
        int high = (int) Math.ceil(position[n - 1] / (m - 1.0));
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // If we can place all balls having a gap at least 'mid',
            if (canPlaceBalls(mid, position, m)) {
                // then 'mid' can be our answer,
                answer = mid;
                // and discard the left half search space.
                low = mid + 1;
            } else {
                // Discard the right half search space.
                high = mid - 1;
            }
        }
        return answer;
    }
}
