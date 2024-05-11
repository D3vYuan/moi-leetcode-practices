package com.example.solution.Q001_Q500;

import com.example.utility.TreeNode;

public class P129_Sum_Root_to_Leaf_Numbers {

    /**
     * Reference:
     * https://leetcode.com/problems/sum-root-to-leaf-numbers/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Explanation:
     * 1. Recursive preorder traversal is extremely simple: follow Root->Left->Right
     * direction, i.e. do all the business with the node (= update the current
     * number and root-to-leaf sum), and then do the recursive calls for the left
     * and right child nodes.
     */
    int rootToLeaf = 0;

  public void preorder(TreeNode r, int currNumber) {
    if (r != null) {
      currNumber = currNumber * 10 + r.val;
      // if it's a leaf, update root-to-leaf sum
      if (r.left == null && r.right == null) {
        rootToLeaf += currNumber;
      }
      preorder(r.left, currNumber);
      preorder(r.right, currNumber) ;
    }
  }

  public int sumNumbers(TreeNode root) {
    preorder(root, 0);
    return rootToLeaf;
  }
}
