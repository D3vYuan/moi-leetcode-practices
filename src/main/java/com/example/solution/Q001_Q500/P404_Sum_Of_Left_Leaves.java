package com.example.solution.Q001_Q500;

import java.util.ArrayDeque;
import java.util.Deque;

import com.example.utility.TreeNode;

public class P404_Sum_Of_Left_Leaves {
    /**
     * Reference:
     * https://leetcode.com/problems/sum-of-left-leaves/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. While we're at a node, we can check if its left-child is a leaf node
     * (instead of trying to check if the node itself is a left child).
     * 2. When we're ready to visit the children of a node, we can pass some extra
     * information down telling the left child that it is a left child.
     */
    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }

    public int sumOfLeftLeaves(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int total = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode subRoot = stack.pop();
            // Check if the left node is a leaf node.
            if (isLeaf(subRoot.left)) {
                total += subRoot.left.val;
            }
            // If the right node exists, put it on the stack.
            if (subRoot.right != null) {
                stack.push(subRoot.right);
            }
            // If the left node exists, put it on the stack.
            if (subRoot.left != null) {
                stack.push(subRoot.left);
            }
        }

        return total;
    }

}
