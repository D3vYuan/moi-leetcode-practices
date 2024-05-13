package com.example.solution.Q001_Q500;

public class P200_Number_Of_Islands {
    /**
     * Reference:
     * https://leetcode.com/problems/number-of-islands/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Linear scan the 2d grid map, if a node contains a '1', then it is a root
     * node that triggers a Depth First Search.
     * 2. During DFS, every visited node should be set as '0' to mark as visited
     * node.
     * 3. Count the number of root nodes that trigger DFS, this number would be the
     * number of islands since each DFS starting at some root identifies an island.
     */
    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    dfs(grid, r, c);
                }
            }
        }

        return num_islands;
    }
}
