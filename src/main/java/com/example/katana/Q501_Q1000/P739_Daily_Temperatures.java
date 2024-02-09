package com.example.katana.Q501_Q1000;

import java.util.PriorityQueue;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class P739_Daily_Temperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] daysTillWarmerTemperature = new int[temperatures.length];
        PriorityQueue<Pair<Integer, Integer>> decreasingTemperature = new PriorityQueue<>(
                (a, b) -> a.getLeft() - b.getLeft());

        for (int i = 1; i < temperatures.length; i++) {
            int previousTemperature = temperatures[i - 1];
            int currentTemperature = temperatures[i];
            if (currentTemperature > previousTemperature) {
                daysTillWarmerTemperature[i - 1] = 1;
            } else {
                decreasingTemperature.offer(new ImmutablePair<Integer, Integer>(previousTemperature, i - 1));
            }

            while (!decreasingTemperature.isEmpty()) {
                Pair<Integer, Integer> pendingTemperaturePair = decreasingTemperature.peek();
                int pendingTemperature = pendingTemperaturePair.getLeft();
                int pendingIndex = pendingTemperaturePair.getRight();
                if (pendingTemperature >= currentTemperature) {
                    break;
                }
                daysTillWarmerTemperature[pendingIndex] = i - pendingIndex;
                decreasingTemperature.poll();
                // System.out.println(String.format("Found: Previous %d@[%d] | Current %d@[%d] |
                // Days Till Warmer: %d",
                // pendingTemperature, pendingIndex, currentTemperature, i, i - pendingIndex));
            }
        }

        // String arrDesc =
        // Arrays.stream(daysTillWarmerTemperature).mapToObj(String::valueOf)
        // .collect(Collectors.joining(","));
        // System.out.println(String.format("Days: %s", arrDesc));
        return daysTillWarmerTemperature;
    }
}
