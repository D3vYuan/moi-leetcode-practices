package com.example.solution.Q1001_Q1500;

import java.util.ArrayDeque;
import java.util.Deque;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.example.utility.TreeNode;

public class P1457_Palindromic_Path_Binary_Tree {
    /**
     * Reference:
     * https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/?envType=daily-question&envId=2024-01-19
     * 
     * Explanation:
     * 1. The problem consists of two subproblems:
     * a. Traverse the tree to build all root-to-leaf paths.
     * b. For each root-to-leaf path, check if it's a pseudo-palindromic path or
     * not.
     * 2. Root-to-leaf traversal is so-called DFS preorder traversal. To implement
     * it, one has to follow the straightforward strategy Root->Left->Right.
     * 3. the path is pseudo-palindromic if it has at most one digit with an odd
     * frequency.
     * 4. The idea is to keep the frequency of digit 1 in the first bit, 2 in the
     * second bit, etc: path ^= (1 << node.val).
     * Left shift operator is used to define the bit, and XOR operator - to compute
     * the digit frequency.
     * 5. XOR of zero and a bit results in that bit. XOR of two equal bits (even if
     * they are zeros) results in a zero. Hence, one could see the bit in a path
     * only if it appears an odd number of times
     * 6. to ensure that at most one digit has an odd frequency, one has to check
     * that path is a power of two, i.e., at most one bit is set to one. That could
     * be done by turning off (= setting to 0) the rightmost 1-bit: path & (path -
     * 1) == 0
     * 7. x & (x - 1) is a way to set the rightmost 1-bit to zero, i.e., x & (x - 1)
     * == 0 for the power of two. To subtract 1 means to change the rightmost 1-bit
     * to 0 and to set all the lower bits to 1. Now AND operator: the rightmost
     * 1-bit will be turned off because 1 & 0 = 0, and all the lower bits as well.
     * 
     * Strategy:
     * 1. Initialize the counter to zero.
     * 2. Push root into the stack.
     * 3. While the stack is not empty:
     * a. Pop out a node from the stack and update the current number.
     * b. If the node is a leaf, update the root-to-leaf path, check it for being
     * pseudo-palindromic, and update the count.
     * c. Push right and left child nodes into the stack.
     * 4. Return count.
     */
    public int pseudoPalindromicPaths(TreeNode root) {
        int count = 0, path = 0;

        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        stack.push(new ImmutablePair<TreeNode, Integer>(root, 0));

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> p = stack.pop();
            TreeNode node = p.getKey();
            path = p.getValue();

            if (node != null) {
                // compute occurrence of each digit
                // in the corresponding register
                path = path ^ (1 << node.val);

                // if it's a leaf check if the path is pseudo-palindromic
                if (node.left == null && node.right == null) {
                    // check if at most one digit has an odd frequency
                    if ((path & (path - 1)) == 0) {
                        ++count;
                    }
                } else {
                    stack.push(new ImmutablePair<TreeNode, Integer>(node.left, path));
                    stack.push(new ImmutablePair<TreeNode, Integer>(node.right, path));
                }
            }
        }
        return count;
    }
}
