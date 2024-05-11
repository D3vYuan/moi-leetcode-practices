package com.example.solution.Q501_Q1000;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P950_Reveal_Cards_Increasing_Order {
    /**
     * Reference:
     * https://leetcode.com/problems/reveal-cards-in-increasing-order/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. Initialize N to the length of the deck.
     * 2. Create a queue to store the indices of the cards, and add the indices 0 to
     * N to the queue.
     * 3. Sort the deck.
     * 4. Initialize an array result of size N to store the answer.
     * 5. Loop through the cards, placing each one in the correct spot in result:
     * 6. Set result at the front index in the queue to deck[i].
     * 7. Take the next index in the queue and move it to the back of the queue.
     * 8. Return result.
     * 
     */
    public int[] deckRevealedIncreasing(int[] deck) {
        int N = deck.length;
        Queue<Integer> queue = new LinkedList<>();

        // Create a queue of indexes
        for (int i = 0; i < N; i++) {
            queue.add(i);
        }

        Arrays.sort(deck);

        // Put cards at correct index in result
        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            // Reveal Card and Place in Result
            result[queue.poll()] = deck[i];

            // Move next card to bottom
            queue.add(queue.poll());
        }
        return result;
    }
}
