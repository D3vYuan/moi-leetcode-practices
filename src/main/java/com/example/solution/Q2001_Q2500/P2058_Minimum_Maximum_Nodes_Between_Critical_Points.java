package com.example.solution.Q2001_Q2500;

import com.example.utility.ListNode;

public class P2058_Minimum_Maximum_Nodes_Between_Critical_Points {
    /**
     * Reference:
     * https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize:
     * a. The result array to [-1, -1], in case there is no valid solution.
     * b. minDistance to the maximum permissible integer value.
     * c. previousNode to point at head.
     * d. currentNode to point at the next node from head.
     * e. currentIndex storing the position of currentNode.
     * f. previousCriticalIndex and firstCriticalIndex set to 0.
     * 
     * 2. Loop over the list till the second-last element:
     * a. If the current node is a critical point:
     * If it is the first critical point encountered:
     * Set previousCriticalIndex and firstCriticalIndex to the position of the
     * current node.
     * Else, update minDistance as the minimum of the current minDistance and
     * difference between currentIndex and previousCriticalIndex.
     * c. Increment currentIndex. Move previousNode to the current node and
     * currentNode to the next node in the list.
     * 
     * 3. If minDistance is not equal to its initial value:
     * a. Set maxDistance to the difference between previousCriticalIndex and
     * firstCriticalIndex.
     * b. Update result with minDistance and maxDistance.
     * 
     * 4. Return result.
     */

    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] result = { -1, -1 };

        // Initialize minimum distance to the maximum possible value
        int minDistance = Integer.MAX_VALUE;

        // Pointers to track the previous node, current node, and indices
        ListNode previousNode = head;
        ListNode currentNode = head.next;
        int currentIndex = 1;
        int previousCriticalIndex = 0;
        int firstCriticalIndex = 0;

        while (currentNode.next != null) {
            // Check if the current node is a local maxima or minima
            if ((currentNode.val < previousNode.val &&
                    currentNode.val < currentNode.next.val) ||
                    (currentNode.val > previousNode.val &&
                            currentNode.val > currentNode.next.val)) {
                // If this is the first critical point found
                if (previousCriticalIndex == 0) {
                    previousCriticalIndex = currentIndex;
                    firstCriticalIndex = currentIndex;
                } else {
                    // Calculate the minimum distance between critical points
                    minDistance = Math.min(
                            minDistance,
                            currentIndex - previousCriticalIndex);
                    previousCriticalIndex = currentIndex;
                }
            }

            // Move to the next node and update indices
            currentIndex++;
            previousNode = currentNode;
            currentNode = currentNode.next;
        }

        // If at least two critical points were found
        if (minDistance != Integer.MAX_VALUE) {
            int maxDistance = previousCriticalIndex - firstCriticalIndex;
            result = new int[] { minDistance, maxDistance };
        }

        return result;
    }
}
