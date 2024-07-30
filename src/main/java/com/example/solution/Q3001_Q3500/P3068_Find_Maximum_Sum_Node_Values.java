package com.example.solution.Q3001_Q3500;

import java.util.Arrays;

public class P3068_Find_Maximum_Sum_Node_Values {

    /**
     * Reference:
     * https://leetcode.com/problems/find-the-maximum-sum-of-node-values/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Explanation:
     * 1. Bitwise XOR operation is commutative and associative. That means
     * aXORbXORb=aa
     * XOR b XOR b = aaXORbXORb=a, and aXORb=bXORaa XOR b = b XOR aaXORb=bXORa.
     * Hence, the order of applying XOR operations doesn't matter. XORing a number
     * with itself (aXORaa XOR aaXORa) results in 000. Therefore, performing the XOR
     * operation twice on the same number yields the original number.
     * 2. The base case occurs when we have traversed through all the nodes of the
     * tree. If the number of nodes on which we have performed the operation is
     * even, we return 0. Otherwise, we return INT_MIN (minimum integer value).
     * 3. We also need to include the parity of the number of elements on which the
     * operation has been performed as a parameter in the recursive solution. If the
     * number of operated elements is even, it is a valid assignment. Parity of a
     * number refers to whether it contains an odd or even number of 1-bits.
     * 4. The two choices that we have here for every node are to perform an
     * operation on it or not. The recursive calls for each case can be explained
     * as:
     * a. If we perform the operation on the node at the position index, then the
     * value of this node would be modified to nums[index] XOR k. Since we are
     * operating on a node, the parity of the total number of elements on which the
     * XOR operation has been performed will be flipped. Therefore, even parity
     * flips to odd, and vice versa. To obtain the answer for this case, we will
     * store the sum of nums[index] XOR k and the subsequent recursive function call
     * for the next node at index+1 and the flipped parity (denoted by isEven XOR
     * 1).
     * b. If we do not perform the operation on the node at the position index, then
     * the value of this node would remain the same. The parity of the total number
     * of elements on which the operation is performed will remain the same. To
     * obtain the answer for this case, we will store the sum of nums[index] and the
     * subsequent recursive function call for the next node at index+1 and the given
     * parity.
     * 5. Since we want to maximize the sum of all nodes, we will return the maximum
     * value of both the cases discussed above.
     * 
     * 
     * Strategy:
     * Main Function: maximumValueSum(nums, k, edges)
     * 1. Initialize a 2D memoization array memo with all values set to -1.
     * 2. Call the helper function maxSumOfNodes with the initial parameters:
     * a. index = 0
     * b. isEven = 1 (start with an odd number of elements)
     * c. nums = the input array
     * d. k = the given XOR value
     * e. memo = the initialized memoization array
     * 3. Return the result from the maxSumOfNodes function.
     * 
     * Recursive Function: maxSumOfNodes(index, isEven, nums, k, memo)
     * 1. If the index is equal to the size of the nums array, return:
     * a. If isEven is 1, return 0 (no operation performed on an odd number of
     * elements).
     * b. Else, return INT_MIN.
     * 2. If the result for the current index and isEven is already memoized, return
     * the memoized value.
     * 3. Calculate the maximum sum of nodes in two cases:
     * a. noXorDone: No XOR operation is performed on the current element.
     * a1. The sum is the current element value nums[index] plus the maximum sum of
     * the remaining elements.
     * b. xorDone: The XOR operation is performed on the current element.
     * b1. The sum is the current element value nums[index] ^ k plus the maximum sum
     * of the remaining elements with isEven flipped.
     * 4. Memoize the maximum of noXorDone and xorDone, and return the result.
     */

    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        long[][] memo = new long[nums.length][2];
        for (long[] row : memo) {
            Arrays.fill(row, -1);
        }
        return maxSumOfNodes(0, 1, nums, k, memo);
    }

    private long maxSumOfNodes(int index, int isEven, int[] nums, int k,
            long[][] memo) {
        if (index == nums.length) {
            // If the operation is performed on an odd number of elements return
            // INT_MIN
            return isEven == 1 ? 0 : Integer.MIN_VALUE;
        }
        if (memo[index][isEven] != -1) {
            return memo[index][isEven];
        }
        // No operation performed on the element
        long noXorDone = nums[index] + maxSumOfNodes(index + 1, isEven, nums, k, memo);
        // XOR operation is performed on the element
        long xorDone = (nums[index] ^ k) +
                maxSumOfNodes(index + 1, isEven ^ 1, nums, k, memo);

        // Memoize and return the result
        return memo[index][isEven] = Math.max(xorDone, noXorDone);
    }
}
