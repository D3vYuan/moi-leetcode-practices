package com.example.solution.Q1501_Q2000;

import java.util.Collections;
import java.util.PriorityQueue;

public class P1647_Minimum_Deletions_Character_Frequencies {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. Store the frequency for each character in the given string s in a
     * frequency array called frequency. We store the frequency for a character c at
     * index c - 'a'. Thus, we will need 26 indices (from 0 to 25) to store the
     * frequencies of the characters.
     * 2. Store the frequencies in the max heap pq. Only insert non-zero frequencies
     * into the priority queue.
     * 3. While the priority queue pq has more than one element:
     * a. Store the top element in the variable topElement and pop it.
     * b. If topElement and the new top element in pq are the same, decrement the
     * value topElement and increment deleteCount. If topElement is still greater
     * than zero, then push it back into pq.
     * 4. Return deleteCount.
     */
    public int minDeletions(String s) {
        // Store the frequency of each character
        int[] frequency = new int[26];
        for (int i = 0; i < s.length(); i++) {
            frequency[s.charAt(i) - 'a']++;
        }

        // Add the frequencies to the priority queue
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < 26; i++) {
            if (frequency[i] > 0) {
                pq.add(frequency[i]);
            }
        }

        int deleteCount = 0;
        while (pq.size() > 1) {
            int topElement = pq.remove();

            // If the top two elements in the priority queue are the same
            if (topElement == pq.peek()) {
                // Decrement the popped value and push it back into the queue
                if (topElement - 1 > 0) {
                    pq.add(topElement - 1);
                }
                deleteCount++;
            }
        }

        return deleteCount;
    }
}
