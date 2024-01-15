package com.example.katana;

public class P938_Range_Sum_BST {

    public int dfs(TreeNode node, int low, int high) {
        if (node == null) {
            return 0;
        }

        // System.out.println(String.format("Traversing: %d", node.val));

        int sum = node.val < low || node.val > high ? 0 : node.val;
        sum += dfs(node.left, low, high);
        sum += dfs(node.right, low, high);
        return sum;
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        return dfs(root, low, high);
    }
}
