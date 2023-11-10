package com.example.template;

import java.util.Stack;

class TreeNode {
    TreeNode left;
    TreeNode right;
}

public class BST_DFS {
    // Binary tree: DFS (recursive)
    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int ans = 0;
        // do logic
        dfs(root.left);
        dfs(root.right);
        return ans;
    }

    // Binary tree: DFS (iterative)
    public int dfs_2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int ans = 0;

        while (!stack.empty()) {
            TreeNode node = stack.pop();
            // do logic
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        return ans;
    }
}
