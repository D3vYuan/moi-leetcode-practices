package com.example.katana.Q001_Q500;

import com.example.utility.TreeNode;

public class P129_Sum_Root_to_Leaf_Numbers {
    public int transverse(TreeNode node, int previousValue, int sum) {
        if (node.left == null && node.right == null) {
            return previousValue * 10 + node.val;
        }

        int currentValue = previousValue * 10 + node.val;
        // System.out.println(String.format("Node: %d | Current: %d", node.val,
        // currentValue));
        int leftSum = 0;
        if (node.left != null) {
            leftSum += transverse(node.left, currentValue, sum);
        }

        int rightSum = 0;
        if (node.right != null) {
            rightSum += transverse(node.right, currentValue, sum);
        }

        // System.out.println(String.format("Previous: %d | Current: %d | Left: %d |
        // Right: %d", previousValue,
        // currentValue, leftSum, rightSum));
        // return previousValue + leftSum + rightSum;
        return leftSum + rightSum;
    }

    public int sumNumbers(TreeNode root) {
        int sum = transverse(root, 0, 0);
        return sum;
    }
}
