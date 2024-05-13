package com.example.solution.Q001_Q500;

import com.example.utility.ListNode;

public class P206_Reverse_Linked_List {
    /**
     * Reference:
     * https://leetcode.com/problems/reverse-linked-list/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. While traversing the list, we can change the current node's next pointer
     * to point to its previous element.
     * 2. Since a node does not have reference to its previous node, we must store
     * its previous element beforehand.
     * 3. We also need another pointer to store the next node before changing the
     * reference.
     * 4. Do not forget to return the new head reference at the end!
     */

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}
