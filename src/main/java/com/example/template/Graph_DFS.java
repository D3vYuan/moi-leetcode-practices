package com.example.template;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Graph_DFS {
    // Graph: DFS (recursive)
    int START_NODE = 0;

    Set<Integer> seen = new HashSet<>();

    public int fn(int[][] graph) {
        seen.add(START_NODE);
        return dfs(START_NODE, graph);
    }

    public int dfs(int node, int[][] graph) {
        int ans = 0;
        // do some logic
        for (int neighbor : graph[node]) {
            if (!seen.contains(neighbor)) {
                seen.add(neighbor);
                ans += dfs(neighbor, graph);
            }
        }

        return ans;
    }

    // Graph: DFS (iterative)
    public int fn_2(int[][] graph) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> seen = new HashSet<>();
        stack.push(START_NODE);
        seen.add(START_NODE);
        int ans = 0;

        while (!stack.empty()) {
            int node = stack.pop();
            // do some logic
            for (int neighbor : graph[node]) {
                if (!seen.contains(neighbor)) {
                    seen.add(neighbor);
                    stack.push(neighbor);
                }
            }
        }

        return ans;
    }
}
