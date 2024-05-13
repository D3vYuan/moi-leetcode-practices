package com.example.solution.Q001_Q500;

import com.example.utility.TreeNode;

public class P100_Same_Tree {
    /**
     * Reference:
     * https://leetcode.com/problems/same-tree/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. The simplest strategy here is to use recursion.
     * Check if p and q nodes are not None, and their values are equal.
     * If all checks are OK, do the same for the child nodes
     * recursively.
     */

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // p and q are both null
        if (p == null && q == null)
            return true;
        // one of p and q is null
        if (q == null || p == null)
            return false;
        if (p.val != q.val)
            return false;
        return isSameTree(p.right, q.right) && isSameTree(p.left, q.left);
    }
}
