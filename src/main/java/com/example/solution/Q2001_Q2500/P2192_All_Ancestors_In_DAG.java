package com.example.solution.Q2001_Q2500;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class P2192_All_Ancestors_In_DAG {
    /**
     * Reference: https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a list of lists adjacencyList to store the edges of the graph.
     * 2. Initialize an array indegree to store the in-degree of each node.
     * 3. Fill adjacencyList and the indegree array based on the given edges.
     * 4. Initialize a queue nodesWithZeroIndegree and add all such nodes to the queue.
     * 5. Initialize a list topologicalOrder to store the topological order of nodes and process nodes in the queue. For each node:
     * a. Reduce the in-degree of its neighbors.
     * b. Add neighbors with zero in-degree to the queue.
     * 6. Initialize a list ancestorsList to store the result and a list of sets ancestorsSetList to store the ancestors of each node.
     * 7. For each node in the topological order:
     * a. Loop over all neighbors neighbor of node. For each neighbor:
     *      Add node as the immediate parent of neighbor to the set ancestorsSetList[neighbor].
     *      Add all other ancestors of node to the set ancestorsSetList[neighbor].
     * 8. Add the contents of each set to it's corresponding list in ancestorsList in ascending order.
     * 9. Return ancestorsList, which contains the ancestors of each node in the graph.
     */

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        // Create adjacency list
        List<Integer>[] adjacencyList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        // Fill the adjacency list and indegree array based on the edges
        int[] indegree = new int[n];
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            adjacencyList[from].add(to);
            indegree[to]++;
        }

        // Queue for nodes with no incoming edges (starting points for topological sort)
        Queue<Integer> nodesWithZeroIndegree = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                nodesWithZeroIndegree.add(i);
            }
        }

        // List to store the topological order of nodes
        List<Integer> topologicalOrder = new ArrayList<>();
        while (!nodesWithZeroIndegree.isEmpty()) {
            int currentNode = nodesWithZeroIndegree.poll();
            topologicalOrder.add(currentNode);

            // Reduce indegree of neighboring nodes and add them to the queue
            // if they have no more incoming edges
            for (int neighbor : adjacencyList[currentNode]) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    nodesWithZeroIndegree.add(neighbor);
                }
            }
        }

        // Initialize the result list and set list for storing ancestors
        List<List<Integer>> ancestorsList = new ArrayList<>();
        List<Set<Integer>> ancestorsSetList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ancestorsList.add(new ArrayList<>());
            ancestorsSetList.add(new HashSet<>());
        }

        // Fill the set list with ancestors using the topological order
        for (int node : topologicalOrder) {
            for (int neighbor : adjacencyList[node]) {
                // Add immediate parent, and other ancestors.
                ancestorsSetList.get(neighbor).add(node);
                ancestorsSetList
                    .get(neighbor)
                    .addAll(ancestorsSetList.get(node));
            }
        }

        // Convert sets to lists
        for (int i = 0; i < n; i++) {
            for (int node = 0; node < n; node++) {
                if (node == i) continue;
                if (ancestorsSetList.get(i).contains(node)) {
                    ancestorsList.get(i).add(node);
                }
            }
        }

        return ancestorsList;
    }
}
