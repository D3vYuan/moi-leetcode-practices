package com.example.katana.Q1501_Q2000;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class P1743_Restore_Array_From_Adjacent_Pairs {
    private Map<Integer, Set<Integer>> adjacentList = new HashMap<>();

    private void pushKey(Map<Integer, Set<Integer>> adjacentList, int currentKey, int currentValue) {
        if (!adjacentList.containsKey(currentKey)) {
            adjacentList.put(currentKey, new HashSet<>());
        }

        adjacentList.get(currentKey).add(currentValue);
    }

    private void dfs(int key, int prev, int[] numbers, int numberIdx) {
        System.out.println("Adding #" + numberIdx + ": " + key);
        numbers[numberIdx] = key;
        Set<Integer> values = adjacentList.get(key);
        for (int nextKey : values) {
            if (nextKey != prev) {
                dfs(nextKey, key, numbers, numberIdx + 1);
            }
        }
    }

    public int[] restoreArray(int[][] adjacentPairs) {
        /**
         * Strategy
         * [1] Populate Adjacent List from the Adjacent Pairs
         * [2] Push Smallest to Stack
         * [3] Pop until stack is empty
         */

        int totalPairs = adjacentPairs.length;
        for (int i = 0; i < totalPairs; i++) {
            int[] currentPair = adjacentPairs[i];
            int currentLeft = currentPair[0];
            int currentRight = currentPair[1];

            pushKey(adjacentList, currentLeft, currentRight);
            pushKey(adjacentList, currentRight, currentLeft);
        }

        Map.Entry<Integer, Set<Integer>> firstElement = Collections.min(adjacentList.entrySet(),
                (a, b) -> a.getValue().size() - b.getValue().size());
        int firstKey = firstElement.getKey();

        int[] numbers = new int[adjacentList.size()];
        dfs(firstKey, Integer.MAX_VALUE, numbers, 0);

        return numbers;
    }
}
