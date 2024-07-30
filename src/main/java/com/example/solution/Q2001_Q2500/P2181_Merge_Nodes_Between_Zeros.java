package com.example.solution.Q2001_Q2500;

import com.example.utility.ListNode;

public class P2181_Merge_Nodes_Between_Zeros {

    /**
     * Reference:
     * https://leetcode.com/problems/merge-nodes-in-between-zeros/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize modify and nextSum with head->next that stores the first node
     * with a non-zero value.
     * 2. Iterate through the list until modify is not null:
     * a. Initialize sum with 0 to store the sum of the current block.
     * b. Iterate through the block until nextSum encounters a 0:
     * c. Add the value of the current node to sum.
     * d. Move nextSum to the next node.
     * e. Modify the node value at modify to sum.
     * f. Move nextSum to the next node that stores the next block's first non-zero
     * value. Also, set modify->next to this node.
     * g. Move modify to it's next node.
     * 3. Return head->next
     */

    public ListNode mergeNodes(ListNode head) {
        // Initialize a sentinel/dummy node with the first non-zero value.
        ListNode modify = head.next;
        ListNode nextSum = modify;

        while (nextSum != null) {
            int sum = 0;
            // Find the sum of all nodes until you encounter a 0.
            while (nextSum.val != 0) {
                sum += nextSum.val;
                nextSum = nextSum.next;
            }

            // Assign the sum to the current node's value.
            modify.val = sum;
            // Move nextSum to the first non-zero value of the next block.
            nextSum = nextSum.next;
            // Move modify also to this node.
            modify.next = nextSum;
            modify = modify.next;
        }
        return head.next;
    }
}
