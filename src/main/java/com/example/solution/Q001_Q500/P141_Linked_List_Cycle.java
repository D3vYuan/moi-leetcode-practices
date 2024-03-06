package com.example.solution.Q001_Q500;

import com.example.utility.ListNode;

public class P141_Linked_List_Cycle {
    /**
     * Reference:
     * https://leetcode.com/problems/linked-list-cycle/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. The space complexity can be reduced to O(1) by considering two pointers at
     * different speed - a slow pointer and a fast pointer. The slow pointer moves
     * one step at a time while the fast pointer moves two steps at a time.
     * 2. If there is no cycle in the list, the fast pointer will eventually reach
     * the end and we can return false in this case.
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
