package com.example.solution.Q001_Q500;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P310_Minimum_Height_Trees {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-height-trees/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initially, we would build a graph with the adjacency list from the input.
     * 2. We then create a queue which would be used to hold the leaf nodes.
     * 3. At the beginning, we put all the current leaf nodes into the queue.
     * 4. We then run a loop until there is only two nodes left in the graph.
     * 5. At each iteration, we remove the current leaf nodes from the queue. While
     * removing the nodes, we also remove the edges that are linked to the nodes. As
     * a consequence, some of the non-leaf nodes would become leaf nodes. And these
     * are the nodes that would be trimmed out in the next iteration.
     * 6. The iteration terminates when there are no more than two nodes left in the
     * graph, which are the desired centroids nodes.
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // edge cases
        if (n < 2) {
            ArrayList<Integer> centroids = new ArrayList<>();
            for (int i = 0; i < n; i++)
                centroids.add(i);
            return centroids;
        }

        // Build the graph with the adjacency list
        ArrayList<Set<Integer>> neighbors = new ArrayList<>();
        for (int i = 0; i < n; i++)
            neighbors.add(new HashSet<Integer>());

        for (int[] edge : edges) {
            Integer start = edge[0], end = edge[1];
            neighbors.get(start).add(end);
            neighbors.get(end).add(start);
        }

        // Initialize the first layer of leaves
        ArrayList<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (neighbors.get(i).size() == 1)
                leaves.add(i);

        // Trim the leaves until reaching the centroids
        int remainingNodes = n;
        while (remainingNodes > 2) {
            remainingNodes -= leaves.size();
            ArrayList<Integer> newLeaves = new ArrayList<>();

            // remove the current leaves along with the edges
            for (Integer leaf : leaves) {
                // the only neighbor left for the leaf node
                Integer neighbor = neighbors.get(leaf).iterator().next();
                // remove the edge along with the leaf node
                neighbors.get(neighbor).remove(leaf);
                if (neighbors.get(neighbor).size() == 1)
                    newLeaves.add(neighbor);
            }

            // prepare for the next round
            leaves = newLeaves;
        }

        // The remaining nodes are the centroids of the graph
        return leaves;
    }
}
