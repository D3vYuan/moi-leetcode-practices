package com.example.solution.Q001_Q500;

import com.example.utility.ListNode;

public class P234_Palindrome_Linked_List {
    /**
     * Reference:
     * 
     * Strategy:
     * 1. Find the end of the first half.
     * To do step 1, we could count the number of nodes, calculate how many nodes
     * are in the first half, and then iterate back down the list to find the end of
     * the first half. Or, we could do it in a single parse using the two runners
     * pointer technique. Either is acceptable, however we'll have a look at the two
     * runners pointer technique here.
     * 
     * 2. Reverse the second half.
     * 
     * 3. Determine whether or not there is a palindrome.
     * Remember that we have the first half, which might also contain a "middle"
     * node at the end, and the second half, which is reversed. We can step down the
     * lists simultaneously ensuring the node values are equal. When the node we're
     * up to in the second list is null, we know we're done. If there was a middle
     * value attached to the end of the first list, it is correctly ignored by the
     * algorithm. The result should be saved, but not returned, as we still need to
     * restore the list.
     * 
     * 4. Restore the list.
     * 
     * 5. Return the result.
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null)
            return true;

        // Find the end of first half and reverse second half.
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // Check whether or not there is a palindrome.
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val)
                result = false;
            p1 = p1.next;
            p2 = p2.next;
        }

        // Restore the list and return the result.
        firstHalfEnd.next =

                reverseList(secondHalfStart);
        return result;
    }

    // Taken from https://leetcode.com/problems/reverse-linked-list/solution/
    private ListNode reverseList(ListNode head) {
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

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
