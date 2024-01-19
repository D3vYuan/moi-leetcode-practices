package com.example.solution.Q2001_Q2500;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class P2050_Parallel_Course_3 {
    /**
     * Reference:
     * https://leetcode.com/problems/parallel-courses-iii/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. Initialize the following data structures:
     * a. A graph from relations. For convenience, we will change the nodes to be
     * 0-indexed.
     * b. An array indegree of length n, representing the indegree of each node.
     * c. A queue to perform Kahn's algorithm.
     * d. An array maxTime of length n, representing the maximum value of all paths
     * ending at certain nodes.
     * 2. For all nodes with indegree[node] = 0, push them to the queue and
     * initialize maxTime[node] = time[node].
     * 3. While queue is not empty:
     * a. Pop a node.
     * b. Iterate over graph[node]. For each neighbor:
     * b1. Update maxTime[neighbor] with maxTime[node] + time[neighbor] if it is
     * larger.
     * b2. Decrement indegree[neighbor].
     * b3. If indegree[neighbor] == 0, push neighbor to queue.
     * 4. Return max(maxTime).
     */
    public int minimumTime(int n, int[][] relations, int[] time) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        int[] indegree = new int[n];
        for (int[] edge : relations) {
            int x = edge[0] - 1;
            int y = edge[1] - 1;
            graph.get(x).add(y);
            indegree[y]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] maxTime = new int[n];

        for (int node = 0; node < n; node++) {
            if (indegree[node] == 0) {
                queue.add(node);
                maxTime[node] = time[node];
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.remove();
            for (int neighbor : graph.get(node)) {
                maxTime[neighbor] = Math.max(maxTime[neighbor], maxTime[node] + time[neighbor]);
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        int ans = 0;
        for (int node = 0; node < n; node++) {
            ans = Math.max(ans, maxTime[node]);
        }
        return ans;
    }
}
