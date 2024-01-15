package com.example.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P2225_Players_With_Zero_Or_One_Losses {
    /**
     * Reference:
     * https://leetcode.com/problems/find-players-with-zero-or-one-losses/editorial/?envType=daily-question&envId=2024-01-15
     * 
     * Strategy:
     * 1. Initialize three empty hash sets, zero_loss, one_loss and more_losses.
     * 2. Iterate over matches, for each match [winner, loser], update the sets they
     * are in according to the following rule:
     * a. For winner:
     * a1. If winner is not in more_losses or one_loss, it means he should be in
     * zero_loss, add it to zero_loss.
     * a2. Otherwise, the number of losses for winner doesn't change, keep this
     * player in the original set.
     * b. For loser:
     * b1. If loser is in zero_loss, remove it from zero_loss and add it to one_loss
     * since this player has one more loss now.
     * b2. If loser is in one_loss, remove it from one_loss and add it to
     * more_losses since this player has one more loss now.
     * b3. If loser is in more_losses, keep this player in more_losses.
     * b4. Otherwise, it means that this match is loser's first match, we add this
     * player to one_loss.
     * 3. After the iteration ends, get the players from zero_loss and one_loss and
     * sort them, as required by the problem.
     * 
     */
    public List<List<Integer>> findWinners(int[][] matches) {
        Set<Integer> zeroLoss = new HashSet<>(), oneLoss = new HashSet<>(),
                moreLosses = new HashSet<>();

        for (int[] match : matches) {
            int winner = match[0], loser = match[1];
            // Add winner.
            if (!oneLoss.contains(winner) && !moreLosses.contains(winner)) {
                zeroLoss.add(winner);
            }
            // Add or move loser.
            if (zeroLoss.contains(loser)) {
                zeroLoss.remove(loser);
                oneLoss.add(loser);
            } else if (oneLoss.contains(loser)) {
                oneLoss.remove(loser);
                moreLosses.add(loser);
            } else if (moreLosses.contains(loser)) {
                continue;
            } else {
                oneLoss.add(loser);
            }
        }

        List<List<Integer>> answer = Arrays.asList(new ArrayList<>(), new ArrayList<>());
        answer.get(0).addAll(zeroLoss);
        answer.get(1).addAll(oneLoss);
        Collections.sort(answer.get(0));
        Collections.sort(answer.get(1));

        return answer;
    }
}
