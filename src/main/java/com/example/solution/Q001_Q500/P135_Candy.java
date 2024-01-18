package com.example.solution.Q001_Q500;

import java.util.Arrays;

public class P135_Candy {
    /**
     * Reference:
     * https://leetcode.com/problems/candy/editorial/?envType=daily-question&envId=2024-01-17
     * 
     * Strategy:
     * 1. we make use of two 1-d arrays left2right and right2left
     * a. The left2right array is used to store the number of candies required by
     * the current student taking care of the distribution relative to the left
     * neighbors only. i.e. Assuming the distribution rule is: The student with a
     * higher rating than their left neighbor should always get more candies than
     * its left neighbor
     * b. the right2left array is used to store the number of candies required by
     * the current student taking care of the distribution relative to the right
     * neighbors only. i.e. Assuming the distribution rule to be: The student with a
     * higher rating than their right neighbor should always get more candies than
     * their right neighbor
     * 2. Assign 1 candy to each student in both the left2right and right2left
     * arrays.
     * 3. Traverse the array from left-to-right and whenever the current element's
     * ratings is larger than the left neighbor we update the current element's
     * candies in the left2right array as left2right[i] = left2right[i-1] + 1, since
     * the current element's candies are always less than or equal candies than its
     * left neighbor before updating.
     * 4. After the forward traversal, we traverse the array from right-to-left and
     * update right2left[i] as right2left[i] = right2left[i + 1] + 1, whenever the
     * current i'th element has a higher ratings than the right i+1'th element.
     * 5. Now, for the i'th student in the array, we need to give max(left2right[i],
     * right2left[i]) to them, in order to satisfy both the left and the right
     * neighbor relationship
     */
    public int candy(int[] ratings) {
        int sum = 0;
        int[] left2right = new int[ratings.length];
        int[] right2left = new int[ratings.length];
        Arrays.fill(left2right, 1);
        Arrays.fill(right2left, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left2right[i] = left2right[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right2left[i] = right2left[i + 1] + 1;
            }
        }
        for (int i = 0; i < ratings.length; i++) {
            sum += Math.max(left2right[i], right2left[i]);
        }
        return sum;
    }
}
