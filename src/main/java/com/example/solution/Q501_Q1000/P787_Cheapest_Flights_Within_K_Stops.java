package com.example.solution.Q501_Q1000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class P787_Cheapest_Flights_Within_K_Stops {
    /**
     * Reference:
     * 
     * Strategy:
     * 1. Create an adjacency list where adj[X] contains all the neighbors of node X
     * and the corresponding price it takes to move to a neighbor.
     * 2. Intialize dist array, storing the minimum price to reach a node from the
     * src node. Intialize it with large values.
     * 3. Initialize a queue storing {node, distance} pairs. Initially, the queue
     * should have only {src, 0}.
     * 4. Create a variable called stops and set its value to 0.
     * 5. Perform BFS until the queue is empty or stops > k:
     * a. Iterate over all the nodes at a particular level. This will be done by
     * starting a nested loop and visiting all the nodes currently present in the
     * queue.
     * b. At each pair {node, distance}, iterate over all the neighbors of node. For
     * each neighbour, check if dist[neighbor] is less than distance + the price of
     * the edge. If it is, then update dist[neighbor] and push {neighbor,
     * dist[neighbor]} onto the queue.
     * c. After iterating over all the nodes in the current level, increase stops by
     * one. We've visited all the nodes at a particular level and are ready to visit
     * the next level of nodes.
     * 6. Once we reach a condition where either the queue is empty or stops == k,
     * we have our answer as dist[dst]. If dist[dst] hasn't changed from the initial
     * large value, then we never reached it, so return -1.
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] i : flights)
            adj.computeIfAbsent(i[0], value -> new ArrayList<>()).add(new int[] { i[1], i[2] });

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { src, 0 });
        int stops = 0;

        while (stops <= k && !q.isEmpty()) {
            int sz = q.size();
            // Iterate on current level.
            while (sz-- > 0) {
                int[] temp = q.poll();
                int node = temp[0];
                int distance = temp[1];

                if (!adj.containsKey(node))
                    continue;
                // Loop over neighbors of popped node.
                for (int[] e : adj.get(node)) {
                    int neighbour = e[0];
                    int price = e[1];
                    if (price + distance >= dist[neighbour])
                        continue;
                    dist[neighbour] = price + distance;
                    q.offer(new int[] { neighbour, dist[neighbour] });
                }
            }
            stops++;
        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}
