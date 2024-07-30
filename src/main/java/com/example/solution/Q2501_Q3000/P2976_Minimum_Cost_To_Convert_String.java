package com.example.solution.Q2501_Q3000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class P2976_Minimum_Cost_To_Convert_String {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-cost-to-convert-string-i/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Main method minimumCost:
     * a. Create an adjacencyList with 26 entries (one for each lowercase letter).
     * b. Iterate through the original array: For each index i:
     * Add an edge to adjacencyList from original[i] to changed[i], with the
     * corresponding cost[i].
     * c. For each of the 26 characters, call dijkstra to find the shortest path
     * from this character to all other characters.
     * d. Store the results in a 2D array minConversionCosts of size 26×26.
     * e. Initialize a variable totalCost to 0.
     * f. Iterate through the length of source:
     * If the character at the current position differs from target:
     * Look up the conversion cost in minConversionCosts:
     * If the conversion is impossible (cost is -1), return -1.
     * Else, add the cost to totalCost.
     * g. Return totalCost as the answer.
     * 
     * 2. Helper method dijkstra:
     * a. Define a method dijkstra with parameters: startChar and adjacencyList.
     * b. Create a priority queue priorityQueue with each element as a pair of
     * (cost, character). Sort the queue by cost (lowest first).
     * c. Initialize an array minCosts of size 26 with all values set to -1
     * (representing unreachable positions).
     * d. Add startChar to priorityQueue with a cost of 0.
     * e. While priorityQueue is not empty:
     * Poll a pair (currentCost, currentChar) from the queue.
     * Loop over all possible conversions from currentChar using the adjacencyList.
     * For each conversion to targetChar:
     * Find the newTotalCost to do the conversion as currentCost + conversionCost.
     * If the conversion hasn't been reached yet minCosts[targetChar] == -1, or
     * newTotalCost is less than the previous cost in minCosts[targetChar]:
     * Set minCosts[targetChar] as newTotalCost.
     * Add the pair (newTotalCost, targetChar) to the priority queue.
     * f. Return minCosts.
     */

    public long minimumCost(
            String source,
            String target,
            char[] original,
            char[] changed,
            int[] cost) {
        // Create a graph representation of character conversions
        List<int[]>[] adjacencyList = new List[26];
        for (int i = 0; i < 26; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        // Populate the adjacency list with character conversions
        int conversionCount = original.length;
        for (int i = 0; i < conversionCount; i++) {
            adjacencyList[original[i] - 'a'].add(
                    new int[] { changed[i] - 'a', cost[i] });
        }

        // Calculate shortest paths for all possible character conversions
        long[][] minConversionCosts = new long[26][26];
        for (int i = 0; i < 26; i++) {
            minConversionCosts[i] = dijkstra(i, adjacencyList);
        }

        // Calculate the total cost of converting source to target
        long totalCost = 0;
        int stringLength = source.length();
        for (int i = 0; i < stringLength; i++) {
            if (source.charAt(i) != target.charAt(i)) {
                long charConversionCost = minConversionCosts[source.charAt(i) - 'a'][target.charAt(
                        i) -
                        'a'];
                if (charConversionCost == -1) {
                    return -1; // Conversion not possible
                }
                totalCost += charConversionCost;
            }
        }
        return totalCost;
    }

    // Find minimum conversion costs from a starting character to all other
    // characters
    private long[] dijkstra(int startChar, List<int[]>[] adjacencyList) {
        // Priority queue to store characters with their conversion cost, sorted by cost
        PriorityQueue<Pair<Long, Integer>> priorityQueue = new PriorityQueue<>(
                (e1, e2) -> Long.compare(e1.getKey(), e2.getKey()));

        // Initialize the starting character with cost 0
        priorityQueue.add(new ImmutablePair<>(0L, startChar));

        // Array to store the minimum conversion cost to each character
        long[] minCosts = new long[26];
        // Initialize all costs to -1 (unreachable)
        Arrays.fill(minCosts, -1L);

        while (!priorityQueue.isEmpty()) {
            Pair<Long, Integer> currentPair = priorityQueue.poll();
            long currentCost = currentPair.getKey();
            int currentChar = currentPair.getValue();

            if (minCosts[currentChar] != -1L &&
                    minCosts[currentChar] < currentCost)
                continue;

            // Explore all possible conversions from the current character
            for (int[] conversion : adjacencyList[currentChar]) {
                int targetChar = conversion[0];
                long conversionCost = conversion[1];
                long newTotalCost = currentCost + conversionCost;

                // If we found a cheaper conversion, update its cost
                if (minCosts[targetChar] == -1L ||
                        newTotalCost < minCosts[targetChar]) {
                    minCosts[targetChar] = newTotalCost;
                    // Add the updated conversion to the queue for further exploration
                    priorityQueue.add(new ImmutablePair<>(newTotalCost, targetChar));
                }
            }
        }
        // Return the array of minimum conversion costs from the starting character to
        // all others
        return minCosts;
    }
}
