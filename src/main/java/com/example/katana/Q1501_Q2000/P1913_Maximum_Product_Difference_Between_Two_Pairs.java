package com.example.katana.Q1501_Q2000;

import java.util.PriorityQueue;

public class P1913_Maximum_Product_Difference_Between_Two_Pairs {
    public int maxProductDifference(int[] nums) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 0; i < nums.length; i++) {
            maxHeap.offer(nums[i]);
            minHeap.offer(nums[i]);
        }

        int maxProduct = maxHeap.poll() * maxHeap.poll();
        int minProduct = minHeap.poll() * minHeap.poll();
        System.out.println(
                String.format("Max: %d | Min: %d | Difference: %d", maxProduct, minProduct, maxProduct - minProduct));
        return maxProduct - minProduct;
    }
}
