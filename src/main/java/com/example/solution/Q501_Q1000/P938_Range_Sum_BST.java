package com.example.solution.Q501_Q1000;

import com.example.utility.TreeNode;

public class P938_Range_Sum_BST {

    /**
     * Reference:
     * https://leetcode.com/problems/range-sum-of-bst/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy:
     * 1.
     * 
     * 
     */

    int ans;

    public int rangeSumBST(TreeNode root, int low, int high) {
        ans = 0;
        dfs(root, low, high);
        return ans;
    }

    public void dfs(TreeNode node, int low, int high) {
        if (node != null) {
            if (low <= node.val && node.val <= high)
                ans += node.val;
            if (low < node.val)
                dfs(node.left, low, high);
            if (node.val < high)
                dfs(node.right, low, high);
        }
    }
}
