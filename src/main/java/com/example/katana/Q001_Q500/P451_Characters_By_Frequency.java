package com.example.katana.Q001_Q500;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class P451_Characters_By_Frequency {
    public String frequencySort(String s) {
        Map<Character, Integer> frequencies = new HashMap<>();
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            frequencies.put(currentChar, frequencies.getOrDefault(currentChar, 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            maxHeap.offer(new int[] { entry.getKey(), entry.getValue() });
        }

        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            int[] currentEntry = maxHeap.poll();
            char currentChar = (char) currentEntry[0];
            int count = 0;
            while (count < currentEntry[1]) {
                sb.append(currentChar);
                count++;
            }
        }
        return sb.toString();
    }
}
