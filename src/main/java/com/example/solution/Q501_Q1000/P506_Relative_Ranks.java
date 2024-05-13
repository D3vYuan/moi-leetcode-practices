package com.example.solution.Q501_Q1000;

import java.util.PriorityQueue;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class P506_Relative_Ranks {
    /**
     * Reference:
     * https://leetcode.com/problems/relative-ranks/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a variable N to the length of the score array.
     * 2. Initialize a max-heap (priority queue) that will store (score, index)
     * pairs.
     * 3. For each index in score, add a pair to the heap with the score and index.
     * 4. Initialize a string array rank of size N for storing the answer.
     * 5. Initialize a varaible place to 1.
     * 6. Assign ranks to athletes. While the heap is not empty:
     * a. Pop the pair with the highest score from the heap. Save the index in
     * originalIndex.
     * b. Add the corresponding place to the rank array at index originalIndex:
     * b1. If place is 1, assign the athlete the "Gold Medal".
     * b2. If place is 2, assign the athlete the "Silver Medal".
     * b3. If place is 3, assign the athlete the "Bronze Medal".
     * b4. Otherwise, set rank[originalIndex] = str(place).
     * c. Increment place.
     * 7. Return rank.
     */
    public String[] findRelativeRanks(int[] score) {
        int N = score.length;

        // Create a max heap of pairs (score, index)
        PriorityQueue<Pair<Integer, Integer>> heap = new PriorityQueue<>(
                (a, b) -> b.getKey() - a.getKey());
        for (int i = 0; i < N; i++) {
            heap.add(new ImmutablePair<>(score[i], i));
        }

        // Assign ranks to athletes
        String[] rank = new String[N];
        int place = 1;
        while (!heap.isEmpty()) {
            Pair<Integer, Integer> pair = heap.poll();
            int originalIndex = pair.getValue();
            if (place == 1) {
                rank[originalIndex] = "Gold Medal";
            } else if (place == 2) {
                rank[originalIndex] = "Silver Medal";
            } else if (place == 3) {
                rank[originalIndex] = "Bronze Medal";
            } else {
                rank[originalIndex] = String.valueOf(place);
            }
            place++;
        }
        return rank;
    }
}
