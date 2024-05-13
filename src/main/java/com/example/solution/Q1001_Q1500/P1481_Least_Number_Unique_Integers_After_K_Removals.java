package com.example.solution.Q1001_Q1500;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class P1481_Least_Number_Unique_Integers_After_K_Removals {

    /**
     * Reference:
     * https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a hashmap map which maps element to its frequency.
     * 2. Iterate over the given arr and increment the frequency of its elements in
     * map.
     * 3. Create a min-heap frequencies and populate it with the frequencies
     * obtained from map.
     * 4. Create a variable elementsRemoved which will track the number of elements
     * that are removed.
     * 5. Remove elements from frequencies and increment elementsRemoved while there
     * are still elements in frequencies.
     * 6. If elementsRemoved becomes greater than k, we can stop iterating and
     * return the number of remaining elements in the heap.
     * 7. Return 0 if the heap becomes empty. This means we removed all elements
     * from the original array arr.
     */
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        // Map to track the frequencies of elements
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        // Min heap to track all the frequencies
        PriorityQueue<Integer> frequencies = new PriorityQueue<>(map.values());

        // Tracking the number of elements removed
        int elementsRemoved = 0;

        // Traversing all frequencies
        while (!frequencies.isEmpty()) {
            // Removing the least frequent element
            elementsRemoved += frequencies.peek();

            // If the number of elements removed exceeds k, return
            // the remaining number of unique elements
            if (elementsRemoved > k) {
                return frequencies.size();
            }
            frequencies.poll();
        }

        // We have removed all elements, so no unique integers remain
        // Return 0 in this case
        return 0;
    }
}
