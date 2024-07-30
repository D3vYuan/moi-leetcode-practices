package com.example.solution.Q1001_Q1500;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.utility.TreeNode;

public class P1110_Delete_Nodes_And_Return_Forest {
    /**
     * Reference:
     * https://leetcode.com/problems/delete-nodes-and-return-forest/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialization:
     * a. Convert the to_delete array to a set for efficient lookups and store it as
     * toDeleteSet.
     * b. Create an empty list forest to store the roots of the resulting forest.
     * 
     * 2. Recursive Traversal: Perform a postorder traversal to ensure that we
     * process all descendant nodes before the current node (node):
     * a. Recursively call processNode for the left child of node and update the
     * left child with the return value.
     * b. Similarly, recursively call processNode for the right child of node and
     * update the right child with the return value.
     * 
     * 3. Node Evaluation:
     * a. Check if the current node needs to be deleted by checking if its value
     * exists in the toDeleteSet. If the node needs to be deleted:
     * If node has a left child that is not null, add the left child to the forest.
     * If node has a right child that is not null, add the right child to the
     * forest.
     * Delete the current node and return null to effectively remove the node by not
     * reconnecting it to its parent.
     * b. If the node is not to be deleted, return the node itself.
     */

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> toDeleteSet = new HashSet<>();
        for (int val : to_delete) {
            toDeleteSet.add(val);
        }
        List<TreeNode> forest = new ArrayList<>();

        root = processNode(root, toDeleteSet, forest);

        // If the root is not deleted, add it to the forest
        if (root != null) {
            forest.add(root);
        }

        return forest;
    }

    private TreeNode processNode(
            TreeNode node,
            Set<Integer> toDeleteSet,
            List<TreeNode> forest) {
        if (node == null) {
            return null;
        }

        node.left = processNode(node.left, toDeleteSet, forest);
        node.right = processNode(node.right, toDeleteSet, forest);

        // Node Evaluation: Check if the current node needs to be deleted
        if (toDeleteSet.contains(node.val)) {
            // If the node has left or right children, add them to the forest
            if (node.left != null) {
                forest.add(node.left);
            }
            if (node.right != null) {
                forest.add(node.right);
            }
            // Return null to its parent to delete the current node
            return null;
        }

        return node;
    }
}
