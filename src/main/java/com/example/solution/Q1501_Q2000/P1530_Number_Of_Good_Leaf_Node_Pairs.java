package com.example.solution.Q1501_Q2000;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.example.utility.TreeNode;

public class P1530_Number_Of_Good_Leaf_Node_Pairs {
    /**
     * Reference:
     * https://leetcode.com/problems/number-of-good-leaf-nodes-pairs/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize an adjacency list to convert the tree into a graph.
     * 2. Initialize a set to store the leaf nodes of the tree.
     * 3. Use a helper method traverseTree to traverse the tree to build the graph
     * and find the leaf nodes. Maintain the current node as well as the parent node
     * in the parameters.
     * a. If the current node is a leaf node, add it to the set initialize in step
     * 2.
     * b. In the adjacency list, add the current node to the parent node's list of
     * neighbors. Also, add the parent node to the current node's list of neighbors.
     * c. Recursively call traverseTree for the current node's left child and right
     * child.
     * 4. Initialize an ans variable to count the number of good leaf node pairs.
     * 5. Iterate through each leaf node in the set:
     * a. Run BFS for the current leaf node. BFS can be terminated early once all
     * nodes that are a distance away from the current leaf node are discovered.
     * Increment ans for every leaf node encountered in each BFS run.
     * 6. Return ans / 2. We count each pair twice so we need to divide by 2 to get
     * the actual count.
     */

    public int countPairs(TreeNode root, int distance) {
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        Set<TreeNode> leafNodes = new HashSet<>();

        traverseTree(root, null, graph, leafNodes);

        int ans = 0;

        for (TreeNode leaf : leafNodes) {
            Queue<TreeNode> bfsQueue = new LinkedList<>();
            Set<TreeNode> seen = new HashSet<>();
            bfsQueue.add(leaf);
            seen.add(leaf);
            // Go through all nodes that are within the given distance of
            // the current leaf node
            for (int i = 0; i <= distance; i++) {
                // Clear all nodes in the queue (distance i away from leaf node)
                // Add the nodes' neighbors (distance i+1 away from leaf node)
                int size = bfsQueue.size();
                for (int j = 0; j < size; j++) {
                    // If current node is a new leaf node, add the found pair to our count
                    TreeNode currNode = bfsQueue.remove();
                    if (leafNodes.contains(currNode) && currNode != leaf) {
                        ans++;
                    }
                    if (graph.containsKey(currNode)) {
                        for (TreeNode neighbor : graph.get(currNode)) {
                            if (!seen.contains(neighbor)) {
                                bfsQueue.add(neighbor);
                                seen.add(neighbor);
                            }
                        }
                    }
                }
            }
        }
        return ans / 2;
    }

    private void traverseTree(
            TreeNode currNode,
            TreeNode prevNode,
            Map<TreeNode, List<TreeNode>> graph,
            Set<TreeNode> leafNodes) {
        if (currNode == null) {
            return;
        }
        if (currNode.left == null && currNode.right == null) {
            leafNodes.add(currNode);
        }
        if (prevNode != null) {
            graph.computeIfAbsent(prevNode, k -> new ArrayList<TreeNode>());
            graph.get(prevNode).add(currNode);
            graph.computeIfAbsent(currNode, k -> new ArrayList<TreeNode>());
            graph.get(currNode).add(prevNode);
        }
        traverseTree(currNode.left, currNode, graph, leafNodes);
        traverseTree(currNode.right, currNode, graph, leafNodes);
    }
}
