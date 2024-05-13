package com.example.solution.Q1501_Q2000;

import com.example.utility.ListNode;

public class P1669_Merge_In_Between_Linked_List {
    /**
     * Reference:
     * https://leetcode.com/problems/merge-in-between-linked-lists/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize two ListNodes, start to null and end to list1.
     * 2. Find the nodes at index a - 1 and b of list1. Traverse through list1 using
     * a for loop with the iterator index from 0 to b - 1:
     * a. If index equals a - 1 set start to end.
     * b. Progress to the next node in list1 by setting end to end.next.
     * 3. Set start.next to list2.
     * 4. Find the tail of list2 by traversing the list with list2 = list2.next
     * until the last node is reached.
     * 5. Set list2.next to end.next and set end.next to null. Note that the order
     * of the statements is important.
     * 6. Return list1, which points to the head of the resultant linked list.
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode start = null;
        ListNode end = list1;

        // Set start to node a - 1 and end to node b
        for (int index = 0; index < b; index++) {
            if (index == a - 1) {
                start = end;
            }
            end = end.next;
        }
        // Connect the start node to list2
        start.next = list2;

        // Find the tail of list2
        while (list2.next != null) {
            list2 = list2.next;
        }
        // Set the tail of list2 to end.next
        list2.next = end.next;
        end.next = null;

        return list1;
    }
}
