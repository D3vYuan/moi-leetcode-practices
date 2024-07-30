package com.example.solution.Q1001_Q1500;

public class P1404_Number_Of_Steps_To_Reduce_Binary_Representation_To_One {
    /**
     * Reference:
     * https://leetcode.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Explanation:
     * 1. we're essentially removing one bit from the right end each time. When the
     * number is even we are directly removing the bit at the rightmost position. In
     * case of an odd number, adding one will make it even, and then we will remove
     * the rightmost bit. Hence, it takes one step to remove the rightmost bit when
     * the number is even, and it takes two steps when the number is odd.
     * 2. For each bit, we will check if it's 1 or 0, i.e., odd or even,
     * respectively. If it's odd, we will add 2 to the answer operations, and if
     * it's even, add 1 to operations.
     * 
     * Strategy:
     * 1. Initialize the variable operations and carry to 0.
     * 2. Iterate over the characters from position N - 1 to 1 in the string s and
     * for each index i, do the following:
     * a. If the bit ((s[i] - '0') + carry) is odd, increment the operations by 2
     * and change carry to 1.
     * b. Else, add 1 to operations
     * 3. Return operations + carry.
     */

    public int numSteps(String s) {
        int N = s.length();

        int operations = 0;
        int carry = 0;
        for (int i = N - 1; i > 0; i--) {
            int digit = Character.getNumericValue(s.charAt(i)) + carry;

            if (digit % 2 == 1) {
                operations += 2;
                carry = 1;
            } else {
                operations++;
            }
        }

        return operations + carry;
    }
}
