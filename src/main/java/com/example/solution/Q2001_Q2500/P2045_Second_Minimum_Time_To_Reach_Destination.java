package com.example.solution.Q2001_Q2500;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class P2045_Second_Minimum_Time_To_Reach_Destination {
    /**
     * Reference:
     * https://leetcode.com/problems/second-minimum-time-to-reach-destination/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Create an adjacency list where adj[X] contains all the neighbors of node
     * X.
     * 2. Initialize two distance arrays dist1 and dist2 storing the minimum and the
     * second minimum distance from node 1 for all the nodes. We would initialize
     * these arrays with large integer values.
     * 3. Initialize a frequency array freq to store the number of times when a node
     * is popped out of the queue. Since we need the second minimal distance, each
     * node can be poped out at most twice.
     * 4. Initialize a priority queue storing a {distance, node_id} pair, ordered by
     * the distance. Insert node 1 with distance 0 into the queue as {0, 1}.
     * 5. Perform the Dijkstra until the priority queue is empty.
     * a. Pop out the top pair of integers, and fetch the node (let's say it is Y)
     * and distance to reach node Y.
     * b. Increase freq[Y] by 1.
     * c. If Y == n and freq[n] == 2, it means we’ve encountered this node via the
     * second minimum distance. In this case, we return dist2[n].
     * d. Else, iterate over all the neighbors of Y.
     * e. For each neighbor, check if dist1[neighbor] could be updated using
     * distance[Y]. If not, check if dist2[neighbor] could be updated.
     * f. Push pair {distance_neighbor, neighbor} into the queue whenever
     * dist1[neighbor] or dist2[neighbor] is updated.
     * 6. If we do not return the answer after the queue is empty, we know that the
     * graph only has one node. Therefore, we just return 0.
     */

    public int secondMinimum(int n, int[][] edges, int time, int change) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            adj.computeIfAbsent(a, value -> new ArrayList<Integer>()).add(b);
            adj.computeIfAbsent(b, value -> new ArrayList<Integer>()).add(a);
        }
        int[] dist1 = new int[n + 1], dist2 = new int[n + 1], freq = new int[n + 1];
        // dist1[i] stores the minimum time taken to reach node i from node 1. dist2[i]
        // stores the second minimum time taken to reach node from node 1. freq[i]
        // stores
        // the number of times a node is popped out of the heap.
        for (int i = 1; i <= n; i++) {
            dist1[i] = dist2[i] = Integer.MAX_VALUE;
            freq[i] = 0;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        pq.offer(new int[] { 1, 0 });
        dist1[1] = 0;

        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            int node = temp[0];
            int time_taken = temp[1];

            freq[node]++;

            // If the node is being visited for the second time and is 'n', return the
            // answer.
            if (freq[node] == 2 && node == n)
                return time_taken;
            // If the current light is red, wait till the path turns green.
            if ((time_taken / change) % 2 == 1)
                time_taken = change * (time_taken / change + 1) + time;
            else
                time_taken = time_taken + time;

            if (!adj.containsKey(node))
                continue;
            for (int neighbor : adj.get(node)) {
                // Ignore nodes that have already popped out twice, we are not interested in
                // visiting them again.
                if (freq[neighbor] == 2)
                    continue;

                // Update dist1 if it's more than the current time_taken and store its value in
                // dist2 since that becomes the second minimum value now.
                if (dist1[neighbor] > time_taken) {
                    dist2[neighbor] = dist1[neighbor];
                    dist1[neighbor] = time_taken;
                    pq.offer(new int[] { neighbor, time_taken });
                } else if (dist2[neighbor] > time_taken && dist1[neighbor] != time_taken) {
                    dist2[neighbor] = time_taken;
                    pq.offer(new int[] { neighbor, time_taken });
                }
            }

        }
        return 0;
    }
}
