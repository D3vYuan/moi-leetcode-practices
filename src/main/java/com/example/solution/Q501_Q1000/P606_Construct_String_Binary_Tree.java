package com.example.solution.Q501_Q1000;

import com.example.utility.TreeNode;

public class P606_Construct_String_Binary_Tree {

    /**
     * Reference:
     * https://leetcode.com/problems/construct-string-from-binary-tree/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation
     * 1. Case 1: Both the left child and the right child exist for the current
     * node. In this case, we need to put the braces () around both the left child's
     * preorder traversal output and the right child's preorder traversal output.
     * 2. Case 2: None of the left or the right child exist for the current node. In
     * this case, as shown in the figure below, considering empty braces for the
     * null left and right children is redundant. Hence, we need not put braces for
     * any of them.
     * 3. Case 3: Only the left child exists for the current node. As the figure
     * below shows, putting empty braces for the right child in this case is
     * unnecessary while considering the preorder traversal. This is because the
     * right child will always come after the left child in the preorder traversal.
     * Thus, omitting the empty braces for the right child also leads to same
     * mapping between the string and the binary tree.
     * 4. Case 4: Only the right child exists for the current node. In this case, we
     * need to consider the empty braces for the left child. This is because, during
     * the preorder traversal, the left child needs to be considered first. Thus, to
     * indicate that the child following the current node is a right child we need
     * to put a pair of empty braces for the left child.
     * 
     */

    public void dfs(TreeNode node, StringBuilder sb) {
        if (node == null)
            return;

        sb.append(String.valueOf(node.val));
        if (node.left == null && node.right == null)
            return;

        sb.append("(");
        dfs(node.left, sb);
        sb.append(")");

        if (node.right != null) {
            sb.append("(");
            dfs(node.right, sb);
            sb.append(")");
        }
    }

    public String tree2str(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString();
    }
}
