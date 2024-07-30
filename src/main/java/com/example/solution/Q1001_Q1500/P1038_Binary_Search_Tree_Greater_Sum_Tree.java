package com.example.solution.Q1001_Q1500;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.utility.TreeNode;

public class P1038_Binary_Search_Tree_Greater_Sum_Tree {
    /**
     * Reference:
     * https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Main function - bstToGst(root)
     * a. Initialize an integer array inorderTraversal.
     * b. Call inorder(root).
     * c. Reverse the inorderTraversal array.
     * d. Call replaceValues(root).
     * e. Return root.
     * 
     * 2. inorder(root)
     * a. If the root is null, return.
     * b. Make a call to inorder(root->left).
     * c. Store the value of the current node in the inorderTraversal array.
     * d. Make a call to inorder(root->right).
     * 
     * 3. replaceValues(root)
     * a. If the root is null, return.
     * b. Make calls to the left and right child, i.e. call the
     * replaceValues(root->left) and replaceValues(root->right).
     * c. Initialize nodeSum with 0.
     * d. Iterate through the inorderTraversal array:
     * If the current value is greater than root->val:
     * Add this value to the nodeSum.
     * e. Increment root->val by nodeSum.
     */

    public TreeNode bstToGst(TreeNode root) {
        // Store the inorder traversal in an array.
        List<Integer> inorderTraversal = new ArrayList<>();
        inorder(root, inorderTraversal);

        // Reverse the array to get descending order.
        Collections.reverse(inorderTraversal);

        // Modify the values in the tree.
        replaceValues(root, inorderTraversal);
        return root;
    }

    // Perform any traversal of your choice to store node values.
    private void inorder(TreeNode root, List<Integer> inorderTraversal) {
        if (root == null) {
            return;
        }
        inorder(root.left, inorderTraversal);
        inorderTraversal.add(root.val);
        inorder(root.right, inorderTraversal);
    }

    // Function to modify the values in the tree.
    private void replaceValues(TreeNode root, List<Integer> inorderTraversal) {
        if (root == null) {
            return;
        }
        replaceValues(root.left, inorderTraversal);
        replaceValues(root.right, inorderTraversal);

        int nodeSum = 0;
        // Replace node with values greater than the current value.
        for (int i : inorderTraversal) {
            if (i > root.val) {
                nodeSum += i;
            } else {
                break;
            }
        }

        root.val += nodeSum;
    }
}
