package com.example.solution;

import java.util.HashMap;
import java.util.Map;

public class P2870_Minimum_Operations_Make_Array_Empty {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-number-of-operations-to-make-array-empty/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation:
     * 1. presents two distinct operations that can be applied repeatedly:
     * a. the removal of two elements with equal values o
     * b. the removal of three elements with equal values2
     * 2. we must find the frequency count of each element. To get the count of each
     * element, we could create a counter counter to tally the occurrences of each
     * unique element in the array
     * 3. The first critical insight arises when considering elements with a count
     * of 1 in the array. We must return -1 immediately in such cases, as the
     * removal of elements requires pairs or triplets, and a solitary element cannot
     * satisfy this criterion.
     * 4. To make sure we empty the array in the minimum number of operations, we
     * need to make sure we are removing the maximum possible elements in each
     * operation. That means we need to remove triplets whenever possible. Triplets
     * get priority over pairs.
     * a. The first conclusion that we can draw is that whenever the count of an
     * element is a multiple of 3, it will take us count / 3 operations to remove
     * the elements of that kind from the array.
     * b. when the count of an element is one more than a multiple of 3. Example: 4,
     * 7, 10, 13,... In such instances, we can eliminate two pairs, thereby making
     * the count divisible by 3. Following this adjustment, we can proceed to remove
     * the remaining numbers in triplets.
     * c. when the count of an element is two more than a multiple of 3. Example: 5,
     * 8, 11, 14,... In such instances, we can eliminate one pair, thereby making
     * the count divisible by 3. Following this adjustment, we can proceed to remove
     * the remaining numbers in triplets.
     * 5. we can deduce that the number of operations needed to remove a total of
     * count elements of a given kind is represented by the expression ceil(count /
     * 3), where the ceil method rounds up the decimal result of count / 3.
     * a. Except in the scenario where the count of the element is 1, making it
     * impossible to remove elements of that kind, in which case we should return
     * -1.
     * 
     * Strategy:
     * 1. Create a hashmap object named counter to count the occurrences of each
     * element in the given array nums. Initialize a variable ans = 0 to keep track
     * of the minimum number of operations required.
     * 2. For each value c in the counter's values:
     * a. Check if c is equal to 1. If yes, return -1, as it is not possible to
     * perform the required operations on a single element.
     * b. Else increment the answer ans by the ceiling division of c by 3.
     * 3. After iterating through all counts in the Counter, return the final value
     * of ans as the minimum number of operations required to empty the array.
     */
    public int minOperations(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }

        int ans = 0;
        for (Integer occurrences : counter.values()) {
            if (occurrences == 1) {
                return -1;
            }
            ans += Math.ceil((double) occurrences / 3);
        }
        return ans;
    }
}
