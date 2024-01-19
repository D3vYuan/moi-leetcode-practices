package com.example.katana.Q501_Q1000;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class P767_Reorganize_String {
    public String reorganizeString(String s) {
        Map<Character, Integer> occurrences = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            occurrences.put(s.charAt(i), occurrences.getOrDefault(s.charAt(i), 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> heap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        occurrences.entrySet().stream().forEach(entry -> heap.offer(entry));

        StringBuilder sb = new StringBuilder();
        while (!heap.isEmpty()) {
            Map.Entry<Character, Integer> entry = heap.poll();
            char affectedCharacter = entry.getKey();
            int affectedOccurrences = entry.getValue();

            System.out.println(String.format("Adding: %s with %d occurrences", affectedCharacter, affectedOccurrences));

            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == affectedCharacter) {
                if (heap.isEmpty()) {
                    return "";
                }

                Map.Entry<Character, Integer> nextEntry = heap.poll();
                char nextCharacter = nextEntry.getKey();
                int nextOccurrences = nextEntry.getValue();

                System.out.println(
                        String.format("Adding Next (conflict): %s with %d occurrences", nextCharacter,
                                nextOccurrences));
                sb.append(nextCharacter);
                if (nextOccurrences - 1 > 0) {
                    heap.offer(new SimpleEntry<Character, Integer>(nextCharacter, nextOccurrences - 1));
                }
                heap.offer(new SimpleEntry<Character, Integer>(affectedCharacter, affectedOccurrences));
            } else {
                sb.append(affectedCharacter);
                if (affectedOccurrences - 1 > 0) {
                    heap.offer(new SimpleEntry<Character, Integer>(affectedCharacter, affectedOccurrences - 1));
                }
            }
        }
        return sb.toString();
    }
}
