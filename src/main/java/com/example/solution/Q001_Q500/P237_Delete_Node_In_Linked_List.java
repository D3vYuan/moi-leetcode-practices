package com.example.solution.Q001_Q500;

import com.example.utility.ListNode;

public class P237_Delete_Node_In_Linked_List {
    /**
     * Reference:
     * https://leetcode.com/problems/delete-node-in-a-linked-list/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Copy the data from the successor node into the current node to be deleted.
     * 2. Update the next pointer of the current node to reference the next pointer
     * of the successor node.
     * 
     */
    public void deleteNode(ListNode node) {
        // Overwrite data of next node on current node.
        node.val = node.next.val;
        // Make current node point to next of next node.
        node.next = node.next.next;
    }
}
