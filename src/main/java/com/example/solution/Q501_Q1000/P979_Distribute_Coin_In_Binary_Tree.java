package com.example.solution.Q501_Q1000;

import com.example.utility.TreeNode;

public class P979_Distribute_Coin_In_Binary_Tree {
    /**
     * Reference: https://leetcode.com/problems/distribute-coins-in-binary-tree/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a variable moves to 0.
     * 2. Define a recursive function dfs that counts the number of moves needed to distribute the coins in the tree given the root as current.
     *      Base case: If current is null, return 0 because no coins need to be exchanged.
     *      Set a variable leftCoins to the number of coins the left subtree needs to exchange, the result of dfs(current.left).
     *      Set a variable rightCoins to the number of coins the right subtree needs to exchange, the result of dfs(current.right).
     *      Calculate the number of moves needed to distribute coins in each of the subtrees. Since the coins exchanged may be negative, we sum the absolute values of leftCoins and rightCoins and then add this sum to moves.
     *      Return the number of coins the current node has available to exchange with its parent. It will keep one coin, so subtract 1 from its value and sum the result with leftCoins and rightCoins.
     * 3. Call dfs(current).
     * 4. Return moves.
     */

    private int moves;

    public int distributeCoins(TreeNode root) {
        moves = 0;
        dfs(root);
        return moves;
    }

    private int dfs(TreeNode current) {
        if (current == null) return 0;

        // Calculate the coins each subtree has available to exchange
        int leftCoins = dfs(current.left);
        int rightCoins = dfs(current.right);

        // Add the total number of exchanges to moves
        moves += Math.abs(leftCoins) + Math.abs(rightCoins);

        // The number of coins current has available to exchange
        return (current.val - 1) + leftCoins + rightCoins;
    }
}
