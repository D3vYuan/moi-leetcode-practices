package com.example.solution.Q1001_Q1500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class P1334_Find_City_With_Smallest_Number_Of_Neighbors_Threshold_Distance {
    /**
     * Reference: https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Create an adjacency list adjacencyList to store the graph.
     * 2. Create a 2D array shortestPathMatrix with dimensions n x n to store shortest path distances between all pairs of cities.
     * 3. For each city i:
     * a. Set all distances in shortestPathMatrix[i] to the maximum integer value.
     * b. Set the distance from the city i to itself (shortestPathMatrix[i][i]) to 0.
     * c. Initialize adjacencyList[i] as an empty list.
     * 4. Iterate through each edge in edges:
     * a. Extract start, end, and weight from each edge.
     * b. Add (end, weight) to adjacencyList[start].
     * c. Add (start, weight) to adjacencyList[end].
     * 5. For each city i:
     * a. Call dijkstra(n, adjacencyList, shortestPathMatrix[i], i), where i is the source city and shortestPathMatrix[i] is the array that will hold the shortest path distances from city i.
     * 6. Return the city identified by calling getCityWithFewestReachable(n, shortestPathMatrix, distanceThreshold) as having the fewest number of reachable cities within the given distance threshold.
     * 
     * 7. dijkstra(n, adjacencyList, shortestPathDistances, source) Function:
     * a. Use a priority queue to process nodes with the smallest distance first:
     *      Initialize the priority queue with the source city.
     *      Set all distances in shortestPathDistances to Integer.MAX_VALUE.
     *      Set the distance to the source city itself (shortestPathDistances[source]) to 0.
     * b. Process nodes in priority order:
     *      For each node, update distances to neighboring cities if a shorter path is found.
     * 
     * 8. getCityWithFewestReachable(n, shortestPathMatrix, distanceThreshold) Function:
     * a. Initialize cityWithFewestReachable to -1 and fewestReachableCount to n.
     * b. For each city i:
     *      Count how many cities are reachable from the city i within the distanceThreshold:
     *          For each city j, check if shortestPathMatrix[i][j] is less than or equal to distanceThreshold.
     *          Increment reachableCount if city j is reachable within the threshold.
     * c. Update cityWithFewestReachable if the current city i has fewer reachable cities compared to previously evaluated cities.
     */

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // Adjacency list to store the graph
        List<int[]>[] adjacencyList = new List[n];
        // Matrix to store shortest path distances from each city
        int[][] shortestPathMatrix = new int[n][n];

        // Initialize adjacency list and shortest path matrix
        for (int i = 0; i < n; i++) {
            Arrays.fill(shortestPathMatrix[i], Integer.MAX_VALUE); // Set all distances to infinity
            shortestPathMatrix[i][i] = 0; // Distance to itself is zero
            adjacencyList[i] = new ArrayList<>();
        }

        // Populate the adjacency list with edges
        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            int weight = edge[2];
            adjacencyList[start].add(new int[] { end, weight });
            adjacencyList[end].add(new int[] { start, weight }); // For undirected graph
        }

        // Compute shortest paths from each city using Dijkstra's algorithm
        for (int i = 0; i < n; i++) {
            dijkstra(n, adjacencyList, shortestPathMatrix[i], i);
        }

        // Find the city with the fewest number of reachable cities within the distance threshold
        return getCityWithFewestReachable(
            n,
            shortestPathMatrix,
            distanceThreshold
        );
    }

    // Dijkstra's algorithm to find shortest paths from a source city
    void dijkstra(
        int n,
        List<int[]>[] adjacencyList,
        int[] shortestPathDistances,
        int source
    ) {
        // Priority queue to process nodes with the smallest distance first
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) ->
            (a[1] - b[1])
        );
        priorityQueue.add(new int[] { source, 0 });
        Arrays.fill(shortestPathDistances, Integer.MAX_VALUE); // Set all distances to infinity
        shortestPathDistances[source] = 0; // Distance to source itself is zero

        // Process nodes in priority order
        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.remove();
            int currentCity = current[0];
            int currentDistance = current[1];
            if (currentDistance > shortestPathDistances[currentCity]) {
                continue;
            }

            // Update distances to neighboring cities
            for (int[] neighbor : adjacencyList[currentCity]) {
                int neighborCity = neighbor[0];
                int edgeWeight = neighbor[1];
                if (
                    shortestPathDistances[neighborCity] >
                    currentDistance + edgeWeight
                ) {
                    shortestPathDistances[neighborCity] = currentDistance +
                    edgeWeight;
                    priorityQueue.add(
                        new int[] {
                            neighborCity,
                            shortestPathDistances[neighborCity],
                        }
                    );
                }
            }
        }
    }

    // Determine the city with the fewest number of reachable cities within the distance threshold
    int getCityWithFewestReachable(
        int n,
        int[][] shortestPathMatrix,
        int distanceThreshold
    ) {
        int cityWithFewestReachable = -1;
        int fewestReachableCount = n;

        // Count number of cities reachable within the distance threshold for each city
        for (int i = 0; i < n; i++) {
            int reachableCount = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                } // Skip self
                if (shortestPathMatrix[i][j] <= distanceThreshold) {
                    reachableCount++;
                }
            }
            // Update the city with the fewest reachable cities
            if (reachableCount <= fewestReachableCount) {
                fewestReachableCount = reachableCount;
                cityWithFewestReachable = i;
            }
        }
        return cityWithFewestReachable;
    }
}
