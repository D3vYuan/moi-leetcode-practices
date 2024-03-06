package com.example.katana.Q001_Q500;

import com.example.utility.ListNode;

public class P141_Linked_List_Cycle {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slowPointer = head.next;
        ListNode fastPointer = head.next == null ? null : head.next.next;

        while (slowPointer != null && fastPointer != null) {
            System.out.println(String.format("Slow: %d | Fast: %d | Same: %s", slowPointer.val, fastPointer.val,
                    slowPointer == fastPointer));
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next == null ? null : fastPointer.next.next;

            if (slowPointer == fastPointer) {
                return true;
            }
        }

        return slowPointer == null || fastPointer == null ? false : true;
    }
}
