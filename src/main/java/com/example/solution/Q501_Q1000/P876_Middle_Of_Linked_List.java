package com.example.solution.Q501_Q1000;

import com.example.utility.ListNode;

public class P876_Middle_Of_Linked_List {
    /**
     * Reference:
     * https://leetcode.com/problems/middle-of-the-linked-list/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. When traversing the list with a pointer slow, make another pointer fast
     * that traverses twice as fast.
     * 2. When fast reaches the end of the list, slow must be in the middle.
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
