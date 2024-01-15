package com.example.katana;

public class P446_Arithmetic_Slices_2 {
    /**
     * Reference:
     * 1.
     * https://leetcode.com/problems/arithmetic-slices-ii-subsequence/editorial/?envType=daily-question&envId=2023-11-13
     * 2. https://www.youtube.com/watch?v=YIMwwT9JdIE
     * 
     * Explanation:
     * 1. To determine an arithmetic sequence, we need at least two parameters: the
     * first (or last) element of the sequence, and the common difference.
     * 2. Assume we want to append a new element A[i] to existing arithmetic
     * subsequences to form new subsequences. We can append A[i] to an existing
     * arithmetic subsequence, only if the difference between the sequence's last
     * element and A[i] is equal to the sequence's common difference.
     * 3. Thus, we can define the state transitions for the element A[i] intuitively
     * : for all j < i, f[i][A[i] - A[j]] += f[j][A[i] - A[j]].
     * 4. weak arithmetic subsequences: Weak arithmetic subsequences are
     * subsequences that consist of at least two elements and if the difference
     * between any two consecutive elements is the same
     * 5. There are two properties of weak arithmetic subsequences that are very
     * useful:
     * a. For any pair i, j (i != j), A[i] and A[j] can always form a weak
     * arithmetic subsequence.
     * b. If we can append a new element to a weak arithmetic subsequence and keep
     * it arithmetic, then the new subsequence must be an arithmetic subsequence.
     * 6. we can change the state representations accordingly: f[i][d] denotes the
     * number of weak arithmetic subsequences that ends with A[i] and its common
     * difference is d.
     * 7. Now the state transitions are quite straightforward: for all j < i,
     * f[i][A[i] - A[j]] += (f[j][A[i] - A[j]] + 1).
     * 8. How can we get the number of arithmetic subsequences that are not weak?
     * a. First, we can count the number of pure weak arithmetic subsequences
     * directly. The pure weak arithmetic subsequences are the arithmetic
     * subsequences of length 2, so the number of pure weak arithmetic subsequences
     * should be equal to the number of pairs (i, j), which is binom{n}{2}=(n∗(n−1))
     * b. Second, for the summation f[i][A[i] - A[j]] += (f[j][A[i] - A[j]] + 1),
     * f[j][A[i] - A[j]] is the number of existing weak arithmetic subsequences,
     * while 1 is the new subsequence built with A[i] and A[j].
     * 
     * Stategy:
     * Assume the following example - [1, 1, 2, 3, 4, 5]
     * 1. For the first element 1, there is no element in front of it, the answer
     * remains 0.
     * 2. For the second element 1, the element itself with the previous 1 can form
     * a pure weak arithmetic subsequence with common difference 0 : [1, 1].
     * 3. For the third element 2, it cannot be appended to the only weak arithmetic
     * subsequence [1, 1], so the answer remains 0. Similar to the second element,
     * it can form new weak arithmetic subsequences [1, 2] and [1, 2].
     * 4. For the forth element 3, if we append it to some arithmetic subsequences
     * ending with 2, these subsequences must have a common difference of 3 - 2 = 1.
     * Indeed there are two: [1, 2] and [1, 2]. So we can append 3 to the end of
     * these subsequences, and the answer is added by 2. Similar to above, it can
     * form new weak arithmetic subsequences [1, 3], [1, 3] and [2, 3].
     * 5. The other elements are the same, we can view the process in the figure
     * below. The red bracket indicates the weak arithmetic subsequence of length 2,
     * and the black bracket indicates the arithmetic subsequence. The answer should
     * be the total number of black brackets.
     * 
     */
    public int numberOfArithmeticSlices(int[] nums) {
        // int maximumDifference = Arrays.stream(nums).max().getAsInt() -
        // Arrays.stream(nums).min().getAsInt();
        int[][] dp = new int[nums.length][Integer.MAX_VALUE];
        // Arrays.stream(dp).forEach(row -> Arrays.fill(row, 0));

        int numsLength = nums.length;
        int result = 0;
        for (int i = 0; i < numsLength; i++) {
            for (int j = 0; j < i; j++) {
                int difference = nums[i] - nums[j];
                dp[i][difference] += dp[j][difference] + 1;
                result += dp[j][difference] + 1;
            }
        }
        return result - (numsLength * (numsLength - 1) / 2);
    }
}
