package com.example.solution.Q1001_Q1500;

public class P1220_Count_Vowels_Permutations {
    /**
     * Reference:
     * https://leetcode.com/problems/count-vowels-permutation/editorial/?envType=daily-question&envId=2024-01-17
     * 
     * Explanation:
     * 1. We must follow all of the rules while looking for permutations, so let's
     * put all of the rules together. As shown in Figure 2, there are two ways to
     * visualize the rules: (a) demonstrates the relationship between each pair of
     * letters - the current letter and the following letter while (b) presents the
     * rules as a directed cycle.
     * 2. we can compute the number of strings of length i + 1 that end in each
     * vowel by simple addition:
     * a. aCountNew = eCount + iCount + uCount
     * b. eCountNew = aCount + iCount
     * c. iCountNew = eCount + oCount
     * d. oCountNew = iCount
     * e. uCountNew = iCount + oCount
     * 
     */

    /**
     * Strategy:
     * 1. We use the indices from 0 to 4 (inclusive) to represent the five vowels a,
     * e, i, o, and u.
     * 2. Initialize a 2D array memo of size n x 5 for memoization.
     * 3. Return the sum of vowelPermutationCount(n - 1, vowel) for each vowel as
     * the answer.
     * 4. Function vowelPermutationCount(i, vowel):
     * a. It returns a number of strings of length i that ends with vowel.
     * b. If this has been computed and saved to memo, return it directly.
     * c. According to each vowel, apply the appropriate rule, as stated above, to
     * count.
     * d. Store the value in memo and return it.
     */
    private long[][] memo;
    private int MOD = 1000000007;

    private long vowelPermutationCount(int i, int vowel) {
        if (memo[i][vowel] != 0)
            return memo[i][vowel];
        if (i == 0) {
            memo[i][vowel] = 1;
        } else {
            if (vowel == 0) {
                memo[i][vowel] = (vowelPermutationCount(i - 1, 1) + vowelPermutationCount(i - 1, 2)
                        + vowelPermutationCount(i - 1, 4)) % MOD;
            } else if (vowel == 1) {
                memo[i][vowel] = (vowelPermutationCount(i - 1, 0) + vowelPermutationCount(i - 1, 2)) % MOD;
            } else if (vowel == 2) {
                memo[i][vowel] = (vowelPermutationCount(i - 1, 1) + vowelPermutationCount(i - 1, 3)) % MOD;
            } else if (vowel == 3) {
                memo[i][vowel] = vowelPermutationCount(i - 1, 2);
            } else if (vowel == 4) {
                memo[i][vowel] = (vowelPermutationCount(i - 1, 2) + vowelPermutationCount(i - 1, 3)) % MOD;
            }
        }
        return memo[i][vowel];
    }

    private int topDown(int n) {
        // each row stands for the length of string
        // each column indicates the vowels
        // specifically, a: 0, e: 1, i: 2, o: 3, u: 4
        memo = new long[n][5];
        long result = 0;
        for (int i = 0; i < 5; i++) {
            result = (result + vowelPermutationCount(n - 1, i)) % MOD;
        }
        return (int) result;
    }

    /**
     * Strategy:
     * 1. Initialize five 1D arrays of size n, where aVowelPermutationCount[i],
     * eVowelPermutationCount[i], iVowelPermutationCount[i],
     * oVowelPermutationCount[i], and uVowelPermutationCount[i] will store the
     * number of strings of length i that end in each vowel accordingly.
     * 2. Initialize the first element in each of the five arrays to 1. This is
     * because for each starting vowel there is only one permutation when the length
     * of the string is 1.\
     * 3. Iterate the string length, i, from 1 to n:
     * a. Follow the rules to count the number of strings that end in each vowel.
     * Take the sum of the last element from each of the five arrays and that will
     * be the answer.
     */
    private int bottomUp(int n) {
        long aCount = 1, eCount = 1, iCount = 1, oCount = 1, uCount = 1;
        int MOD = 1_000_000_007;

        for (int i = 1; i < n; i++) {
            long aCountNew = (eCount + iCount + uCount) % MOD;
            long eCountNew = (aCount + iCount) % MOD;
            long iCountNew = (eCount + oCount) % MOD;
            long oCountNew = (iCount) % MOD;
            long uCountNew = (iCount + oCount) % MOD;
            aCount = aCountNew;
            eCount = eCountNew;
            iCount = iCountNew;
            oCount = oCountNew;
            uCount = uCountNew;
        }
        long result = (aCount + eCount + iCount + oCount + uCount) % MOD;
        return (int) result;
    }

    public int countVowelPermutation(int n) {
        return topDown(n);
        // return bottomUp(n);
    }
}
