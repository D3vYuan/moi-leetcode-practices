package com.example.solution;

public class P1422_Maximum_Score_After_String {
    /**
     * Reference:
     * https://leetcode.com/problems/maximum-score-after-splitting-a-string/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation:
     * 1. The answer to our problem is the maximum score for all valid splits, as
     * represented by the following equation: score=ZL + OR, where ZL is the number
     * of zeros in the left substring and OR is the number of ones in the right
     * substring.
     * 2. We can express OR as OT − OL, where OT is the total number of ones in s,
     * and OL is the number of ones in the left substring.
     * 3. Using the above expression, our first equation can be represented as:
     * score = ZL + OT − OL
     * 4. In the above equation, OT is a constant, we need to find the maximum value
     * of ZL − OL for all valid splits. Notice that both of these values depend
     * solely on the left substring. Therefore, we don't need to consider the right
     * substring, which saves the need for the first traversal in the previous
     * solution.
     * 5. We will use the variable zeros to represent ZL and the variables ones to
     * represent OL. As zeros - ones may be negative, we initialize an integer best
     * to a very small value, like negative infinity. Here, best represents the
     * largest value of zeros - ones we have seen so far.
     * 6. We now iterate i in the same manner as the first two approaches: at each
     * iteration, i represents the final index of the left part. On each iteration,
     * we are adding s[i] to the left part. Thus, if s[i] = '1' we increment ones,
     * otherwise s[i] = '0' and we increment zeros. Then, we update best with zeros
     * - ones if it is larger.
     * 7. We don't iterate i over the final index since it would mean having an
     * empty right part. Once we are done iterating over s, we will check the final
     * index to see if it is a 1. If it is, we increment ones.
     * 8. The reason we explicitly check the final index for 1 is that we want ones
     * to represent OT in the end, but when we calculate ones, we don't iterate over
     * the last index, so we need to account for it. Now, we have best as the
     * maximum of all ZL−OL and ones represents OT, we can return best + ones as the
     * answer.
     * 
     * Strategy:
     * 1. Initialize ones = 0, zeros = 0, and best to a very small value like
     * negative infinity.
     * 2. Iterate i from 0 until s.length - 1:
     * a. If s[i] == '1', increment ones.
     * b. Otherwise, increment zeros.
     * c. Update best with zeros - ones if it is larger.
     * 3. If the final character of s is equal to '1', increment ones.
     * 4. Return best + ones.
     */
    public int maxScore(String s) {
        int ones = 0;
        int zeros = 0;
        int best = Integer.MIN_VALUE;

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '1') {
                ones++;
            } else {
                zeros++;
            }
            best = Math.max(best, zeros - ones);
        }

        // Last Element for the Right Array (Non-Empty Array)
        if (s.charAt(s.length() - 1) == '1') {
            ones++;
        }

        return best + ones;
    }
}
