package com.example.solution.Q2001_Q2500;

public class P2485_Find_Pivot_Integer {
    /**
     * Reference:
     * https://leetcode.com/problems/find-the-pivot-integer/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize leftValue and rightValue to 1 and n, respectively, and sumLeft
     * and sumRight to leftValue and rightValue, respectively.
     * 2. If n is 1, return n as it is already a valid pivot.
     * 3. Enter a while loop that continues while leftValue is less than rightValue.
     * a. Check if sumLeft is less than sumRight. If true, increment leftValue by 1
     * and add the new value to sumLeft.
     * b. If false, decrement rightValue by 1 and add the new value to sumRight.
     * c. After adjusting the pointers and sums, check if sumLeft is equal to
     * sumRight and if the pointers are next to each other (leftValue + 1 ==
     * rightValue - 1). If this condition is met, it means that leftValue + 1 is a
     * valid pivot; thus, return this value.
     * 4. If the loop exits without finding a pivot, return -1 to indicate that no
     * valid pivot was found.
     */

    public int pivotInteger(int n) {
        int leftValue = 1;
        int rightValue = n;
        int sumLeft = leftValue;
        int sumRight = rightValue;

        if (n == 1)
            return n;

        // Iterate until the pointers meet
        while (leftValue < rightValue) {
            // Adjust sums and pointers based on comparison
            if (sumLeft < sumRight) {
                sumLeft += ++leftValue;
            } else {
                sumRight += --rightValue;
            }

            // Check for pivot condition
            if (sumLeft == sumRight && leftValue + 1 == rightValue - 1) {
                return leftValue + 1;
            }
        }

        return -1; // Return -1 if no pivot is found
    }
}
