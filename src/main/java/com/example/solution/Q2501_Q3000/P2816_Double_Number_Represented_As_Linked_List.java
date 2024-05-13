package com.example.solution.Q2501_Q3000;

import java.util.Stack;

import com.example.utility.ListNode;

public class P2816_Double_Number_Represented_As_Linked_List {
    /**
     * Reference:
     * 
     * Strategy:
     * 1. Initialize an empty stack values to store the values of the linked list
     * nodes.
     * 2. Initialize a variable val to hold the carryover value when doubling
     * digits.
     * 3. Traverse the linked list and push the values of the nodes onto the stack.
     * 4. Initialize the tail of the new linked list as null.
     * 5. Iterate over the stack of values:
     * a. Create a new ListNode with value 0 and the previous tail as its next node.
     * b. If the stack is not empty, pop the top value, double it, and add it to the
     * val.
     * c. Set the value of the new node to the units digit of the new value.
     * d. Update the val to hold the carryover value for the next iteration.
     * 6. Return the tail of the new linked list.
     */
    public ListNode doubleIt(ListNode head) {
        // Initialize a stack to store the values of the linked list
        Stack<Integer> values = new Stack<>();
        int val = 0;

        // Traverse the linked list and push its values onto the stack
        while (head != null) {
            values.push(head.val);
            head = head.next;
        }

        ListNode newTail = null;

        // Iterate over the stack of values and the carryover
        while (!values.isEmpty() || val != 0) {
            // Create a new ListNode with value 0 and the previous tail as its next node
            newTail = new ListNode(0, newTail);

            // Calculate the new value for the current node
            // by doubling the last digit, adding carry, and getting the remainder
            if (!values.isEmpty()) {
                val += values.pop() * 2;
            }
            newTail.val = val % 10;
            val /= 10;
        }

        // Return the tail of the new linked list
        return newTail;
    }
}
