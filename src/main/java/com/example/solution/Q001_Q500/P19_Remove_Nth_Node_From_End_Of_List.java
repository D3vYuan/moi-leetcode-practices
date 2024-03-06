package com.example.solution.Q001_Q500;

import com.example.utility.ListNode;

public class P19_Remove_Nth_Node_From_End_Of_List {

    /**
     * Reference:
     * https://leetcode.com/problems/remove-nth-node-from-end-of-list/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. First we will add an auxiliary "dummy" node, which points to the list
     * head.
     * 2. The "dummy" node is used to simplify some corner cases such as
     * a. a list with only one node,
     * b. removing the head of the list.
     * 3. On the first pass, we find the list length L.
     * 4. Then we set a pointer to the dummy node and start to move it through the
     * list till it comes to the (L−n)th node.
     * 5. We relink next pointer of the (L−n)th node to the (L−n+2)th node and we
     * are done.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }
        length -= n;
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }
}
