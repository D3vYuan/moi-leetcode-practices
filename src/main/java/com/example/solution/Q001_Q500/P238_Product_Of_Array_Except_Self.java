package com.example.solution.Q001_Q500;

public class P238_Product_Of_Array_Except_Self {
    /**
     * Reference:
     * https://leetcode.com/problems/product-of-array-except-self/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize the empty answer array where for a given index i, answer[i]
     * would contain the product of all the numbers to the left of i.
     * 2. We construct the answer array the same way we constructed the L array in
     * the previous approach. These two algorithms are exactly the same except that
     * we are trying to save up on space.
     * 3. The only change in this approach is that we don't explicitly build the R
     * array from before. Instead, we simply use a variable to keep track of the
     * running product of elements to the right and we keep updating the answer
     * array by doing answer[i]=answer[i]∗R. For a given index i, answer[i] contains
     * the product of all the elements to the left and R would contain product of
     * all the elements to the right. We then update R as R=R∗nums[i]
     */
    public int[] productExceptSelf(int[] nums) {
        // The length of the input array
        int length = nums.length;

        // Final answer array to be returned
        int[] answer = new int[length];

        // answer[i] contains the product of all the elements to the left
        // Note: for the element at index '0', there are no elements to the left,
        // so the answer[0] would be 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {

            // answer[i - 1] already contains the product of elements to the left of 'i - 1'
            // Simply multiplying it with nums[i - 1] would give the product of all
            // elements to the left of index 'i'
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        // R contains the product of all the elements to the right
        // Note: for the element at index 'length - 1', there are no elements to the
        // right,
        // so the R would be 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {

            // For the index 'i', R would contain the
            // product of all elements to the right. We update R accordingly
            answer[i] = answer[i] * R;
            R *= nums[i];
        }

        return answer;
    }
}
