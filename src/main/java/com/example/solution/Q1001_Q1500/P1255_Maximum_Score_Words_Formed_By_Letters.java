package com.example.solution.Q1001_Q1500;

public class P1255_Maximum_Score_Words_Formed_By_Letters {
    /**
     * Reference:
     * https://leetcode.com/problems/maximum-score-words-formed-by-letters/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Generate a frequency array where freq[c] is the number of times letter c
     * appears in letters.
     * 2. Initialize maxScore to store the largest score among valid subsets.
     * 3. Call a recursive subroutine check that passes w (the index of the current
     * word), words, score, subsetLetters, and totalScore (the sum of word scores in
     * the subset) as parameters. Steps 4-10 describe the check method.
     * 4. If w equals −1, all words have been considered, and we should update
     * maxScore to totalScore if maxScore is less than totalScore.
     * 5. Otherwise, we need to consider two possible recursive calls: one that adds
     * words[w] to the subset, and one that doesn't.
     * 6. To account for not adding a word, call check(w - 1, words, score,
     * subsetLetters, totalScore).
     * 7. To add words[w] to the subset, update subsetLetters and totalScore to
     * include the word.
     * 8. If the addition of words[w] does not violate letter limits imposed by
     * freq, make the recursive call check(w - 1, words, score, subsetLetters,
     * totalScore). To check for validity, we define the isValidWord method as
     * follows:
     * a. For each character in the alphabet, check if freq[c] < subsetLetters[c].
     * If there exists such c, return false.
     * b. Return true if the subset can be built out of the given letters.
     * 9. Roll back the changes to subsetLetters and totalScore immediately after
     * making this recursive call.
     * 10. Call check(W - 1, words, score, subsetLetters, 0), where subsetLetters is
     * initially all zeros.
     * 11. Return maxScore as the result.
     */

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int W = words.length;
        // Count how many times each letter occurs
        freq = new int[26];
        for (char c : letters) {
            freq[c - 'a']++;
        }
        maxScore = 0;
        check(W - 1, words, score, new int[26], 0);
        return maxScore;
    }

    // Check if adding this word exceeds the frequency of any letter
    private boolean isValidWord(int[] subsetLetters) {
        for (int c = 0; c < 26; c++) {
            if (freq[c] < subsetLetters[c]) {
                return false;
            }
        }
        return true;
    }

    private int maxScore;
    private int[] freq;

    private void check(int w, String[] words, int[] score, int[] subsetLetters, int totalScore) {
        if (w == -1) {
            // If all words have been iterated, check the score of this subset
            maxScore = Math.max(maxScore, totalScore);
            return;
        }
        // Not adding words[w] to the current subset
        check(w - 1, words, score, subsetLetters, totalScore);
        // Adding words[w] to the current subset
        int L = words[w].length();
        for (int i = 0; i < L; i++) {
            int c = words[w].charAt(i) - 'a';
            subsetLetters[c]++;
            totalScore += score[c];
        }

        if (isValidWord(subsetLetters)) {
            // Consider the next word if this subset is still valid
            check(w - 1, words, score, subsetLetters, totalScore);
        }
        // Rollback effects of this word
        for (int i = 0; i < L; i++) {
            int c = words[w].charAt(i) - 'a';
            subsetLetters[c]--;
            totalScore -= score[c];
        }
    }
}
