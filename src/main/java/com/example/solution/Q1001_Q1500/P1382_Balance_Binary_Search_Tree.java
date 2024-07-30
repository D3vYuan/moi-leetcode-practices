package com.example.solution.Q1001_Q1500;

import java.util.ArrayList;
import java.util.List;

import com.example.utility.TreeNode;

public class P1382_Balance_Binary_Search_Tree {
    /**
     * Reference:
     * https://leetcode.com/problems/balance-a-binary-search-tree/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialization:
     * a. Create an empty list inorder to store the nodes' values after the inorder traversal.
     * 
     * 2. Perform inorder traversal:
     * a. Traverse the BST and populate the inorder list with the node values in sorted order.
     * 
     * 3. Reconstruct the balanced BST:
     * a. Define a recursive function createBalancedBST that takes the inorder list, start index, and end index as parameters.
     *      If start is greater than end, return null (or equivalent).
     *      Calculate the mid index as the middle of the current range.
     *      Create a new tree node with the value at the mid index.
     *      Recursively build the left subtree using the left half of the current range.
     *      Recursively build the right subtree using the right half of the current range.
     * 
     * 4. Return the root of the newly constructed balanced BST.
     */

    public TreeNode balanceBST(TreeNode root) {
        // Create a list to store the inorder traversal of the BST
        List<Integer> inorder = new ArrayList<>();
        inorderTraversal(root, inorder);

        // Construct and return the balanced BST
        return createBalancedBST(inorder, 0, inorder.size() - 1);
    }

    private void inorderTraversal(TreeNode root, List<Integer> inorder) {
        // Perform an inorder traversal to store the elements in sorted order
        if (root == null) return;
        inorderTraversal(root.left, inorder);
        inorder.add(root.val);
        inorderTraversal(root.right, inorder);
    }

    private TreeNode createBalancedBST(
        List<Integer> inorder,
        int start,
        int end
    ) {
        // Base case: if the start index is greater than the end index, return null
        if (start > end) return null;

        // Find the middle element of the current range
        int mid = start + (end - start) / 2;

        // Recursively construct the left and right subtrees
        TreeNode leftSubtree = createBalancedBST(inorder, start, mid - 1);
        TreeNode rightSubtree = createBalancedBST(inorder, mid + 1, end);

        // Create a new node with the middle element and attach the subtrees
        TreeNode node = new TreeNode(
            inorder.get(mid),
            leftSubtree,
            rightSubtree
        );
        return node;
    }
}
