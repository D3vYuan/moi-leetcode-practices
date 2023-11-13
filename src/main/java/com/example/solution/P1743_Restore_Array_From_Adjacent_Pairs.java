package com.example.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P1743_Restore_Array_From_Adjacent_Pairs {
    /**
     * In this problem, we are given information about numbers that are adjacent to each other in some array nums. We can think of these pairs in adjacentPairs as edges in a graph: if we have a pair (x, y), we can imagine that there is an undirected edge between node x and y.
     * This graph would form a doubly-linked list, since the edges, by definition, only describe adjacent elements. In fact, this doubly-linked list would represent nums, since the adjacent elements are adjacent elements in nums!
     *
     * This simplifies our problem: to recover nums, we simply need to perform a traversal over the graph, starting from one end of the "linked list". This is because as stated above, the graph/linked list represents nums. Thus, if we start at either end, we will continuously visit adjacent numbers one by one until we reach the other end, which is equivalent to iterating over the elements of nums in order.
     * This brings us to the question: how do we find either end of the graph/linked list, so that we know where to start the traversal from? From the above image, there are two ends: 1 and 4. You may notice that these nodes only have one edge, whereas other nodes have exactly two edges. This is because every node has a node to its left and to its right, except for the nodes at the ends.
     * Thus, we can identify a root as a node that only has one edge. Once we have a root, we will perform a DFS from it.
     * It is typical in a DFS over a graph to use a data structure (usually a hash map) seen that keeps track of nodes we have already visited. In this problem, since the graph essentially forms a doubly linked list, we don't need to use any data structure. We simply need to keep track of the previous node we visited prev. Each node can have at most two edges: the node we came from, and a node we haven't visited yet. By keeping track of prev and not traversing to it, we ensure that we walk in a straight line and never visit a node twice.
     * At each node during the traversal, we add node to an answer list ans. Once the traversal is finished, ans will be a valid nums.
     * 
     * Algorithm
     * [1] Initialize a graph, where graph[node] holds a list of neighbors for node.
     * [2] Iterate over each edge (x, y) in adjacentPairs
     *  Add y to graph[x].
     *  Add x to graph[y].
     * [3] Iterate over each num in graph:
     *  If the length of graph[num] is equal to 1, set root = num and break from the loop.
     * [4] Define a function dfs(node, prev, ans):
     *  Add node to ans.
     *  Iterate over each neighbor in graph[node]:
     *      If neighbor != prev:
     *          Call dfs(neighbor, node, ans).
     * [5] Initialize the answer list ans.
     * [6] Call dfs(root, k, ans), where k can be any value that is guaranteed to not appear in the graph, such as infinity.
     * [7] Return ans.
     */
    Map<Integer, List<Integer>> graph = new HashMap();

    public int[] restoreArray(int[][] adjacentPairs) {
        for (int[] edge : adjacentPairs) {
            int x = edge[0];
            int y = edge[1];

            if (!graph.containsKey(x)) {
                graph.put(x, new ArrayList());
            }

            if (!graph.containsKey(y)) {
                graph.put(y, new ArrayList());
            }

            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        int root = 0;
        for (int num : graph.keySet()) {
            if (graph.get(num).size() == 1) {
                root = num;
                break;
            }
        }

        int[] ans = new int[graph.size()];
        dfs(root, Integer.MAX_VALUE, ans, 0);
        return ans;
    }

    private void dfs(int node, int prev, int[] ans, int i) {
        ans[i] = node;
        for (int neighbor : graph.get(node)) {
            if (neighbor != prev) {
                dfs(neighbor, node, ans, i + 1);
            }
        }
    }
}
