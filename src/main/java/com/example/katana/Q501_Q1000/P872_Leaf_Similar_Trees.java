package com.example.katana.Q501_Q1000;

import java.util.ArrayList;
import java.util.List;

public class P872_Leaf_Similar_Trees {

    private void dfs(TreeNode node, List<Integer> rootLeaves) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            rootLeaves.add(node.val);
            return;
        }

        dfs(node.left, rootLeaves);
        dfs(node.right, rootLeaves);
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> root1Leaves = new ArrayList<>();
        List<Integer> root2Leaves = new ArrayList<>();

        dfs(root1, root1Leaves);
        dfs(root2, root2Leaves);

        if (root1Leaves.size() != root2Leaves.size()) {
            return false;
        }

        for (int i = 0; i < root1Leaves.size(); i++) {
            Integer currentRoot1Value = root1Leaves.get(i);
            Integer currentRoot2Value = root2Leaves.get(i);
            if (currentRoot1Value != currentRoot2Value) {
                return false;
            }
        }

        return true;
    }
}
