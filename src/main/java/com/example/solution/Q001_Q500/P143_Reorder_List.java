package com.example.solution.Q001_Q500;

import com.example.utility.ListNode;

public class P143_Reorder_List {
    /**
     * Reference:
     * https://leetcode.com/problems/reorder-list/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Find a middle node of the linked list.
     * If there are two middle nodes, return the second middle node.
     * Example: for the list 1->2->3->4->5->6, the middle element is 4.
     * 
     * 2. Once a middle node has been found, reverse the second part of the list.
     * Example: convert 1->2->3->4->5->6 into 1->2->3->4 and 6->5->4.
     * 
     * 3. Now merge the two sorted lists.
     * Example: merge 1->2->3->4 and 6->5->4 into 1->6->2->5->3->4.
     */
    public void reorderList(ListNode head) {
        if (head == null)
            return;

        // find the middle of linked list [Problem 876]
        // in 1->2->3->4->5->6 find 4
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the second part of the list [Problem 206]
        // convert 1->2->3->4->5->6 into 1->2->3->4 and 6->5->4
        // reverse the second half in-place
        ListNode prev = null, curr = slow, tmp;
        while (curr != null) {
            tmp = curr.next;

            curr.next = prev;
            prev = curr;
            curr = tmp;
        }

        // merge two sorted linked lists [Problem 21]
        // merge 1->2->3->4 and 6->5->4 into 1->6->2->5->3->4
        ListNode first = head, second = prev;
        while (second.next != null) {
            tmp = first.next;
            first.next = second;
            first = tmp;

            tmp = second.next;
            second.next = first;
            second = tmp;
        }
    }
}
