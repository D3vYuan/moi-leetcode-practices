package com.example.solution.Q1501_Q2000;

public class P1579_Remove_Max_Number_Of_Edges {
    /**
     * Reference:
     * https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Create a class UnionFind, this will have the relevant data members and
     * member functions to perform DSU operations:
     * a. Data member representative stores the immediate parent of nodes in the
     * union find structure, initially each node will be it's own representative.
     * b. Data member componentSize stores the number of nodes in the components
     * with the node as the root node, initially the size of component for each node
     * is 1
     * c. Data member components stores the number of components in the graph,
     * initially it will be equal to N as each node is considered a separate
     * component.
     * d. Method findRepresentative() returns the root node in the representative
     * hierarchy.
     * e. Method performUnion(), returns 1 after performing the union between the
     * components of the two nodes that were not connected before, otherwise returns
     * 0.
     * f. Method isConnected() returns true if the graph is connected, i.e.
     * components = 1.
     * 2. Create a UnionFind object for both Alice and Bob with N nodes.
     * 3. Initialize edgesRequired to 0.
     * 4. Perform union of all edges of Type 3 for both Alice and Bob, and for each
     * edge increment the value of edgesRequired if for any of them performUnion()
     * returns 1.
     * 5. Perform union of all edges of Type 1 & 2 for Alice or Bob respectively and
     * increment the edgesRequired if for any of them performUnion() returns 1.
     * 6. If isConnected() returns true for both Alice and Bob, then return Total
     * Edges - edgesRequired, otherwise return -1.
     */

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        // Different objects for Alice and Bob.
        UnionFind Alice = new UnionFind(n);
        UnionFind Bob = new UnionFind(n);

        int edgesRequired = 0;
        // Perform union for edges of type = 3, for both Alice and Bob.
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                edgesRequired += (Alice.performUnion(edge[1], edge[2]) | Bob.performUnion(edge[1], edge[2]));
            }
        }

        // Perform union for Alice if type = 1 and for Bob if type = 2.
        for (int[] edge : edges) {
            if (edge[0] == 1) {
                edgesRequired += Alice.performUnion(edge[1], edge[2]);
            } else if (edge[0] == 2) {
                edgesRequired += Bob.performUnion(edge[1], edge[2]);
            }
        }

        // Check if the Graphs for Alice and Bob have n - 1 edges or is a single
        // component.
        if (Alice.isConnected() && Bob.isConnected()) {
            return edges.length - edgesRequired;
        }

        return -1;
    }

    private class UnionFind {
        int[] representative;
        int[] componentSize;
        // Number of distinct components in the graph.
        int components;

        // Initialize the list representative and componentSize
        // Each node is representative of itself with size 1.
        public UnionFind(int n) {
            components = n;
            representative = new int[n + 1];
            componentSize = new int[n + 1];

            for (int i = 0; i <= n; i++) {
                representative[i] = i;
                componentSize[i] = 1;
            }
        }

        // Get the root of a node.
        int findRepresentative(int x) {
            if (representative[x] == x) {
                return x;
            }

            // Path compression.
            return representative[x] = findRepresentative(representative[x]);
        }

        // Perform the union of two components that belongs to node x and node y.
        int performUnion(int x, int y) {
            x = findRepresentative(x);
            y = findRepresentative(y);

            if (x == y) {
                return 0;
            }

            if (componentSize[x] > componentSize[y]) {
                componentSize[x] += componentSize[y];
                representative[y] = x;
            } else {
                componentSize[y] += componentSize[x];
                representative[x] = y;
            }

            components--;
            return 1;
        }

        // Returns true if all nodes get merged to one.
        boolean isConnected() {
            return components == 1;
        }
    }
}
