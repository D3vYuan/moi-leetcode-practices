package com.example.solution.Q501_Q1000;

import java.util.Stack;

import com.example.utility.TreeNode;

public class P623_Add_One_Row_To_Tree {
    /**
     * Reference:
     * https://leetcode.com/problems/add-one-row-to-tree/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. We start by pushing the root NodeNodeNode onto the stack. Then, at every
     * step we do as follows:
     * a. Pop an element from the stack.
     * b. For every Node popped, check if its depth corresponds to one prior to the
     * depth at which the new node needs to be inserted.
     * c. If yes, insert the new nodes appropriately as in the last approach.
     * d. If no, we push both the left and the right child Node(value+depth) of the
     * current node onto the stack.
     * e. Continue the popping and pushing process till the stack becomes empty.
     */

    class Node {
        Node(TreeNode n, int d) {
            node = n;
            depth = d;
        }

        TreeNode node;
        int depth;
    }

    public TreeNode addOneRow(TreeNode root, int val, int dept) {
        if (dept == 1) {
            TreeNode n = new TreeNode(val);
            n.left = root;
            return n;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(root, 1));
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            if (n.node == null)
                continue;
            if (n.depth == dept - 1) {
                TreeNode temp = n.node.left;
                n.node.left = new TreeNode(val);
                n.node.left.left = temp;
                temp = n.node.right;
                n.node.right = new TreeNode(val);
                n.node.right.right = temp;

            } else {
                stack.push(new Node(n.node.left, n.depth + 1));
                stack.push(new Node(n.node.right, n.depth + 1));
            }
        }
        return root;
    }
}
