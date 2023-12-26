package com.example.solution;

import java.util.ArrayList;
import java.util.List;

public class P847_Shortest_Path_Visiting_All_Nodes {
    /**
     * Reference:
     * https://leetcode.com/problems/shortest-path-visiting-all-nodes/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation:
     * 1. First, we clearly need to know what node we are currently at. Second, we
     * need to know which nodes we have already visited. Since the problem states
     * that we can revisit nodes and reuse edges, these two pieces of information
     * are all that we need
     * 2. If there are n nodes, then there are 2^n possible states of nodes we have
     * visited so far - for each node, we have either visited it or we haven't.
     * Therefore, we can use an integer to represent the nodes that we have visited
     * so far - if the ith bit of the integer (from the right side) is set to 1,
     * then that means we have visited node i
     * 3. To change the mask through flipping bits, we can start by taking the
     * number 1. If we left shift 1 i times, then we will end up with a binary
     * number that only has a 1 in the ith position. For example, 1 << 4 equals 16,
     * or '10000' in binary, which is just a 1 in the 4th position. We can then XOR
     * this number with our mask. Because 1 XOR 1 = 0 and 1 XOR 0 = 1, this would
     * flip the bit in the 4th position of our mask.
     * 
     * Strategy:
     * 1. If graph only contains one node, then return 0 as we can start at node 0
     * and complete the problem without taking any steps.
     * 2. Initialize some variables:
     * a. n, as the length of graph.
     * b. endingMask = (1 << n) - 1, a bitmask that represents all nodes being
     * visited.
     * c. seen, a data structure that will be used to indicate if we have visited a
     * state to prevent cycles.
     * d. queue, a data structure that implements an abstract queue used for our
     * BFS.
     * e. steps, an integer that keeps track of which step we are on. Since BFS
     * gaurantees a shortest path, as soon as we encounter endingMask, we can return
     * steps.
     * 3. Populate queue and seen with the base cases (starting at all nodes with
     * the mask set to having only visited the given node). This is (i, 1 << i) for
     * all i from 0 to n - 1.
     * 4. Perform a BFS:
     * a. Initialize nextQueue, which will replace queue at the end of the current
     * step.
     * b. Loop through the current queue. For each state (node, mask), loop through
     * graph[node]. For each neighbor, declare a new state (neighbor, nextMask),
     * where nextMask = mask | (1 << neighbor). If nextMask == endingMask, then that
     * means taking one more step to the neighbor will complete visiting all nodes,
     * so return 1 + steps. Otherwise, if this new state has not yet been visited,
     * then add it nextQueue and seen.
     * c. After looping through the current queue, increment steps by 1 and replace
     * queue with nextQueue.
     * 5. The constraints state that the input graph is always connected, therefore
     * there will always be an answer. The return statement in the BFS will always
     * trigger, and we don't need to worry about other cases.
     */
    public int shortestPathLength(int[][] graph) {
        if (graph.length == 1) {
            return 0;
        }

        int n = graph.length;
        int endingMask = (1 << n) - 1;
        boolean[][] seen = new boolean[n][endingMask];
        List<int[]> queue = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            queue.add(new int[] { i, 1 << i });
            seen[i][1 << i] = true;
        }

        int steps = 0;
        while (!queue.isEmpty()) {
            List<int[]> nextQueue = new ArrayList<>();
            for (int i = 0; i < queue.size(); i++) {
                int[] currentPair = queue.get(i);

                int node = currentPair[0];
                int mask = currentPair[1];

                for (int neighbor : graph[node]) {
                    int nextMask = mask | (1 << neighbor);
                    if (nextMask == endingMask) {
                        return 1 + steps;
                    }

                    if (!seen[neighbor][nextMask]) {
                        seen[neighbor][nextMask] = true;
                        nextQueue.add(new int[] { neighbor, nextMask });
                    }
                }
            }

            steps++;
            queue = nextQueue;
        }
        return -1;
    }
}
