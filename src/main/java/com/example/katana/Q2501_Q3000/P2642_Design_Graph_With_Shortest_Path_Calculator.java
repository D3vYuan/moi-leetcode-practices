package com.example.katana.Q2501_Q3000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class P2642_Design_Graph_With_Shortest_Path_Calculator {
    class NodeEdge {
        int source;
        int destination;
        int cost;

        public NodeEdge(int source, int destination, int cost) {
            this.source = source;
            this.destination = destination;
            this.cost = cost;
        }

        public int getSource() {
            return this.source;
        }

        public int getDestination() {
            return this.destination;
        }

        public int getCost() {
            return this.cost;
        }
    }

    class Graph {
        private int n;
        private int[][] edges;
        private Map<Integer, List<NodeEdge>> edgesMapping;

        public Graph(int n, int[][] edges) {
            this.n = n;
            this.edges = edges;
            this.edgesMapping = new HashMap<>();
            Arrays.stream(this.edges).forEach(row -> populateEdgesMapping(row));
        }

        private void populateEdgesMapping(int[] edge) {
            int source = edge[0];
            int destination = edge[1];
            int cost = edge[2];
            NodeEdge nodeEdge = new NodeEdge(source, destination, cost);

            if (!edgesMapping.containsKey(source)) {
                edgesMapping.put(source, new ArrayList<>());
            }
            edgesMapping.get(source).add(nodeEdge);
        }

        private void printEdges() {
            System.out.println(String.format("Total Edges: %d", this.n));
            System.out.println(String.format("Total Edges Path: %d", this.edges.length));
            for (int row = 0; row < this.edges.length; row++) {
                for (int col = 0; col < this.edges[row].length; col++) {
                    System.out.println(String.format("(%d, %d) - %d", row, col, this.edges[row][col]));
                }
            }
        }

        public void addEdge(int[] edge) {
            // addEdge(int[] edge) adds an edge to the list of edges where edge = [from, to,
            // edgeCost]
            String edges = "(" + Arrays.stream(edge).mapToObj(String::valueOf).collect(Collectors.joining(",")) + ")";
            System.out.println(String.format("Adding Edge: %s", edges));
            this.edges[this.edges.length - 1] = edge;
            populateEdgesMapping(edge);
        }

        private List<Integer> buildAdjacentList(int node1, int node2) {
            Queue<Integer> relatedQueues = new LinkedList<>();
            List<Integer> adjacentList = new ArrayList<>();
            List<Integer> visited = new ArrayList<>();

            for (int i = 0; i < this.n; i++) {
                adjacentList.add(Integer.MAX_VALUE);
            }
            adjacentList.set(node1, 0);
            relatedQueues.offer(node1);
            while (!relatedQueues.isEmpty()) {
                int currentNode = relatedQueues.poll();
                int currentCost = adjacentList.get(currentNode);
                visited.add(currentNode);

                List<NodeEdge> neighbouringNodes = !edgesMapping.containsKey(currentNode) ? new ArrayList<>()
                        : edgesMapping.get(currentNode);

                for (NodeEdge neighbouringNode : neighbouringNodes) {
                    System.out.println(String.format("(%d, %d) - %d", neighbouringNode.getSource(),
                            neighbouringNode.getDestination(),
                            neighbouringNode.getCost()));

                    if (!adjacentList.contains(neighbouringNode.getDestination())) { // Not in adjacent list yet
                        System.out.println(String.format("Adding to adjacent list: Node #%d (%d)",
                                neighbouringNode.getDestination(),
                                currentCost + neighbouringNode.getCost()));
                        adjacentList.set(neighbouringNode.getDestination(), currentCost + neighbouringNode.getCost());
                    } else if (adjacentList.get(neighbouringNode.getDestination()) > currentCost
                            + neighbouringNode.getCost()) { // Shorter
                        // Path
                        System.out.println(
                                String.format("Updated adjacent list: Node #%d (%d) | Original (%d)",
                                        neighbouringNode.getDestination(),
                                        currentCost + neighbouringNode.getCost(),
                                        adjacentList.get(neighbouringNode.getDestination())));
                        adjacentList.set(neighbouringNode.getDestination(), currentCost + neighbouringNode.getCost());
                    }

                    if (!visited.contains(neighbouringNode.getDestination())) {
                        System.out
                                .println(String.format("Adding to queue: Node #%d", neighbouringNode.getDestination()));
                        relatedQueues.offer(neighbouringNode.getDestination());
                    }
                }
            }

            return adjacentList;
        }

        public int shortestPath(int node1, int node2) {
            // Build Adjacent List
            System.out.println(String.format("Traversing: Node %d to Node %d", node1, node2));
            List<Integer> adjacentList = buildAdjacentList(node1, node2);
            return adjacentList.get(node2) == Integer.MAX_VALUE ? -1 : adjacentList.get(node2);
        }
    }

    /**
     * Your Graph object will be instantiated and called as such:
     * Graph obj = new Graph(n, edges);
     * obj.addEdge(edge);
     * int param_2 = obj.shortestPath(node1,node2);
     */
}
