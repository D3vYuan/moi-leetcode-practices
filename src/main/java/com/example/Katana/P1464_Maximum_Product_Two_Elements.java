package com.example.katana;

import java.util.PriorityQueue;

public class P1464_Maximum_Product_Two_Elements {
    public int maxProduct(int[] nums) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < nums.length; i++) {
            maxHeap.offer(nums[i]);
        }

        return (maxHeap.poll() - 1) * (maxHeap.poll() - 1);
    }
}
