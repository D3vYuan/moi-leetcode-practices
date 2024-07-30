package com.example.solution.Q1501_Q2000;

public class P1791_Average_Waiting_Time {
    /**
     * Reference:
     * https://leetcode.com/problems/average-waiting-time/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize integers nextIdleTime and netWaitTime with 0.
     * 2. Iterate through the customers array:
     * a. Set nextIdleTime as the maximum of customer's arrival time and the current
     * value of nextIdleTime plus the order preparation time.
     * b. Increment netWaitTime by the difference of nextIdleTime and the customer's
     * arrival time.
     * 3. Divide the netWaitTime by customers.size to get the averageWaitTime.
     * 4. Return the averageWaitTime.
     */

    public double averageWaitingTime(int[][] customers) {
        int nextIdleTime = 0;
        long netWaitTime = 0;

        for (int i = 0; i < customers.length; i++) {
            // The next idle time for the chef is given by the time of delivery
            // of current customer's order.
            nextIdleTime = Math.max(customers[i][0], nextIdleTime) +
                    customers[i][1];

            // The wait time for the current customer is the difference between
            // his delivery time and arrival time.
            netWaitTime += nextIdleTime - customers[i][0];
        }

        // Divide by total customers to get average.
        double averageWaitTime = (double) netWaitTime / customers.length;
        return averageWaitTime;
    }
}
