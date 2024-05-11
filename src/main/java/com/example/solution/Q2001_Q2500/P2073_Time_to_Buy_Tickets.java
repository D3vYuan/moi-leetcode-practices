package com.example.solution.Q2001_Q2500;

import java.util.LinkedList;
import java.util.Queue;

public class P2073_Time_to_Buy_Tickets {
    /**
     * Reference:
     * https://leetcode.com/problems/time-needed-to-buy-tickets/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. Initialize a queue queue.
     * 2. Iterate through the tickets array:
     * a. Add the index i to the queue.
     * 3. Initialize time to 0.
     * 4. Enter a loop that continues until the queue is empty:
     * a. Increment time by 1.
     * b. Get the front element front from the queue.
     * c. Decrement tickets[front] by 1 to buy one ticket for the person at index
     * front.
     * d. If the person at index k has bought all their tickets (k == front &&
     * tickets[front] == 0):
     * d1. Return the time.
     * e. If there are more tickets at index front:
     * e1. Re-add the index front to the end of the queue (queue.add(front)).
     * f. Return time.
     */
    public int timeRequiredToBuy(int[] tickets, int k) {
        Queue<Integer> queue = new LinkedList<>();

        // Initialize the queue with ticket indices
        for (int i = 0; i < tickets.length; i++) {
            queue.add(i);
        }

        int time = 0;

        // Loop until the queue is empty
        while (!queue.isEmpty()) {
            // Increment the time counter for each iteration
            time++;

            // Get the front element of the queue
            int front = queue.poll();

            // Buy a ticket for the front person
            tickets[front]--;

            // If person k bought all their tickets, return time
            if (k == front && tickets[front] == 0) {
                return time;
            }

            // Re-add the current index to the queue for the next iteration
            if (tickets[front] != 0) {
                queue.add(front);
            }
        }

        return time;
    }
}
