package com.example.solution.Q2001_Q2500;

import java.util.HashMap;
import java.util.Stack;

import com.example.utility.TreeNode;

public class P2331_Boolean_Binary_Tree {
    /**
     * Reference:
     * https://leetcode.com/problems/evaluate-boolean-binary-tree/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a stack st with the root node. Also, create a hashmap evaluated with node data type for the key and boolean for values.
     * 2. Iterate until st is empty:
     * a. Initialise the top element of the st with topNode.
     * b. If the topNode is a leaf node:
     *      Pop the top element of st and add the value of the node to evaluated with the node as the key.
     * c. If both the children of topNode are present in the hashmap evaluated:
     *      If the value of topNode is 2:
     *          Store the evaluation of topNode as boolean OR of the evaluations of the children of topNode in evaluated.
     *      If the value of topNode is 3:
     *          Store the evaluation of topNode as boolean AND of the evaluations of the children of topNode in evaluated.
     *      Pop the top element of st.
     * d. If any of the children of topNode are not present in evaluated:
     *      Push the left and right child of topNode in st. 
     * 3. Return the evaluated boolean value of root stored in evaluated.
     */

    public boolean evaluateTree(TreeNode root) {
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        HashMap<TreeNode, Boolean> evaluated = new HashMap<>();

        while (!st.isEmpty()) {
            TreeNode topNode = st.peek();

            // If the node is a leaf node, store its value in the evaluated hashmap
            // and continue
            if (topNode.left == null && topNode.right == null) {
                st.pop();
                evaluated.put(topNode, topNode.val == 1);
                continue;
            }

            // If both the children have already been evaluated, use their
            // values to evaluate the current node.
            if (evaluated.containsKey(topNode.left) &&
                evaluated.containsKey(topNode.right)) {
                st.pop();
                if (topNode.val == 2) {
                    evaluated.put(topNode,
                            evaluated.get(topNode.left) || evaluated.get(topNode.right));
                } else {
                    evaluated.put(topNode,
                            evaluated.get(topNode.left) && evaluated.get(topNode.right));
                }
            } else {
                // If both the children are not leaf nodes, push the current
                // node along with its left and right child back into the stack.
                st.push(topNode.right);
                st.push(topNode.left);
            }
        }
        
        return evaluated.get(root);
    }
}
