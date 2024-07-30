package com.example.solution.Q1001_Q1500;

public class P1052_Grumpy_Bookstore_Owner {
    /**
     * Reference:
     * https://leetcode.com/problems/grumpy-bookstore-owner/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize variables:
     * a. n as the length of customers array.
     * b. unrealizedCustomers to store the number of unsatisfied customer for each
     * window
     * 2. Calculate unrealizedCustomers for the initial window.
     * 3. Initialize maxUnrealizedCustomers with the initial window.
     * 4. Move the window over the customers array.
     * a. Add the current minute's customers if the owner is grumpy.
     * b. Remove the customers who entered minutes ago and are now out of the
     * window's range.
     * c. Update maxUnrealizedCustomers to be the maximum value between the current
     * maxUnrealizedCustomers and unrealizedCustomers.
     * 5. Initialize a variable totalCustomers to maxUnrealizedCustomers.
     * 6. Add all satisfied customers during the non-grumpy minutes.
     * 7. Return totalCustomers, which holds the total number of satisfied
     * customers.
     */

    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int unrealizedCustomers = 0;

        // Calculate initial number of unrealized customers in first 'minutes' window
        for (int i = 0; i < minutes; i++) {
            unrealizedCustomers += customers[i] * grumpy[i];
        }

        int maxUnrealizedCustomers = unrealizedCustomers;

        // Slide the 'minutes' window across the rest of the customers array
        for (int i = minutes; i < n; i++) {
            // Add the current minute's unsatisfied customers if the owner is grumpy
            // and remove the customers that are out of the current window
            unrealizedCustomers += customers[i] * grumpy[i];
            unrealizedCustomers -= customers[i - minutes] * grumpy[i - minutes];

            // Update the maximum unrealized customers
            maxUnrealizedCustomers = Math.max(
                    maxUnrealizedCustomers,
                    unrealizedCustomers);
        }

        // Start with maximum possible satisfied customers due to secret technique
        int totalCustomers = maxUnrealizedCustomers;

        // Add the satisfied customers during non-grumpy minutes
        for (int i = 0; i < customers.length; i++) {
            totalCustomers += customers[i] * (1 - grumpy[i]);
        }

        // Return the maximum number of satisfied customers
        return totalCustomers;
    }
}
