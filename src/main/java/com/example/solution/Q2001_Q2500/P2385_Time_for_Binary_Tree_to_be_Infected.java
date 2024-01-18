package com.example.solution.Q2001_Q2500;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.example.utility.TreeNode;

public class P2385_Time_for_Binary_Tree_to_be_Infected {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {

        /**
         * Reference:
         * 
         * Explanation:
         * 1. define a function that converts our binary tree to an undirected graph by
         * traversing the tree and creating a graph. The parameters are the current node
         * and its parent. We traverse the tree with a preorder traversal, visiting
         * first the root, then the left and right child, so we can log the parent of
         * each node and make a connection to it. When we encounter a new right or left
         * child, we add them to the adjacency list.
         * The algorithm for this recursive convert function is defined as follows:
         * a. If current == null, return.
         * b. If the root has a new value, we add it to the map and create a new
         * adjacency list to store the adjacent vertices
         * c. Retrieve the adjacency list of the current vertex.
         * d. If current is not the root, add its parent to the adjacency list.
         * e. If current a left child, add the child to its adjacency list.
         * f. If current has a right child, add the child to its adjacency list.
         * g. Recursively call convert on current.left with current as the parent.
         * h. Recursively call convert on current.right with current as the parent.
         * 2. we will use a queue to store the vertices that we need to visit. We will
         * create a set to store the nodes we have already visited so we don't visit
         * them multiple times. We add start to the queue and the visited set and then
         * iterate through the vertices in the queue until it is empty. We set the
         * variable levelSize to the size of the queue so we can keep track of how many
         * vertices are in the current level. We poll() a vertex current from the queue.
         * We iterate through each of the values in its adjacency list checking whether
         * each one has been visited. If they have not been visited, we add them to the
         * queue and the visited set. After adding all of the adjacent vertices, we
         * decrement levelSize. When there are no more vertices in the current level, we
         * will move to the next level, so we increment the variable minute. When the
         * queue is empty, we return minute - 1, because we have incremented minute for
         * each level, but the time taken by the first node to infect neighbors is zero.
         * 
         * Strategy
         * 1. Declare a hash map map to store vertices and their adjacency list for
         * edges.
         * 2. Implement a function convert that creates an undirected graph of the tree
         * and stores it in map as explained above.
         * 3. Call convert(root, 0, map) as the root has no parent.
         * 4. Set minute, the distance from the start vertex to 0.
         * 5. Initialize a queue and add start.
         * 6. Initialize a set visited to store the visited vertexes and add start.
         * 7. While queue is not empty:
         * a. Set levelSize, the number of vertices in this level, to the size of queue.
         * b. While levelSize is greater than 0:
         * b1. Remove a vertex current from the queue .
         * b2. For each edge in the adjacency list:
         * b3. Check whether the edge has been visited. If not, add it to queue and
         * visited.
         * b4. Decrement levelSize.
         * c. Increment minute as the distance from startNode has increased.
         * 8. After the BFS, return minute - 1
         */

        public int amountOfTime(TreeNode root, int start) {
            Map<Integer, Set<Integer>> map = new HashMap<>();
            convert(root, 0, map);
            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            int minute = 0;
            Set<Integer> visited = new HashSet<>();
            visited.add(start);

            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                while (levelSize > 0) {
                    int current = queue.poll();
                    for (int num : map.get(current)) {
                        if (!visited.contains(num)) {
                            visited.add(num);
                            queue.add(num);
                        }
                    }
                    levelSize--;
                }
                minute++;
            }

            return minute - 1;
        }

        public void convert(TreeNode current, int parent, Map<Integer, Set<Integer>> map) {
            if (current == null) {
                return;
            }

            if (!map.containsKey(current.val)) {
                map.put(current.val, new HashSet<>());
            }

            Set<Integer> adjacentList = map.get(current.val);
            if (parent != 0) {
                adjacentList.add(parent);
            }

            if (current.left != null) {
                adjacentList.add(current.left.val);
            }

            if (current.right != null) {
                adjacentList.add(current.right.val);
            }

            convert(current.left, current.val, map);
            convert(current.right, current.val, map);
        }
    }
}
