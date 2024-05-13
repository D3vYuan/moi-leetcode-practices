package com.example.solution.Q501_Q1000;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class P752_Open_The_Lock {
    /**
     * Reference:
     * https://leetcode.com/problems/open-the-lock/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialization:
     * a. Create two character maps, next_slot to map the current slot digit with
     * its next slot digit, and prev_slot to map the current slot digit with its
     * previous slot digit.
     * b. Create a hash set visited_combinations, initially containing all deadends
     * array combinations.
     * c. Create a queue pending_combinations to traverse all combinations in
     * level-wise BFS.
     * d. Create an integer variable turns initially storing 0, to denote the number
     * of wheel turns made.
     * 2. If visited_combinations contains the starting combination '0000' then we
     * can never reach the target combination and will return -1.
     * 3. Insert the starting combination '0000' in the queue and mark it as
     * visited.
     * 4. While there are elements in the queue, iterate on all current level
     * combinations using a for loop:
     * a. Pop the current combination from the front of the queue.
     * b. If the current combination is the target combination return turns.
     * c. Otherwise, iterate on all four wheels; for each wheel, generate the new
     * combination by turning the respective wheel to the next slot and the previous
     * slot. If the new combination is not present in visited_combinations then push
     * it in the queue and mark it as visited.
     * d. After iterating on all current level combinations increment turns by 1.
     * 5. If we never reach the target combination, then, return -1.
     */
    public int openLock(String[] deadends, String target) {
        // Map the next slot digit for each current slot digit.
        Map<Character, Character> nextSlot = new HashMap<Character, Character>() {
            {
                put('0', '1');
                put('1', '2');
                put('2', '3');
                put('3', '4');
                put('4', '5');
                put('5', '6');
                put('6', '7');
                put('7', '8');
                put('8', '9');
                put('9', '0');
            }
        };

        // Map the previous slot digit for each current slot digit.
        Map<Character, Character> prevSlot = new HashMap<Character, Character>() {
            {
                put('0', '9');
                put('1', '0');
                put('2', '1');
                put('3', '2');
                put('4', '3');
                put('5', '4');
                put('6', '5');
                put('7', '6');
                put('8', '7');
                put('9', '8');
            }
        };

        // Set to store visited and dead-end combinations.
        Set<String> visitedCombinations = new HashSet<>(Arrays.asList(deadends));
        // Queue to store combinations generated after each turn.
        Queue<String> pendingCombinations = new LinkedList<String>();

        // Count the number of wheel turns made.
        int turns = 0;

        // If the starting combination is also a dead-end,
        // then we can't move from the starting combination.
        if (visitedCombinations.contains("0000")) {
            return -1;
        }

        // Start with the initial combination '0000'.
        pendingCombinations.add("0000");
        visitedCombinations.add("0000");

        while (!pendingCombinations.isEmpty()) {
            // Explore all the combinations of the current level.
            int currLevelNodesCount = pendingCombinations.size();
            for (int i = 0; i < currLevelNodesCount; i++) {
                // Get the current combination from the front of the queue.
                String currentCombination = pendingCombinations.poll();

                // If the current combination matches the target,
                // return the number of turns/level.
                if (currentCombination.equals(target)) {
                    return turns;
                }

                // Explore all possible new combinations by turning each wheel in both
                // directions.
                for (int wheel = 0; wheel < 4; wheel += 1) {
                    // Generate the new combination by turning the wheel to the next digit.
                    StringBuilder newCombination = new StringBuilder(currentCombination);
                    newCombination.setCharAt(wheel, nextSlot.get(newCombination.charAt(wheel)));
                    // If the new combination is not a dead-end and was never visited,
                    // add it to the queue and mark it as visited.
                    if (!visitedCombinations.contains(newCombination.toString())) {
                        pendingCombinations.add(newCombination.toString());
                        visitedCombinations.add(newCombination.toString());
                    }

                    // Generate the new combination by turning the wheel to the previous digit.
                    newCombination = new StringBuilder(currentCombination);
                    newCombination.setCharAt(wheel, prevSlot.get(newCombination.charAt(wheel)));
                    // If the new combination is not a dead-end and is never visited,
                    // add it to the queue and mark it as visited.
                    if (!visitedCombinations.contains(newCombination.toString())) {
                        pendingCombinations.add(newCombination.toString());
                        visitedCombinations.add(newCombination.toString());
                    }
                }
            }
            // We will visit next-level combinations.
            turns += 1;
        }
        // We never reached the target combination.
        return -1;
    }
}
