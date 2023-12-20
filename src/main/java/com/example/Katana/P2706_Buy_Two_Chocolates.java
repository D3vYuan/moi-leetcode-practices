package com.example.katana;

import java.util.PriorityQueue;

public class P2706_Buy_Two_Chocolates {
    public int buyChoco(int[] prices, int money) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 0; i < prices.length; i++) {
            minHeap.offer(prices[i]);
        }

        int remaining = money - minHeap.poll() - minHeap.poll();
        return remaining < 0 ? money : remaining;
    }
}
