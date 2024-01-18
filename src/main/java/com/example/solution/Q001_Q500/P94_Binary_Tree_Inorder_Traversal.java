package com.example.solution.Q001_Q500;

import java.util.ArrayList;
import java.util.List;

import com.example.utility.TreeNode;

public class P94_Binary_Tree_Inorder_Traversal {
    /**
     * Reference:
     * https://leetcode.com/problems/binary-tree-inorder-traversal/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * 
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    public void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            helper(root.left, res);
            res.add(root.val);
            helper(root.right, res);
        }
    }
}
