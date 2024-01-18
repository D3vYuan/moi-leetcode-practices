package com.example.solution.Q2501_Q3000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class P2642_Design_Graph_With_Shortest_Path_Calculator {
    class Graph {
        List<List<Pair<Integer, Integer>>> adjList;

        public Graph(int n, int[][] edges) {
            adjList = new ArrayList<>();
            for (int i = 0; i < n; i++)
                adjList.add(new ArrayList<>());
            for (int[] e : edges)
                adjList.get(e[0]).add(new ImmutablePair<Integer, Integer>(e[1], e[2]));
        }

        public void addEdge(int[] edge) {
            adjList.get(edge[0]).add(new ImmutablePair<Integer, Integer>(edge[1], edge[2]));
        }

        public int shortestPath(int node1, int node2) {
            int n = adjList.size();
            PriorityQueue<List<Integer>> pq = new PriorityQueue<List<Integer>>(
                    Comparator.comparingInt(list -> list.get(0)));
            int[] costForNode = new int[n];
            Arrays.fill(costForNode, Integer.MAX_VALUE);
            costForNode[node1] = 0;
            pq.offer(Arrays.asList(0, node1));

            while (!pq.isEmpty()) {
                List<Integer> curr = pq.poll();
                int currCost = curr.get(0);
                int currNode = curr.get(1);

                if (currCost > costForNode[currNode]) {
                    continue;
                }
                if (currNode == node2) {
                    return currCost;
                }
                for (Pair<Integer, Integer> neighbor : adjList.get(currNode)) {
                    int neighborNode = neighbor.getKey();
                    int cost = neighbor.getValue();
                    int newCost = currCost + cost;

                    if (newCost < costForNode[neighborNode]) {
                        costForNode[neighborNode] = newCost;
                        pq.offer(Arrays.asList(newCost, neighborNode));
                    }
                }
            }

            return -1;
        }
    }
}
