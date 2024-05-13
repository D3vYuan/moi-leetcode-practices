package com.example.solution.Q501_Q1000;

import com.example.utility.TreeNode;

public class P513_Find_Bottom_Left_Tree_Value {
    /**
     * Reference:
     * https://leetcode.com/problems/find-bottom-left-tree-value/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Explanation:
     * 1. Standard Recursive Preorder Traversal
     * a. If the tree is empty, return.
     * b. Handle the root.
     * c. Traverse the left subtree - call Preorder(root.left).
     * d. Traverse the right subtree - call Preorder(root.right).
     * 
     * Strategy;
     * 1. Initialize a variable maxDepth to store the depth of the bottom level of
     * the tree.
     * 2. Initialize a variable bottomLeftValue to store the leftmost value in the
     * last row of the tree.
     * 3. Implement a recursive function, dfs, that traverses the tree and finds the
     * leftmost value in the last row of the tree. The parameters are current, the
     * current node, and depth, its depth.
     * a. Check whether current is empty. If so, return.
     * b. Check if the current depth exceeds the global variable maxDepth. If it
     * does, that means we have found a new level.
     * b1. Set maxDepth to depth.
     * b2. Set bottomLeftValue to the value of the current node.
     * c. Recursively call dfs on the current node's left subtree and increment
     * depth by one.
     * d. Recursively call dfs on the current node's right subtree and increment
     * depth by one.
     * 4. Call dfs with root and the initial depth of 0.
     * 5. Return bottomLeftValue.
     */
    private int maxDepth;
    private int bottomLeftValue;

    public int findBottomLeftValue(TreeNode root) {
        maxDepth = -1;
        bottomLeftValue = 0;
        dfs(root, 0);
        return bottomLeftValue;
    }

    private void dfs(TreeNode current, int depth) {
        if (current == null) {
            return;
        }
        if (depth > maxDepth) { // If true, we discovered a new level
            maxDepth = depth;
            bottomLeftValue = current.val;
        }
        dfs(current.left, depth + 1);
        dfs(current.right, depth + 1);
    }
}
