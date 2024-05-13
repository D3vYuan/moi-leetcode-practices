package com.example.solution.Q501_Q1000;

import com.example.utility.TreeNode;

public class P543_Diameter_Of_Binary_Tree {
    /**
     * Reference:
     * https://leetcode.com/problems/diameter-of-binary-tree/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initalize an integer variable diameter to keep track of the longest path
     * we find from the DFS.
     * 2. Implement a recursive function longestPath which takes a TreeNode as
     * input. It should recursively explore the entire tree rooted at the given
     * node. Once it's finished, it should return the longest path out of its left
     * and right branches:
     * a. if node is None, we have reached the end of the tree, hence we should
     * return 0;
     * b. We want to recursively explore node's children, so we call longestPath
     * again with node's left and right children. In return, we get the longest path
     * of its left and right children leftPath and rightPath;
     * c. if leftPath plus rightPath is longer than the current longest diameter
     * found, then we need to update diameter;
     * d. finally, we return the longer one of leftPath and rightPath. Remember to
     * add 111 as the edge connecting it with its parent.
     * 3. Call longestPath with root
     */

    private int diameter;

    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        longestPath(root);
        return diameter;
    }

    private int longestPath(TreeNode node) {
        if (node == null)
            return 0;
        // recursively find the longest path in
        // both left child and right child
        int leftPath = longestPath(node.left);
        int rightPath = longestPath(node.right);

        // update the diameter if left_path plus right_path is larger
        diameter = Math.max(diameter, leftPath + rightPath);

        // return the longest one between left_path and right_path;
        // remember to add 1 for the path connecting the node and its parent
        return Math.max(leftPath, rightPath) + 1;
    }
}