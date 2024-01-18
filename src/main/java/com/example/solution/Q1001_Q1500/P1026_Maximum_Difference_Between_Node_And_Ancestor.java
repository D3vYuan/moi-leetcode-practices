package com.example.solution.Q1001_Q1500;

import com.example.utility.TreeNode;

public class P1026_Maximum_Difference_Between_Node_And_Ancestor {

    /**
     * Reference:
     * https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/editorial/?envType=daily-question&envId=2024-01-15
     * 
     * Explanation:
     * 1. Since the problem asks us for the Maximum Difference, maybe we do not need
     * to compare all ancestors for a given node and we only need to compare the
     * ancestors with the Maximum value and the Minimum value.
     * 
     * Strategy:
     * 1. Step 1: Initialize a variable result to record the required maximum
     * difference.
     * 2. Step 2: Define a function helper, which takes three arguments as input.
     * a. The first argument is the current node, and the second and third arguments
     * are the maximum and minimum values along the root to the current node,
     * respectively.
     * b. In the function helper, update result and call helper on the left and
     * right subtrees.
     * 3. Step 3: Run helper on the root. It will automatically do recursion on
     * every node.
     * 4. Step 4: Finally, return result
     */

    // record the required maximum difference
    int result = 0;

    public int maxAncestorDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }

        result = 0;
        dfs(root, root.val, root.val);
        return result;
    }

    public void dfs(TreeNode node, int nodeMax, int nodeMin) {
        if (node == null) {
            return;
        }

        int maxDifference = Math.abs(node.val - nodeMax);
        int minDifference = Math.abs(node.val - nodeMin);
        int possibleResult = Math.max(maxDifference, minDifference);

        // update `result`
        result = Math.max(possibleResult, result);

        // update the max and min
        nodeMax = Math.max(nodeMax, node.val);
        nodeMin = Math.min(nodeMin, node.val);

        dfs(node.left, nodeMax, nodeMin);
        dfs(node.right, nodeMax, nodeMin);
    }
}
