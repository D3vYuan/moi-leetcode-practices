package com.example.solution.Q1001_Q1500;

import com.example.utility.TreeNode;

public class P1325_Delete_Leaves_With_Given_Value {
    /**
     * Reference:
     * https://leetcode.com/problems/delete-leaves-with-a-given-value/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Base Case: If root is null, return null, to handle the conditions of an
     * empty tree or traversing beyond the leaf nodes.
     * 2. Recursive Traversal: Perform a postorder traversal to ensure that we
     * process all descendant nodes before the current node (root):
     * Recursively call removeLeafNodes for the left child of the root and update
     * the left child with the return value.
     * Similarly, recursively call removeLeafNodes for the right child of root and
     * update the right child with the return value.
     * 3. Node Evaluation:
     * Check if the current root node is a leaf node and if its value equals the
     * target. If both conditions are satisfied, return null to effectively delete
     * the node by not reconnecting it to its parent.
     * If the node is neither a leaf nor matches target, return the root itself.
     */

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        // Base case
        if (root == null) {
            return null;
        }

        // 1. Visit the left children
        root.left = removeLeafNodes(root.left, target);

        // 2. Visit the right children
        root.right = removeLeafNodes(root.right, target);

        // 3. Check if the current node is a leaf node and its value equals target
        if (root.left == null && root.right == null && root.val == target) {
            // Delete the node by returning null to the parent, effectively disconnecting it
            return null;
        }

        // Keep it untouched otherwise
        return root;
    }
}
