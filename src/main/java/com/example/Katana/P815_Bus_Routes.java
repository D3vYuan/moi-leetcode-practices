
package com.example.katana;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class P815_Bus_Routes {
    // Only Passed 43/49
    private void addNeighbour(Map<Integer, List<List<Integer>>> adjacentList, int busId, int source, int neighbour) {
        // System.out.println(String.format("Adding: %d and %d for bus id #%d", source,
        // neighbour, busId));
        if (!adjacentList.containsKey(source)) {
            adjacentList.put(source, new ArrayList<>());
        }

        if (!adjacentList.containsKey(neighbour)) {
            adjacentList.put(neighbour, new ArrayList<>());
        }

        List<List<Integer>> sourceList = adjacentList.get(source);
        List<List<Integer>> neighbourList = adjacentList.get(neighbour);

        // System.out.println(String.format("Source #%d: There total of %d routes so
        // far", source, sourceList.size()));
        while (sourceList.size() < busId + 1) {
            sourceList.add(new ArrayList<>());
        }

        // System.out.println(String.format("Neighbour #%d: There total of %d routes so
        // far", source, neighbourList.size()));
        while (neighbourList.size() < busId + 1) {
            neighbourList.add(new ArrayList<>());
        }
        List<Integer> sourceBusList = sourceList.get(busId);
        List<Integer> neighbourBusList = neighbourList.get(busId);

        sourceBusList.add(neighbour);
        neighbourBusList.add(source);
    }

    private Map<Integer, List<List<Integer>>> buildAdjacentList(int[][] routes) {
        Map<Integer, List<List<Integer>>> adjacentList = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            int[] currentRoute = routes[i];
            System.out.println(String.format("Bus #%d has %d routes", i, currentRoute.length));
            for (int j = 0; j < currentRoute.length; j++) {
                int neighbour = j + 1;
                while (neighbour < currentRoute.length) {
                    addNeighbour(adjacentList, i, routes[i][j], routes[i][neighbour]);
                    neighbour++;
                }
            }
        }
        return adjacentList;
    }

    private int bfs(Map<Integer, List<List<Integer>>> busReachableStops, int source, int target) {
        Set<Integer> busVisited = new HashSet<>();
        Set<Integer> routeVisited = new HashSet<>();
        Queue<String> nextRoutes = new LinkedList<>();

        // System.out
        // .println(String.format("Adding Bus #? Route #%d to queue", source));
        nextRoutes.offer("-" + source);
        boolean isFound = false;

        while (!nextRoutes.isEmpty() && !isFound) {
            String nextRoute = nextRoutes.poll();
            String[] nextRouteParts = nextRoute.split("-");
            int currentBus = nextRouteParts[0] == "" ? -1 : Integer.valueOf(nextRouteParts[0]);
            int currentRoute = Integer.valueOf(nextRouteParts[nextRouteParts.length - 1]);

            if (routeVisited.contains(currentRoute)) {
                continue;
            }

            routeVisited.add(currentRoute);
            busVisited.add(currentBus);

            List<List<Integer>> currentAssociatedRoutes = busReachableStops.get(currentRoute);
            for (int i = 0; i < currentAssociatedRoutes.size(); i++) {
                List<Integer> currentBusRoutes = currentAssociatedRoutes.get(i);
                if (currentBusRoutes.isEmpty()) {
                    // System.out
                    // .println(String.format("Found Bus #%d No Routes - continuing", i));
                    continue;
                }

                for (int routeId = 0; routeId < currentBusRoutes.size(); routeId++) {
                    int currentAssoicatedRoute = currentBusRoutes.get(routeId);
                    if (currentAssoicatedRoute == target) {
                        // System.out
                        // .println(String.format("Found Bus #%d Route #%d - exiting", i,
                        // currentAssoicatedRoute));
                        isFound = true;
                        break;
                    }

                    // System.out
                    // .println(String.format("Adding Bus #%d Route #%d to queue", i,
                    // currentAssoicatedRoute));
                    nextRoutes.offer(String.format("%d-%d", i, currentAssoicatedRoute));
                }

                if (isFound) {
                    break;
                }
            }
        }

        return isFound ? (busVisited.size() == 0 ? 1 : busVisited.size()) : -1;
    }

    private void showAdjacentList(Map<Integer, List<List<Integer>>> busReachableStops) {

        for (Map.Entry<Integer, List<List<Integer>>> route : busReachableStops.entrySet()) {

            int routeId = route.getKey();
            StringBuilder sb = new StringBuilder();
            // sb.append(String.format("Current Route #%d - ", routeId));
            // System.out.println(String.format("Exploring Route #%d", routeId));

            List<List<Integer>> associatedRoutes = route.getValue();
            for (int i = 0; i < associatedRoutes.size(); i++) {
                List<Integer> busRoutes = associatedRoutes.get(i);
                if (busRoutes.isEmpty()) {
                    continue;
                }
                String busRoutesDesc = "("
                        + busRoutes.stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";
                sb.append(String.format("Bus #%d (%s) ", i, busRoutesDesc));
            }
            System.out.println(sb.toString());
        }
    }

    public int numBusesToDestination(int[][] routes, int source, int target) {
        // Strategy
        // Build Adjacent List of Stop <-> Reachable Stops
        // Using BFS see whether Source is reachable to target
        if (source == target) {
            return 0;
        }
        Map<Integer, List<List<Integer>>> busReachableStops = buildAdjacentList(routes);
        showAdjacentList(busReachableStops);
        int minimumNumberOfStops = bfs(busReachableStops, source, target);
        return minimumNumberOfStops;
    }
}