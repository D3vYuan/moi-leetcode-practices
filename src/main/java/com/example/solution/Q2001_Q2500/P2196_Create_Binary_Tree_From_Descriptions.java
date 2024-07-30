package com.example.solution.Q2001_Q2500;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.example.utility.TreeNode;

public class P2196_Create_Binary_Tree_From_Descriptions {
    /**
     * Reference:
     * https://leetcode.com/problems/create-binary-tree-from-descriptions/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize children and parents as sets to track unique child and parent
     * nodes, respectively.
     * 2. Initialize parentToChildren as a map to store parent to children
     * relationships using array of pairs.
     * 3. Build the graph:
     * a. Iterate through each d in descriptions:
     * Extract parent, child, and isLeft from d.
     * Add parent and child to parents to track all nodes.
     * Add child to children.
     * Push back the pair (child, isLeft) into parentToChildren[parent] to store
     * child nodes and their left/right flags.
     * 4. Iterate through parents to find the node that is in parents but not in
     * children, and assign it to root.
     * 5. Create the root node TreeNode* using the first element of parents.
     * 6. Construct the binary tree using BFS:
     * a. Initialize a queue and push root into it.
     * b. While queue is not empty:
     * Dequeue the front parent node from queue.
     * Iterate over each childInfo in parentToChildren[parent.val]:
     * Extract childValue and isLeft.
     * Create a new TreeNode* child with childValue.
     * Push child into queue.
     * Attach child to parent.left or parent.right based on the isLeft flag.
     * 7. Return the constructed root node of the binary tree.
     */

    public TreeNode createBinaryTree(int[][] descriptions) {
        // Sets to track unique children and parents
        Set<Integer> children = new HashSet<>(), parents = new HashSet<>();
        // Map to store parent to children relationships
        Map<Integer, List<int[]>> parentToChildren = new HashMap<>();

        // Build graph from parent to child, and add nodes to HashSets
        for (int[] d : descriptions) {
            int parent = d[0], child = d[1], isLeft = d[2];
            parents.add(parent);
            parents.add(child);
            children.add(child);
            parentToChildren
                    .computeIfAbsent(parent, l -> new ArrayList<>())
                    .add(new int[] { child, isLeft });
        }

        // Find the root node by checking which node is in parents but not in children
        parents.removeAll(children);
        TreeNode root = new TreeNode(parents.iterator().next());

        // Starting from root, use BFS to construct binary tree
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode parent = queue.poll();
            // Iterate over children of current parent
            for (int[] childInfo : parentToChildren.getOrDefault(
                    parent.val,
                    Collections.emptyList())) {
                int childValue = childInfo[0], isLeft = childInfo[1];
                TreeNode child = new TreeNode(childValue);
                queue.offer(child);
                // Attach child node to its parent based on isLeft flag
                if (isLeft == 1) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }
        }

        return root;
    }
}
