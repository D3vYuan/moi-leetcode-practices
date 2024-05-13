package com.example.solution.Q3001_Q3500;

import java.util.Comparator;
import java.util.PriorityQueue;

public class P3075_Maximize_Happiness_Of_Selected_Children {
    /**
     * Reference:
     * https://leetcode.com/problems/maximize-happiness-of-selected-children/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Declare a max heap pq.
     * 2. Initialize variable turns = 0 to represent the number of selection rounds
     * passed.
     * 3. Initialize variable totalHappinessSum = 0 to accumulate the total sum of
     * happiness achieved.
     * 4. Push all the elements of the happiness into pq.
     * 5. For the ith selection round (0 <= i < k, zero indexed):
     * a. Pick the ith biggest happiness score by querying the top element stored in
     * pq, subtract it from turns and add it to totalHappinessSum if the result of
     * the subtraction is bigger than zero.
     * b1. Pop the maximum value stored in pq.
     * b2. Increment turns by one.
     * c. Return totalHappinessSum.
     */
    public long maximumHappinessSum(int[] happiness, int k) {
        // Create a max heap using PriorityQueue with a custom comparator
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        // Add all elements to the priority queue
        for (int h : happiness) {
            pq.add(h);
        }

        long totalHappinessSum = 0;
        int turns = 0;

        for (int i = 0; i < k; i++) {
            // Add the current highest value to the total happiness sum and remove it from
            // the max heap
            totalHappinessSum += Math.max(pq.poll() - turns, 0);

            // Increment turns for the next iteration
            turns++;
        }

        return totalHappinessSum;
    }
}
