package com.example.solution.Q1501_Q2000;

import java.util.HashMap;
import java.util.Map;

public class P1791_Find_Center_Of_Star_Graph {
    /**
     * Reference:
     * https://leetcode.com/problems/find-center-of-star-graph/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize an empty unordered hashmap degree to store the degree of all
     * nodes.
     * 2. Iterate over the edges in the list edges and, for each edge, increment the
     * degree of nodes this edge connects in the map degree.
     * 3. Iterate over the hash map degree and check if the degree is equal to N−1,
     * i.e., edges.size().
     * 4. Return the node that satisfies the above condition.
     * 5. Return -1, although this is an unreachable part of the code as the input
     * is always valid.
     */

    public int findCenter(int[][] edges) {
        Map<Integer, Integer> degree = new HashMap<>();

        for (int[] edge : edges) {
            degree.put(edge[0], degree.getOrDefault(edge[0], 0) + 1);
            degree.put(edge[1], degree.getOrDefault(edge[1], 0) + 1);
        }

        for (int node : degree.keySet()) {
            if (degree.get(node) == edges.length) {
                return node;
            }
        }

        return -1;
    }
}
