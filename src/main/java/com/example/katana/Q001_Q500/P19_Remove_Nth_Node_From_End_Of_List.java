package com.example.katana.Q001_Q500;

import com.example.utility.ListNode;

public class P19_Remove_Nth_Node_From_End_Of_List {
    ListNode connectNode;

    private int transverse(ListNode node, int n) {
        if (node == null) {
            return 0;
        }

        int currentNodeCount = 1 + transverse(node.next, n);
        System.out.println(String.format("Current Node: %s", currentNodeCount));
        if (currentNodeCount == n) {
            connectNode = node.next;
        } else if (currentNodeCount == n + 1) {
            node.next = connectNode;
        }

        return currentNodeCount;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int totalNode = transverse(head, n);
        System.out.println(String.format("Total: %s node", totalNode));
        return totalNode == n ? head.next : head;
    }
}
