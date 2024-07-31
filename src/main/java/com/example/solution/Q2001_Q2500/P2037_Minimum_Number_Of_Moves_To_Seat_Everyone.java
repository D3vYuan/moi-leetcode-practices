package com.example.solution.Q2001_Q2500;

import java.util.Arrays;

public class P2037_Minimum_Number_Of_Moves_To_Seat_Everyone {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-number-of-moves-to-seat-everyone/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Sort the given arrays seats and students.
     * 2. Initialize a variable moves to 0 for storing the result.
     * 3. For each index in the seats array:
     * a. Add the absolute difference between the position of the seat at that index
     * and the position of the student at that index to moves.
     * 4. Return moves.
     */

    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int moves = 0;
        for (int i = 0; i < seats.length; i++) {
            // Add the absolute value of the difference
            // between the position of the seat and the student
            moves += Math.abs(seats[i] - students[i]);
        }
        return moves;
    }
}
