package com.example.solution.Q1501_Q2000;

import java.util.LinkedList;
import java.util.Queue;

public class P1823_Find_Winner_Of_Circular_Game {

    /**
     * Reference:
     * https://leetcode.com/problems/find-the-winner-of-the-circular-game/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a queue of size n, where the elements are labeled 1 to n.
     * 2. While more than 1 friend is remaining
     * a. Remove the next k-1 friends and re-add them to the queue.
     * b. Remove the next friend (the k-th friend that should be eliminated in the
     * game)
     * 3. Return the value of the last friend remaining
     */

    public int findTheWinner(int n, int k) {
        // Initialize queue with n friends
        Queue<Integer> circle = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) {
            circle.add(i);
        }

        // Perform eliminations while more than 1 player remains
        while (circle.size() > 1) {
            // Process the first k-1 friends without eliminating them
            for (int i = 0; i < k - 1; i++) {
                circle.add(circle.remove());
            }
            // Eliminate the k-th friend
            circle.remove();
        }

        return circle.peek();
    }
}
