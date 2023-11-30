package com.example.template;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class Dijkstra {
    public void calculate() {
        int n = 10;
        int source = 0;

        Map<Integer, List<Pair<Integer, Integer>>> graph = new HashMap<>();

        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        Queue<Pair<Integer, Integer>> heap = new PriorityQueue<Pair<Integer, Integer>>(
                Comparator.comparing(Pair::getKey));
        heap.add(new ImmutablePair<Integer, Integer>(0, source));

        while (!heap.isEmpty()) {
            Pair<Integer, Integer> curr = heap.remove();
            int currDist = curr.getKey();
            int node = curr.getValue();

            if (currDist > distances[node]) {
                continue;
            }

            for (Pair<Integer, Integer> edge : graph.get(node)) {
                int nei = edge.getKey();
                int weight = edge.getValue();
                int dist = currDist + weight;

                if (dist < distances[nei]) {
                    distances[nei] = dist;
                    heap.add(new ImmutablePair<Integer, Integer>(dist, nei));
                }
            }
        }
    }
}
