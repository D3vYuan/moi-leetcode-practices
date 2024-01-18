package com.example.solution.Q001_Q500;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class P332_Reconstruct_Itinerary {

    /**
     * Reference:
     * https://leetcode.com/problems/reconstruct-itinerary/?envType=daily-question&envId=2024-01-17
     * 
     * Explanation:
     * 1. At each airport, one might have several possible destinations to fly to.
     * With backtracking, we enumerate each possible destination. We mark the choice
     * at each iteration (i.e. trial) before we move on to the chosen destination.
     * If the destination does not lead to a solution (i.e. fail), we would then
     * fallback to the previous state and start another iteration of the
     * trial-fail-and-fallback cycle.
     * 2. At each airport, given a list of possible destinations, while
     * backtracking, at each step we would pick the destination greedily in lexical
     * order, i.e. the one with the smallest lexical order would have its trial
     * first.
     * 
     * Strategy:
     * 1. As the first step, we build a graph data structure from the given input.
     * This graph should allow us to quickly identify a list of potential
     * destinations, given an origin. Here we adopted the hashmap (or dictionary)
     * data structure, with each entry as <origin, [destinations]>.
     * 2. Then due to our greedy strategy, we should order the destination list for
     * each entry in lexical order. As an alternative solution, one could use
     * PriorityQueue data structure in the first step to keep the list of
     * destinations, which would maintain the order at the moment of constructing
     * the list.
     * 3. As the final step, we kick off the backtracking traversal on the above
     * graph, to obtain the final result.
     * a. At the beginning of the backtracking function, as the bottom case, we
     * check if we have already obtained a valid itinerary.
     * b. Otherwise, we enumerate the next destinations in order.
     * c. We mark the status of the visit, before and after each backtracking loop.
     */
    // origin -> list of destinations
    HashMap<String, List<String>> flightMap = new HashMap<>();
    HashMap<String, boolean[]> visitBitmap = new HashMap<>();
    int flights = 0;
    List<String> result = null;

    public List<String> findItinerary(List<List<String>> tickets) {
        // Step 1). build the graph first
        for (List<String> ticket : tickets) {
            String origin = ticket.get(0);
            String dest = ticket.get(1);
            if (this.flightMap.containsKey(origin)) {
                List<String> destList = this.flightMap.get(origin);
                destList.add(dest);
            } else {
                List<String> destList = new LinkedList<String>();
                destList.add(dest);
                this.flightMap.put(origin, destList);
            }
        }

        // Step 2). order the destinations and init the visit bitmap
        for (Map.Entry<String, List<String>> entry : this.flightMap.entrySet()) {
            Collections.sort(entry.getValue());
            this.visitBitmap.put(entry.getKey(), new boolean[entry.getValue().size()]);
        }

        this.flights = tickets.size();
        LinkedList<String> route = new LinkedList<String>();
        route.add("JFK");

        // Step 3). backtracking
        this.backtracking("JFK", route);
        return this.result;
    }

    protected boolean backtracking(String origin, LinkedList<String> route) {
        if (route.size() == this.flights + 1) {
            this.result = (List<String>) route.clone();
            return true;
        }

        if (!this.flightMap.containsKey(origin))
            return false;

        int i = 0;
        boolean[] bitmap = this.visitBitmap.get(origin);

        for (String dest : this.flightMap.get(origin)) {
            if (!bitmap[i]) {
                bitmap[i] = true;
                route.add(dest);
                boolean ret = this.backtracking(dest, route);
                route.pollLast();
                bitmap[i] = false;

                if (ret)
                    return true;
            }
            ++i;
        }

        return false;
    }
}
