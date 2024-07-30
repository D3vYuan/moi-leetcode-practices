package com.example.solution.Q501_Q1000;

import java.util.Map;
import java.util.TreeMap;

public class P846_Hand_Of_Straights {
    /**
     * Reference:
     * https://leetcode.com/problems/hand-of-straights/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Check if the length of the hand array is divisible by groupSize. If not,
     * return false.
     * 2. Create a map called cardCount to store the count of each card value in the
     * given hand array.
     * 3. Iterate through the hand array and update the cardCount map accordingly.
     * 4. Process the cards until the cardCount map is empty:
     * a. Get the smallest card value currentCard from the cardCount map.
     * b. Check if a consecutive sequence of groupSize cards starting from
     * currentCard exists.
     * If any card in the potential sequence is not present in the cardCount map or
     * has exhausted its occurrences, return false.
     * If the sequence exists, decrement the count of each card in the sequence from
     * the cardCount map.
     * If the count of a card becomes zero, remove it from the cardCount map.
     * 5. If all cards can be grouped into consecutive sequences of groupSize,
     * return true.
     */

    public boolean isNStraightHand(int[] hand, int groupSize) {
        int handSize = hand.length;
        if (handSize % groupSize != 0) {
            return false;
        }

        // TreeMap to store the count of each card value
        Map<Integer, Integer> cardCount = new TreeMap<>();
        for (int i = 0; i < handSize; i++) {
            cardCount.put(hand[i], cardCount.getOrDefault(hand[i], 0) + 1);
        }

        // Process the cards until the map is empty
        while (cardCount.size() > 0) {
            // Get the smallest card value
            int current_card = cardCount.entrySet().iterator().next().getKey();
            // Check each consecutive sequence of groupSize cards
            for (int i = 0; i < groupSize; i++) {
                // If a card is missing or has exhausted its occurrences
                if (!cardCount.containsKey(current_card + i))
                    return false;
                cardCount.put(
                        current_card + i,
                        cardCount.get(current_card + i) - 1);
                // Remove the card value if its occurrences are exhausted
                if (cardCount.get(current_card + i) == 0)
                    cardCount.remove(
                            current_card + i);
            }
        }

        return true;
    }
}
