package com.example.katana.Q001_Q500;

import java.util.PriorityQueue;

public class P4_Median_Of_Two_Sorted_Arrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a); // For Small Number
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b); // For Large Number

        int totalLength = nums1.length + nums2.length;
        int maxCapacity = totalLength / 2;
        int minCapacity = totalLength - maxCapacity;

        for (int i = 0; i < nums1.length; i++) {
            minHeap.offer(nums1[i]);
        }

        for (int i = 0; i < nums2.length; i++) {
            minHeap.offer(nums2[i]);
        }

        while (minHeap.size() > minCapacity) {
            maxHeap.offer(minHeap.poll());
        }

        boolean isEven = totalLength % 2 == 0;
        if (isEven) {
            System.out.println(String.format("Max: %d | Min: %d | Median: %d", maxHeap.peek(), minHeap.peek(),
                    (maxHeap.peek() + minHeap.peek()) / 2));
            return (maxHeap.poll() + minHeap.poll()) / 2.0;
        } else {
            return minHeap.poll();
        }
    }
}
