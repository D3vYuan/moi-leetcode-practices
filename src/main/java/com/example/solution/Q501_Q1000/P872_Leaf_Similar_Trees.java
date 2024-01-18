package com.example.solution.Q501_Q1000;

import java.util.ArrayList;
import java.util.List;

import com.example.utility.TreeNode;

public class P872_Leaf_Similar_Trees {

    /**
     * Reference:
     * https://leetcode.com/problems/leaf-similar-trees/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation:
     * 1. Our dfs function writes the node's value if it is a leaf, and then
     * recursively explores each child. This is guaranteed to visit each leaf in
     * left-to-right order, as left-children are fully explored before
     * right-children
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList();
        List<Integer> leaves2 = new ArrayList();
        dfs(root1, leaves1);
        dfs(root2, leaves2);
        return leaves1.equals(leaves2);
    }

    public void dfs(TreeNode node, List<Integer> leafValues) {
        if (node != null) {
            if (node.left == null && node.right == null)
                leafValues.add(node.val);
            dfs(node.left, leafValues);
            dfs(node.right, leafValues);
        }
    }
}
