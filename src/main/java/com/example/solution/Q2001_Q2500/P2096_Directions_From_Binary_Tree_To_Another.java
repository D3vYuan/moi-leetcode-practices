package com.example.solution.Q2001_Q2500;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.example.utility.TreeNode;

public class P2096_Directions_From_Binary_Tree_To_Another {
    /**
     * Reference:
     * https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Main method getDirections:
     * a. Initialize a map parentMap to store parent nodes for each node in the
     * tree.
     * b. Find the startNode using the findStartNode method, which recursively
     * searches the tree for the node with startValue.
     * c. Populate parentMap using the populateParentMap method, which traverses the
     * tree and maps each child node to its parent.
     * d. For the BFS, initialize
     * A queue containing the startNode.
     * A set visitedNodes to keep track of visited nodes to avoid cycles.
     * A map pathTracker to record the path taken by the BFS.
     * e. While the queue is not empty:
     * Dequeue a TreeNode from the queue.
     * If the current node's value matches destValue, we have found our path. Call
     * backtrackPath and return the path calculated by it.
     * f. If parentMap contains a parent for the current node and it hasn't been
     * visited, enqueue the parent node and add an entry to pathTracker with the
     * current node as the key and a pair containing the parent node and direction
     * 'U' as the value.
     * g. If the left child exists and hasn't been visited, enqueue the left child
     * and add an entry to pathTracker with the current node as the key and a pair
     * containing the left child and direction 'L' as the value.
     * h. If the right child exists and hasn't been visited, enqueue the right child
     * and add an entry to pathTracker with the current node as the key and a pair
     * containing the right child and direction 'R' as the value.
     * i. If the destination node is never reached, an empty string is returned.
     * 
     * 2. Helper method backtrackPath:
     * a. Define backtrackPath with parameters: destination node (TreeNode) and
     * pathTracker map.
     * b. Initialize an empty string path.
     * c. While node exists in pathTracker:
     * Retrieve the parent node and direction from pathTracker.
     * Append the direction to path.
     * Set node to the parent node.
     * d. Reverse and return path.
     * 
     * 3. Helper method populateParentMap:
     * a. Define populateParentMap with parameters: current node (TreeNode) and
     * parentMap.
     * b. If node is null, return.
     * c. If left or right children exist, add them to parentMap with node as their
     * parent.
     * d. Recurse on left and right children.
     * 
     * 4. Helper method findStartNode:
     * a. Define findStartNode with parameters: current node (TreeNode) and
     * startValue.
     * b. If node is null, return.
     * c. If node's value matches startValue, return node.
     * d. Recursively search the left subtree. If a node is found, return it.
     * e. Otherwise, search the right subtree and return the result.
     */

    public String getDirections(TreeNode root, int startValue, int destValue) {
        // Map to store parent nodes
        Map<Integer, TreeNode> parentMap = new HashMap<>();

        // Find the start node and populate parent map
        TreeNode startNode = findStartNode(root, startValue);
        populateParentMap(root, parentMap);

        // Perform BFS to find the path
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(startNode);
        Set<TreeNode> visitedNodes = new HashSet<>();
        // Key: next node, Value: <current node, direction>
        Map<TreeNode, Pair<TreeNode, String>> pathTracker = new HashMap<>();
        visitedNodes.add(startNode);

        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();

            // If destination is reached, return the path
            if (currentNode.val == destValue) {
                return backtrackPath(currentNode, pathTracker);
            }

            // Check and add parent node
            if (parentMap.containsKey(currentNode.val)) {
                TreeNode parentNode = parentMap.get(currentNode.val);
                if (!visitedNodes.contains(parentNode)) {
                    queue.add(parentNode);
                    pathTracker.put(parentNode, new ImmutablePair<>(currentNode, "U"));
                    visitedNodes.add(parentNode);
                }
            }

            // Check and add left child
            if (currentNode.left != null &&
                    !visitedNodes.contains(currentNode.left)) {
                queue.add(currentNode.left);
                pathTracker.put(currentNode.left, new ImmutablePair<>(currentNode, "L"));
                visitedNodes.add(currentNode.left);
            }

            // Check and add right child
            if (currentNode.right != null &&
                    !visitedNodes.contains(currentNode.right)) {
                queue.add(currentNode.right);
                pathTracker.put(currentNode.right, new ImmutablePair<>(currentNode, "R"));
                visitedNodes.add(currentNode.right);
            }
        }

        // This line should never be reached if the tree is valid
        return "";
    }

    private String backtrackPath(
            TreeNode node,
            Map<TreeNode, Pair<TreeNode, String>> pathTracker) {
        StringBuilder path = new StringBuilder();

        // Construct the path
        while (pathTracker.containsKey(node)) {
            // Add the directions in reverse order and move on to the previous node
            path.append(pathTracker.get(node).getValue());
            node = pathTracker.get(node).getKey();
        }

        // Reverse the path
        path.reverse();

        return path.toString();
    }

    private void populateParentMap(
            TreeNode node,
            Map<Integer, TreeNode> parentMap) {
        if (node == null)
            return;

        // Add children to the map and recurse further
        if (node.left != null) {
            parentMap.put(node.left.val, node);
            populateParentMap(node.left, parentMap);
        }

        if (node.right != null) {
            parentMap.put(node.right.val, node);
            populateParentMap(node.right, parentMap);
        }
    }

    private TreeNode findStartNode(TreeNode node, int startValue) {
        if (node == null)
            return null;

        if (node.val == startValue)
            return node;

        TreeNode leftResult = findStartNode(node.left, startValue);

        // If left subtree returns a node, it must be StartNode. Return it
        // Otherwise, return whatever is returned by right subtree.
        if (leftResult != null)
            return leftResult;
        return findStartNode(node.right, startValue);
    }
}
