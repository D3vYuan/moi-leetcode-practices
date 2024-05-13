package com.example.solution.Q1001_Q1500;

import java.util.HashMap;
import java.util.Map;

import com.example.utility.ListNode;

public class P1171_Remove_Zero_Sum_Consecutive_Nodes {
    /**
     * Reference:
     * https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialization:
     * a. Initialize a new ListNode front with the value 0 whose next field points
     * to head and a node current to front.
     * b. Initialize a variable prefixSum to 0 and a hashmap prefixSumToNode, which
     * stores integer, ListNode pairs. The key is the prefix sum, and the value is
     * the corresponding ListNode. Add front to the hashmap.
     * 2. Process all of the nodes in the linked list, while current != null:
     * a. Add current's value to prefixSum.
     * b. Add the prefix sum and node pair to the prefixSumToNode hashmap.
     * c. Set current to current.next.
     * 3. Reset prefixSum to 0 and current to front.
     * 4. Process all of the nodes in the linked list, while current != null:
     * a. Add current's value to prefixSum.
     * b. Make a connection from current to the last node after the zero-sum
     * consecutive sequence by setting current.next to
     * prefixSumToNode[prefixSum].next.
     * c. Set current to current.next.
     * 5. Return front.next. The front points to the head of the final linked list.
     */
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode front = new ListNode(0, head);
        ListNode current = front;
        int prefixSum = 0;
        Map<Integer, ListNode> prefixSumToNode = new HashMap<>();
        while (current != null) {
            // Add current's value to the prefix sum
            prefixSum += current.val;

            // If prefixSum is already in the hashmap,
            // we have found a zero-sum sequence:
            if (prefixSumToNode.containsKey(prefixSum)) {
                ListNode prev = prefixSumToNode.get(prefixSum);
                current = prev.next;

                // Delete zero sum nodes from hashmap
                // to prevent incorrect deletions from linked list
                int p = prefixSum + current.val;
                while (p != prefixSum) {
                    prefixSumToNode.remove(p);
                    current = current.next;
                    p += current.val;
                }

                // Make connection from the node before
                // the zero sum sequence to the node after
                prev.next = current.next;
            } else {
                // Add new prefixSum to hashmap
                prefixSumToNode.put(prefixSum, current);
            }
            // Progress to next element in list
            current = current.next;
        }
        return front.next;
    }
}
