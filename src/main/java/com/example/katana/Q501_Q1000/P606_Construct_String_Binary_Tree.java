package com.example.katana.Q501_Q1000;

//   Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class P606_Construct_String_Binary_Tree {
    public String getNodeRepresentation(TreeNode node) {
        if (node == null) {
            return "";
        }

        System.out.println(String.format("Traversing: %d", node.val));

        if (node.left == null && node.right == null) {
            System.out.println(String.format("Traversing: %d - No child", node.val));
            return String.format("%d", node.val);
        }

        StringBuilder sb = new StringBuilder();
        String leftValue = getNodeRepresentation(node.left);
        String rightValue = getNodeRepresentation(node.right);

        System.out.println(String.format("Traversing: %d - Found Left %s", node.val, leftValue));
        System.out.println(String.format("Traversing: %d - Found Right %s", node.val, rightValue));

        if (rightValue == "") {
            sb.append(String.format("%d(%s)", node.val, leftValue));
        } else {
            sb.append(String.format("%d(%s)(%s)", node.val, leftValue, rightValue));
        }

        return sb.toString();
    }

    public String tree2str(TreeNode root) {
        int rootValue = root.val;
        StringBuilder sb = new StringBuilder();

        String leftValue = getNodeRepresentation(root.left);
        String rightValue = getNodeRepresentation(root.right);

        if (leftValue == "" && rightValue == "") {
            sb.append(String.format("%d", rootValue));
        } else if (rightValue == "") {
            sb.append(String.format("%d(%s)", rootValue, leftValue));
        } else {
            sb.append(String.format("%d(%s)(%s)", rootValue, leftValue, rightValue));
        }
        return sb.toString();
    }
}
