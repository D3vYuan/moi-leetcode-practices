package com.example.solution.Q501_Q1000;

import java.util.Arrays;

public class P514_Freedom_Trail {
    /**
     * Reference:
     * https://leetcode.com/problems/freedom-trail/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Define a function countSteps that gives the minimum path between two
     * indices of ring.
     * 2. Create the variables ringLen to store the length of ring and keyLen to
     * store the length of key.
     * 3. Declare a 2D array bestSteps. It will have ringLen rows and keyLen + 1
     * columns.
     * bestSteps has one extra column because we want to store the base case of 0
     * steps.
     * 4. Initialize all values in bestSteps to the largest integer to indicate that
     * a path has not been determined.
     * 5. Set each index ringIndex, keyLength of bestSteps, to 0 for the base case
     * of zero steps.
     * 6. Iterate from the end to the beginning of key with keyIndex and through
     * ring with ringIndex:
     * a. For each character ring[charIndex] in ring:
     * a1. If ring[charIndex] equals key at keyIndex: Use the recurrence relation
     * bestSteps[ringIndex][k] = min(bestSteps[ringIndex][k], 1 +
     * countSteps[ringIndex, charIndex] + bestSteps[charIndex][keyIndex + 1]) to
     * calculate the minimum number of steps to find the character at keyIndex of
     * the keyword when the ringIndex of ring is aligned with the "12:00" position.
     * 7. Return bestSteps[0][0] which stores the minimum number of steps to spell
     * key when ring begins with its zeroth index in the "12:00" position.
     */
    public int findRotateSteps(String ring, String key) {
        int ringLen = ring.length();
        int keyLen = key.length();
        int[][] bestSteps = new int[ringLen][keyLen + 1];
        // Initialize values of best_steps to largest integer
        for (int[] row : bestSteps) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        // Initialize last column to zero to represent the word has been spelled
        for (int i = 0; i < ring.length(); i++) {
            bestSteps[i][keyLen] = 0;
        }
        // For each occurrence of the character at keyIndex of key in ring
        // Stores minimum steps to the character from ringIndex of ring
        for (int keyIndex = keyLen - 1; keyIndex >= 0; keyIndex--) {
            for (int ringIndex = 0; ringIndex < ringLen; ringIndex++) {
                for (int charIndex = 0; charIndex < ringLen; charIndex++) {
                    if (ring.charAt(charIndex) == key.charAt(keyIndex)) {
                        bestSteps[ringIndex][keyIndex] = Math.min(bestSteps[ringIndex][keyIndex],
                                1 + countSteps(ringIndex, charIndex, ringLen)
                                        + bestSteps[charIndex][keyIndex + 1]);
                    }
                }
            }
        }
        return bestSteps[0][0];
    }

    // Find the minimum steps between two indexes of ring
    private int countSteps(int curr, int next, int ringLength) {
        int stepsBetween = Math.abs(curr - next);
        int stepsAround = ringLength - stepsBetween;
        return Math.min(stepsBetween, stepsAround);
    }
}
