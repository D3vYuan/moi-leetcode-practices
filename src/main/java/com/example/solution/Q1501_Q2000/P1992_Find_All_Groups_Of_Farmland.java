package com.example.solution.Q1501_Q2000;

import java.util.ArrayList;
import java.util.List;

public class P1992_Find_All_Groups_Of_Farmland {
    /**
     * Reference:
     * https://leetcode.com/problems/find-all-groups-of-farmland/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Iterate over each cell in the matrix land, and for each cell (row1, col1),
     * do the following:
     * a. If the cell is a farmland cell, i.e. land[row1][col1] = 1, and hasn't been
     * visited yet (visited[row1][col1] = 0), start DFS from (row1, col1). Also,
     * keep two variables row2 and col2 as the coordinates of the bottom right
     * corner initialized with 0 each.
     * b. In the DFS, mark the current coordinates as visited and update the values
     * of row2 and col2 to the maximum compared with the current coordinates.
     * c. Traverse over the four neighbors and apply DFS if the neighbor is within
     * the matrix boundary, a farmland cell, and hasn't been visited yet.
     * d. When the DFS is complete, store the top left coordinate as (row1, col1)
     * and the bottom right as (row2, col2) in the list ans.
     * 2. Return ans.
     */
    // The four directions in which traversal will be done.
    int[][] dirs = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
    // Global variables with 0 value initially.
    int row2, col2;

    // Returns true if the coordinate is within the boundary of the matrix.
    private boolean isWithinFarm(int x, int y, int N, int M) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private void DFS(int[][] land, boolean[][] visited, int x, int y) {
        visited[x][y] = true;
        // Maximum x and y for the bottom right cell.
        row2 = Math.max(row2, x);
        col2 = Math.max(col2, y);

        for (int[] dir : dirs) {
            // Neighbor cell coordinates.
            int newX = x + dir[0], newY = y + dir[1];

            // If the neighbor is within the matrix and is a farmland cell and is not
            // visited yet.
            if (isWithinFarm(newX, newY, land.length, land[0].length) && !visited[newX][newY]
                    && land[newX][newY] == 1) {
                DFS(land, visited, newX, newY);
            }
        }
    }

    public int[][] findFarmland(int[][] land) {
        boolean[][] visited = new boolean[land.length][land[0].length];
        List<int[]> ans = new ArrayList<>();

        for (int row1 = 0; row1 < land.length; row1++) {
            for (int col1 = 0; col1 < land[0].length; col1++) {
                if (land[row1][col1] == 1 && !visited[row1][col1]) {
                    row2 = 0;
                    col2 = 0;

                    DFS(land, visited, row1, col1);

                    int[] arr = new int[] { row1, col1, row2, col2 };
                    ans.add(arr);
                }
            }
        }

        return ans.stream().toArray(int[][]::new);
    }
}
