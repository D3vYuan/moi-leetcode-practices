package com.example.template;

import java.util.PriorityQueue;

public class TopKElements {
    public int[] fn(int[] arr, int k) {
        int CRITERIA = 0;

        PriorityQueue<Integer> heap = new PriorityQueue<>(CRITERIA);
        for (int num : arr) {
            heap.add(num);
            if (heap.size() > k) {
                heap.remove();
            }
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = heap.remove();
        }

        return ans;
    }
}
