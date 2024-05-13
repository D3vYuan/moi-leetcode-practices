package com.example.solution.Q1501_Q2000;

public class P1971_Find_Path_Exists_In_Graph {
    /**
     * Reference:
     * https://leetcode.com/problems/find-if-path-exists-in-graph/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize the DSU data structure UnionFind containing all nodes. Each
     * node i has:
     * a. a distinct root, which means each point is individual.
     * b. a group size of 1, which means each set only contains one node.
     * 
     * the DSU structure also supports:
     * a. find(x): find the root of the node x.
     * b. union(x, y): if two given nodes x and y are not in the same group, we
     * modify one of the roots as the other root, which means that the two groups
     * containing x and y are merged into one group. Note that we use the
     * union-by-rank method to optimize the time complexity, basically, we modify
     * the root of the smaller group as the root of the larger group.
     * 
     * 2. Iterate over all edges. For each edge edge = [node_a, node_b], use the DSU
     * data structure we initialized to connect node_a and node_b.
     * 3. Check if node source and node destination are in the same group.
     */
    class UnionFind {
        private int[] root;
        private int[] rank;

        public UnionFind(int n) {
            this.root = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; ++i) {
                this.root[i] = i;
                this.rank[i] = 1;
            }
        }

        public int find(int x) {
            if (this.root[x] != x) {
                this.root[x] = find(this.root[x]);
            }
            return this.root[x];
        }

        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX != rootY) {
                if (this.rank[rootX] > this.rank[rootY]) {
                    int tmp = rootX;
                    rootX = rootY;
                    rootY = tmp;
                }
                // Modify the root of the smaller group as the root of the
                // larger group, also increment the size of the larger group.
                this.root[rootX] = rootY;
                this.rank[rootY] += this.rank[rootX];
            }
        }
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        UnionFind uf = new UnionFind(n);

        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }

        return uf.find(source) == uf.find(destination);
    }
}
