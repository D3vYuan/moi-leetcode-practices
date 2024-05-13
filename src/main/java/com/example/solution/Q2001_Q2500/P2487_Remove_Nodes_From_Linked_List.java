package com.example.solution.Q2001_Q2500;

import java.util.Stack;

import com.example.utility.ListNode;

public class P2487_Remove_Nodes_From_Linked_List {
    /**
     * Reference:
     * https://leetcode.com/problems/remove-nodes-from-linked-list/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize an empty stack to be used for reversing the nodes.
     * 2. Set a pointer current to head.
     * 3. While current is not Null:
     * a. Add current to the stack.
     * b. Set current to current.next.
     * 4. Pop the node from the top of the stack and set current to that node.
     * 5. Initialize a variable maximum to current.val.
     * 6. Create a new ListNode resultList with maximum as its value.
     * 7. While the stack is not empty:
     * a. Pop the node from the top of the stack and set current to that node.
     * b. If current.val < maximum:
     * b1. Continue; this node does not need to be added to the resultList.
     * c. Otherwise, add a new node to the front of the resultList:
     * c1. Create a new ListNode newNode with current.val as its value.
     * c2. Set newNode.next to resultList.
     * c3. Set resultList to newNode.
     * c4. Update maximum to current.val.
     * 8. Return resultList.
     * 
     */
    public ListNode removeNodes(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode current = head;

        // Add nodes to the stack
        while (current != null) {
            stack.push(current);
            current = current.next;
        }

        current = stack.pop();
        int maximum = current.val;
        ListNode resultList = new ListNode(maximum);

        // Remove nodes from the stack and add to result
        while (!stack.isEmpty()) {
            current = stack.pop();
            // Current should not be added to the result
            if (current.val < maximum) {
                continue;
            }
            // Add new node with current's value to front of the result
            else {
                ListNode newNode = new ListNode(current.val);
                newNode.next = resultList;
                resultList = newNode;
                maximum = current.val;
            }
        }

        return resultList;
    }
}
