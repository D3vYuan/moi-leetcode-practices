package com.example.solution.Q1001_Q1500;

public class P1442_Count_Triplets_Form_Two_Arrays_Equal_XOR {
    /**
     * Reference:
     * https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a result variable count to 0 to store the count of valid
     * triplets.
     * 2. Iterate over each possible starting index start.
     * a. Initialize xorA to 0 (XOR value for the subarray from start to mid - 1)
     * b. Iterate over each possible middle index mid.
     * b1. Update xorA by XORing it with arr[mid - 1] (including the element at mid
     * - 1 in the XOR computation).
     * b2. Initialize xorB to 0 (XOR value for the subarray from mid to end).
     * b3. Iterate over each possible ending index end, starting from mid.
     * b4. Update xorB by XORing it with arr[end].
     * b5. If we find a valid triplet where the XOR of the first subarray is equal
     * to the XOR of the second subarray(xorA == xorB), increment the count of valid
     * triplets count.
     * 3. Return the final count of valid triplets count.
     */

    public int countTriplets(int[] arr) {
        int count = 0;

        // Iterate over each possible starting index i
        for (int start = 0; start < arr.length - 1; ++start) {
            // Initialize XOR value for the subarray from start to mid-1
            int xorA = 0;

            // Iterate over each possible middle index j
            for (int mid = start + 1; mid < arr.length; ++mid) {
                // Update xorA to include arr[mid - 1]
                xorA ^= arr[mid - 1];

                // Initialize XOR value for the subarray from mid to end
                int xorB = 0;

                // Iterate over each possible ending index k (starting from mid)
                for (int end = mid; end < arr.length; ++end) {
                    // Update xorB to include arr[end]
                    xorB ^= arr[end];

                    // found a valid triplet (start, mid, end)
                    if (xorA == xorB) {
                        ++count;
                    }
                }
            }
        }

        return count;
    }
}
