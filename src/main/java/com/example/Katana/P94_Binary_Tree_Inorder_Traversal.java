package com.example.katana;

import java.util.ArrayList;
import java.util.List;

public class P94_Binary_Tree_Inorder_Traversal {

    private void dfs(TreeNode node, List<Integer> item) {
        if (node == null) {
            return;
        }

        dfs(node.left, item);
        item.add(node.val);
        dfs(node.right, item);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();
        dfs(root, inOrder);
        return inOrder;
    }
}
