package com.example.katana;

import java.util.PriorityQueue;

public class P1561_Maximum_Number_Of_Coins {
    public int maxCoins(int[] piles) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> b - a);

        int partition = piles.length / 3;

        for (int i = 0; i < piles.length; i++) {
            minHeap.offer(piles[i]);
            if (minHeap.size() > partition) {
                System.out.println(String.format("Move to max: %d", minHeap.peek()));
                maxHeap.offer(minHeap.poll());
            }
        }

        int count = 0;
        int maximumCoins = 0;
        while (!maxHeap.isEmpty()) {
            count++;
            int currentCoin = maxHeap.poll();
            if (count % 2 == 0) {
                System.out.println(String.format("Adding: %d", currentCoin));
                maximumCoins += currentCoin;
            }
        }

        return maximumCoins;
    }
}
