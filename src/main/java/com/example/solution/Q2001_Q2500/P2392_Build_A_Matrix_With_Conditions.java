package com.example.solution.Q2001_Q2500;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P2392_Build_A_Matrix_With_Conditions {
    /**
     * Reference:
     * https://leetcode.com/problems/build-a-matrix-with-conditions/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Main function - buildMatrix(k, rowConditions, colConditions)
     * a. Create two arrays given by orderRows and orderColumns to store the
     * topological sorted sequence.
     * b. Store the values of topoSort(rowConditions,k) and
     * topoSort(colConditions,k) in them.
     * c. If either of the arrays is empty, return {}.
     * d. Create a matrix of size k x k and initialize all values with 0.
     * e. Iterate i through all values from 0 to k:
     * Iterate j through all values from 0 to k:
     * If orderRows[i] = orderColumns[j], store orderRows[i] in matrix[i][j].
     * f. Return the matrix.
     * 
     * 2. topoSort(edges,n)
     * a. Initialize an adjacency matrix adj with n+1 rows, an array deg with size
     * n+1, and an empty array order. Also, initialize a visited array and a boolean
     * hasCycle variable with 0.
     * b. Store all the edges in adj by pushing b in adj[a] denoting an edge from a
     * to b.
     * c. For all nodes with an index from 1 to n:
     * c1. If the current node is not visited, perform dfs(i, adj, visited, order,
     * hasCycle). If the hasCycle value is true, return an empty array.
     * d. Reverse the order array.
     * e. Return order.
     * 
     * 3. dfs(node, adj, visited, order, hasCycle)
     * a. Set visited[node] to 1.
     * b. Iterate over all neighbors of node:
     * b1. If visited[neighbor] = 0, perform dfs(neighbor, adj, visited, order,
     * hasCycle). If hasCycle is true, return.
     * b2. If visited[neighbor] = 1, set hasCycle to true, return.
     * c. Set visited[node] as 2.
     * d. Push node in order array.
     */
    public int[][] buildMatrix(
            int k,
            int[][] rowConditions,
            int[][] colConditions) {
        // Store the topologically sorted sequences.
        List<Integer> orderRows = topoSort(rowConditions, k);
        List<Integer> orderColumns = topoSort(colConditions, k);

        // If no topological sort exists, return empty array.
        if (orderRows.isEmpty() || orderColumns.isEmpty())
            return new int[0][0];

        int[][] matrix = new int[k][k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (orderRows.get(i).equals(orderColumns.get(j))) {
                    matrix[i][j] = orderRows.get(i);
                }
            }
        }
        return matrix;
    }

    private List<Integer> topoSort(int[][] edges, int n) {
        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }

        List<Integer> order = new ArrayList<>();
        // 0: not visited, 1: visiting, 2: visited
        int[] visited = new int[n + 1];
        boolean[] hasCycle = { false };

        // Perform DFS for each node
        for (int i = 1; i <= n; i++) {
            if (visited[i] == 0) {
                dfs(i, adj, visited, order, hasCycle);
                // Return empty if cycle detected
                if (hasCycle[0])
                    return new ArrayList<>();
            }
        }

        // Reverse to get the correct order
        Collections.reverse(order);
        return order;
    }

    private void dfs(
            int node,
            List<List<Integer>> adj,
            int[] visited,
            List<Integer> order,
            boolean[] hasCycle) {
        visited[node] = 1; // Mark node as visiting
        for (int neighbor : adj.get(node)) {
            if (visited[neighbor] == 0) {
                dfs(neighbor, adj, visited, order, hasCycle);
                // Early exit if a cycle is detected
                if (hasCycle[0])
                    return;
            } else if (visited[neighbor] == 1) {
                // Cycle detected
                hasCycle[0] = true;
                return;
            }
        }
        // Mark node as visited
        visited[node] = 2;
        // Add node to the order
        order.add(node);
    }
}
