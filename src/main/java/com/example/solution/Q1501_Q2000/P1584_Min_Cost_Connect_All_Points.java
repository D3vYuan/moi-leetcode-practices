package com.example.solution.Q1501_Q2000;

import java.util.ArrayList;
import java.util.Collections;

class UnionFind {
    public int[] group;
    public int[] rank;

    public UnionFind(int size) {
        group = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; ++i) {
            group[i] = i;
        }
    }

    public int find(int node) {
        if (group[node] != node) {
            group[node] = find(group[node]);
        }
        return group[node];
    }

    public boolean union(int node1, int node2) {
        int group1 = find(node1);
        int group2 = find(node2);

        // node1 and node2 already belong to same group.
        if (group1 == group2) {
            return false;
        }

        if (rank[group1] > rank[group2]) {
            group[group2] = group1;
        } else if (rank[group1] < rank[group2]) {
            group[group1] = group2;
        } else {
            group[group1] = group2;
            rank[group2] += 1;
        }

        return true;
    }
}

public class P1584_Min_Cost_Connect_All_Points {
    /**
     * Reference:
     * https://leetcode.com/problems/min-cost-to-connect-all-points/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Explanation:
     * 1. The union-find data structure has two primary functions:
     * a. find(a): Function which returns the ID of the group in which node a
     * belongs.
     * b. union(a,b): Function to merge the groups of node a and b. If they already
     * belong to the same group, we don't do anything and return false to signify
     * the edge between a and b was not added. Otherwise, we return true.
     * 
     * Strategy:
     * 1. Create a class UnionFind:
     * a. group, rank - Arrays to store the group (also known as root) and rank of
     * each node.
     * b. find(a) - Function to find the group of node aaa using path compression.
     * c. union(a,b) - Function to merge groups of nodes a and b by rank.
     * 2. Initialize some variables:
     * a. n - Number of nodes in the graph.
     * b. mstCost - Cost to build the MST.
     * c. edgesUsed - Number of edges included in the MST.
     * d. uf - UnionFind object of size nnn to connect nnn nodes.
     * e. allEdges - Array to store all the edges of our graph.
     * 3. Iterate over all coordinate points, and for each coordinate point, create
     * an edge to all other coordinate points. Store the edges in the allEdges
     * array. Each element of allEdges contains three values: edge weight, node1,
     * node2.
     * 4. Sort the elements in allEdge in increasing order of their edge weights.
     * 5. Iterate over each edge in allEdges until edgesUsed becomes equal to n−1.
     * For each edge:
     * a. Try joining both the nodes of the current edge.
     * b. If the nodes are already connected, we discard the current edge because
     * including this edge would create a cycle. Otherwise, we add the weight of the
     * current edge to the mstCost variable and increment edgesUsed by 1.
     * 6. Return the total cost of MST, mstCost.
     */

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        ArrayList<int[]> allEdges = new ArrayList<>();

        // Storing all edges of our complete graph.
        for (int currNext = 0; currNext < n; ++currNext) {
            for (int nextNext = currNext + 1; nextNext < n; ++nextNext) {
                int weight = Math.abs(points[currNext][0] - points[nextNext][0]) +
                        Math.abs(points[currNext][1] - points[nextNext][1]);

                int[] currEdge = { weight, currNext, nextNext };
                allEdges.add(currEdge);
            }
        }

        // Sort all edges in increasing order.
        Collections.sort(allEdges, (a, b) -> Integer.compare(a[0], b[0]));

        UnionFind uf = new UnionFind(n);
        int mstCost = 0;
        int edgesUsed = 0;

        for (int i = 0; i < allEdges.size() && edgesUsed < n - 1; ++i) {
            int node1 = allEdges.get(i)[1];
            int node2 = allEdges.get(i)[2];
            int weight = allEdges.get(i)[0];

            if (uf.union(node1, node2)) {
                mstCost += weight;
                edgesUsed++;
            }
        }

        return mstCost;
    }
}
