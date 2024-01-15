package com.example.katana;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class P2225_Players_With_Zero_Or_One_Losses {
    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> losses = new HashMap<>();
        Map<Integer, Integer> wins = new HashMap<>();
        for (int i = 0; i < matches.length; i++) {
            int[] outcome = matches[i];

            int winner = outcome[0];
            int loser = outcome[1];

            wins.put(winner, wins.getOrDefault(winner, 0) + 1);
            losses.put(loser, losses.getOrDefault(loser, 0) + 1);
        }
        List<Integer> noLossesWinner = wins.keySet().stream().filter(winner -> !losses.containsKey(winner))
                .collect(Collectors.toList());
        noLossesWinner.sort((a, b) -> a - b);
        List<Integer> lostOneMatch = losses.entrySet().stream().filter(entry -> entry.getValue() == 1)
                .map(entry -> entry.getKey()).collect(Collectors.toList());
        lostOneMatch.sort((a, b) -> a - b);
        List<List<Integer>> winners = new ArrayList<>(
                Arrays.asList(noLossesWinner, lostOneMatch));
        return winners;
    }
}
