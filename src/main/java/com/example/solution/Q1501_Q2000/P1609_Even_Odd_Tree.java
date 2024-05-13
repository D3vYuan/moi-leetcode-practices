package com.example.solution.Q1501_Q2000;

import java.util.ArrayList;
import java.util.List;

import com.example.utility.TreeNode;

public class P1609_Even_Odd_Tree {
    /**
     * Reference:
     * https://leetcode.com/problems/even-odd-tree/?envType=daily-question&envId=2024-05-05
     * 
     * Explanation:
     * 1. Recursive Preorder Traversal
     * a. If the tree is empty, return.
     * b. Handle the root.
     * c. Traverse the left subtree - call Preorder(root.right).
     * d. Traverse the right subtree - call Preorder(root.left).
     * 
     * Strategy:
     * 1. Declare an array prev to store the previous value on each level.
     * 2. Initialize a node current to root for traversing the tree.
     * 3. Define a function dfs whose parameters are a TreeNode current and level
     * that performs a depth-first search, checking that the nodes meet the
     * requirements for being an Even-Odd tree. If the tree is Even-Odd, it returns
     * true; otherwise, it returns false.
     * a. Base case: if the tree is empty, return true. An empty tree is Even-Odd.
     * b. Check whether the current value has the correct parity compared with the
     * level: current->val % 2 == level % 2. Return false if not.
     * c. Resize and add a new level to prev if we've reached a new level.
     * d. If we have already visited a node on this level, check that the current
     * value is in the correct order depending on the level.
     * d1. If on an even level, check that current.val is greater than the previous.
     * d2. If on an odd level, check that current.val is less than the previous.
     * d3. Otherwise, return false.
     * e. Add current's value to the prev array. Only the most recent node on this
     * level matters to the next node.
     * f. Recursively call dfs on the left and right child, incrementing level.
     * 4. Call and return dfs(current, 0) because the first level will be 0
     */
    private List<Integer> prev = new ArrayList<>();

    public boolean isEvenOddTree(TreeNode root) {
        TreeNode current = root;
        return dfs(current, 0);
    }

    private boolean dfs(TreeNode current, int level) {
        // Base case, an empty tree is Even-Odd
        if (current == null) {
            return true;
        }

        // Compare the parity of current and level
        if (current.val % 2 == level % 2) {
            return false;
        }

        // Add a new level to prev if we've reached a new level
        while (prev.size() <= level) {
            prev.add(0);
        }

        // If there are previous nodes on this level, check increasing/decreasing
        // If on an even level, check that current's value is greater than the previous
        // on this level
        // If on an odd level, check that current's value is less than the previous on
        // this level
        if (prev.get(level) != 0 &&
                ((level % 2 == 0 && current.val <= prev.get(level)) ||
                        (level % 2 == 1 && current.val >= prev.get(level)))) {
            return false;
        }

        // Add current value to prev at index level
        prev.set(level, current.val);

        // Recursively call DFS on the left and right children
        return dfs(current.left, level + 1) && dfs(current.right, level + 1);
    }
}
