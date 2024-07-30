package com.example.solution.Q2001_Q2500;

import java.util.Arrays;

public class P2285_Maximum_Total_Importance_Of_Roads {
    /**
     * Reference:
     * https://leetcode.com/problems/maximum-total-importance-of-roads/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize an array degree of size N to store the degree of each node.
     * Initially, all values are 0.
     * 2. Iterate over the list of edges roads and increment the degree for each of
     * the nodes the road connects, i.e. edge[0] and edge[1].
     * 3. Sort the array degree in the ascending order.
     * 4. Initialize the variable value to 1, this will be the value we assign to
     * the nodes.
     * 5. Initialize the variable totalImportance to 0 to store the maximum
     * importance of all edges.
     * 6. Iterate over the array degree and keep adding the importance as node
     * degree * assigned value to the variable totalImportance. Also, increment the
     * value value to assign it to the next node.
     * 7. Return totalImportance.
     */

    public long maximumImportance(int n, int[][] roads) {
        long[] degree = new long[n];

        for (int[] edge : roads) {
            degree[edge[0]]++;
            degree[edge[1]]++;
        }

        Arrays.sort(degree);

        long value = 1;
        long totalImportance = 0;
        for (long d : degree) {
            totalImportance += (value * d);
            value++;
        }

        return totalImportance;
    }
}
