package com.example.solution.Q501_Q1000;

import com.example.utility.TreeNode;

public class P988_Smallest_String_From_Leaf {
    /**
     * Reference:
     * 
     * Strategy:
     * 1. Initialize an empty string smallestString to store the lexicographically
     * smallest string.
     * 2. Call the helper function dfs(root, "").
     * a. The dfs function takes the current node root and the current string
     * currentString as parameters.
     * 3. Inside the dfs function:
     * a. If the current node root is NULL, return (base case).
     * b. Construct the currentString by appending the character corresponding to
     * the current node's value to the beginning of the currentString.
     * c. If the current node root is a leaf node:
     * c1. If smallestString is empty or if the currentString is lexicographically
     * smaller than smallestString: Update smallestString to be the currentString.
     * c2. Recursively call dfs on the left child of the current node (if it
     * exists).
     * c3. Recursively call dfs on the right child of the current node (if it
     * exists).
     * 4. After the dfs function call, return the smallestString.
     */
    String smallestString = "";

    public String smallestFromLeaf(TreeNode root) {
        dfs(root, "");
        return smallestString;
    }

    // Helper function to find the lexicographically smallest string
    void dfs(TreeNode root, String currentString) {

        // If the current node is NULL, return
        if (root == null) {
            return;
        }

        // Construct the current string by appending
        // the character corresponding to the node's value
        currentString = (char) (root.val + 'a') + currentString;

        // If the current node is a leaf node
        if (root.left == null && root.right == null) {

            // If the current string is smaller than the result
            // or if the result is empty
            if (smallestString.isEmpty() || smallestString.compareTo(currentString) > 0) {
                smallestString = currentString;
            }
        }

        // Recursively traverse the left subtree
        if (root.left != null) {
            dfs(root.left, currentString);
        }

        // Recursively traverse the right subtree
        if (root.right != null) {
            dfs(root.right, currentString);
        }

    }
}
