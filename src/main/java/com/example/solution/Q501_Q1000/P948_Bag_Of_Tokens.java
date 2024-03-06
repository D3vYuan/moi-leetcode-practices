package com.example.solution.Q501_Q1000;

import java.util.Arrays;

public class P948_Bag_Of_Tokens {
    /**
     * Reference:
     * https://leetcode.com/problems/bag-of-tokens/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Explanation:
     * 1. What patterns do we notice in how the tokens are played to maximize the
     * score in the above examples?
     * a. The lowest power tokens are played face-up to increase the score.
     * b. The highest power tokens are played face-down to increase power.
     * 
     * Strategy:
     * 1. Initializations:
     * a. Initialize a pointer low to 0 and high to tokens.length - 1. low points to
     * the first index of tokens and high points the the last index of tokens.
     * b. Initialize a variable score to 0.
     * 2. Sort tokens in ascending order.
     * 3. While low is less than or equal to high:
     * a. If power is greater than or equal to tokens[low], we have enough power to
     * play a token face-up. We increment score by 1, reduce power by tokens[low],
     * and increase low by 1.
     * b. Else if score is greater than 0, and low is less than high, we play a
     * token face-down. We decrease score by 1, increase our power by tokens[high],
     * and decrease high by 1.
     * c. Otherwise, we don't have enough power to play a token face-up, and we
     * either don't have enough score to play a token face-down or not enough tokens
     * remain to make it worth playing a token face-down, so we return score.
     * 4. We have played all the tokens, so we return score
     */
    public int bagOfTokensScore(int[] tokens, int power) {
        int low = 0;
        int high = tokens.length - 1;
        int score = 0;
        Arrays.sort(tokens);

        while (low <= high) {
            // When we have enough power, play lowest token face-up
            if (power >= tokens[low]) {
                score += 1;
                power -= tokens[low];
                low += 1;
            }
            // We don't have enough power to play a token face-up
            // If there is at least one token remaining,
            // and we have enough score, play highest token face-down
            else if (low < high && score > 0) {
                score -= 1;
                power += tokens[high];
                high -= 1;
            }
            // We don't have enough score, power, or tokens
            // to play face-up or down and increase our score
            else {
                return score;
            }
        }
        return score;
    }
}
