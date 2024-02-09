package com.example.solution.Q501_Q1000;

import java.util.Stack;

public class P907_Sum_Subarray_Minimum {

    /**
     * Reference:
     * 
     * Explanation:
     * 1. Let's take an example and see how this might work: arr=[8,6,3,5,4,9,2]
     * 2. Let's consider all subarrays which end at the element 3. There are three
     * such subarrays - [8,6,3],[6,3],[3]
     * a. 3 is the smallest element here. So the sum contributed by all subarrays
     * ending at 3 is 3+3+3=9. Let's call it dp[2]=9, as 2 is the index of element
     * 3.
     * 3. Let's look at the next element, 5. Four subarrays end at 5. We can get
     * them by concatenating 5 at the end of the subarrays ending at the previous
     * element. One subarray will consist of 5 as the only element - [8,6,3],[6,3]
     * and [3] are clubbed with 5 to get [8,6,3,5],[6,3,5],[3,5] and one subarray
     * with 5 as the only element - [5]
     * The current element 5 is greater than the previous element 3. So we can see
     * that in all the subarrays made by concatenating 5 with previously seen
     * subarrays, 5 is not going to be the minimum element. There is only one
     * subarray where it is the minimum element, [5]. So 5 will contribute its value
     * to the summation dp[3] with just one subarray where it is the only element.
     * a. Subarray minimums' sum for subarray ending at index 3: dp[3] = dp[2] + 5 =
     * 9 + 5 = 14
     * 4. In other words, the following should be true for two elements at index i
     * and i+1
     * a. if arr[i+1] > arr[i], then dp[i+1] = dp[i] + arr[i+1]
     * 5. What if the number was anything less than 3? We can see a similar case
     * with the help of the next element at the index 4.
     * a. There are five subarrays that end at 4 -
     * [8,6,3,5,4],[6,3,5,4],[3,5,4],[5,4],[4]
     * b. The current element 4 is smaller than its previous element, 5. So, we walk
     * left from 4 to find the first element smaller than 4. We find 3 at index 2.
     * We can see that in the subarrays which start after 3, 4 is the minimum
     * element. There are two such subarrays - [5,4],[4]. The rest of them maintain
     * 3 as the minimum element
     * c. dp[4] = dp[2] + 2 ∗ arr[4]
     * d. dp[4] = 9 + 2 ∗ 4 = 17
     * e. The 2 above comes from the two subarrays [5,4] and [4], in other words,
     * the difference between indices 4 and 2
     * 6. Take any element i in the array arr. As we walk toward the left from i, we
     * look for the index j of the first element smaller than or equal to arr[i]. We
     * find i−j subarrays with arr[i] as the minimum element. For the rest, dp[j]
     * contains the answer, so we sum both the values to get dp[i].
     * a. dp[i] = dp[j] + (i−j) ∗ arr[i]
     * 7. For the elements which have no smaller element appearing before them in
     * the array
     * a. we can assume j to be −1 and dp[j] = 0
     * 
     * Strategy:
     * 1. Create a dp array of the same size as the input array arr. All
     * the elements are 0 by default.
     * 2. Initialize an empty stack stackstackstack, which will contain a
     * monotonically increasing stack.
     * 3. For each of the elements at index i in the array
     * a. Pop all the elements from the stack until the stack is empty or the top of
     * the stack is smaller than the current element.
     * b. There are two cases
     * b1. Stack is empty: This means the current element has no previous element
     * smaller than itself - dp[i] = (i + 1) * arr[i]
     * b2. Stack is not empty: The top of the stack represents the index of the
     * previous smaller element. Let's call it j - dp[i] = dp[j] + (i - j) * arr[i]
     * 4. At the end, we sum all the elements of the dpdpdp array to get the answer.
     */
    public int sumSubarrayMins(int[] arr) {
        int MOD = 1_000_000_007;
        Stack<Integer> stack = new Stack<>();

        // make a dp array of the same size as the input array `arr`
        int[] dp = new int[arr.length];

        // making a monotonic increasing stack
        for (int i = 0; i < arr.length; i++) {
            // pop the stack until it is empty or
            // the top of the stack is greater than or equal to
            // the current element
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            // either the previousSmaller element exists
            if (stack.size() > 0) {
                int previousSmaller = stack.peek();
                dp[i] = dp[previousSmaller] + (i - previousSmaller) * arr[i];
            } else {
                // or it doesn't exist, in this case the current element
                // contributes with all subarrays ending at i
                dp[i] = (i + 1) * arr[i];
            }
            // push the current index
            stack.push(i);
        }

        // Add all elements of the dp to get the answer
        long sumOfMinimums = 0;
        for (int count : dp) {
            sumOfMinimums += count;
            sumOfMinimums %= MOD;
        }

        return (int) sumOfMinimums;
    }
}
