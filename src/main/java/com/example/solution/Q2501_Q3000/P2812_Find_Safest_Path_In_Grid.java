package com.example.solution.Q2501_Q3000;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P2812_Find_Safest_Path_In_Grid {
    /**
     * Reference:
     * https://leetcode.com/problems/find-the-safest-path-in-a-grid/?envType=daily-question&envId=2024-05-05
     * 
     * Explanation:
     * 1. Perform a breadth-first search to compute the safeness factor for each
     * cell, leveraging the fact that the first time a cell is visited, it
     * represents the minimum distance from the nearest thief.
     * 2. Apply binary search to find the maximum safeness factor for which a path
     * exists from the source to the destination cell.
     * 
     * Strategy:
     * isValidCell Method
     * 1. Take the grid, row i, and column j as input.
     * 2. Get the size of the grid, denoted by n.
     * 3. Check if the cell at (i, j) is within the grid boundaries.
     * 4. Return true if the cell is valid, false otherwise.
     * 
     * isValidSafeness Method
     * 1. Take the grid and the minimum safeness value as input.
     * 2. Initialize variables:
     * a. n as the size of the grid.
     * b. q as a queue of coordinates to perform the breadth-first search (BFS).
     * c. visited as a 2-D array to mark visited cells.
     * 3. Check if the source and destination cells satisfy the minimum safeness.
     * 4. Perform a breadth-first search (BFS) to find a valid path:
     * a. Initialize a queue q to contain the coordinates.
     * b. Add the source cell (0, 0) to the queue.
     * c. While the queue is not empty:
     * c1. Retrieve the front element curr from the queue.
     * c2. Explore neighboring cells in all directions:
     * c2a. If the neighboring cell is valid, unvisited and has a safeness value
     * greater than or equal to the minimum safeness value:
     * c2b. Mark the cell as visited and push it to the queue.
     * d. If a valid path is found, return true.
     * 5. Return false if no valid path is found.
     * 
     * Signature function maximumSafenessFactor
     * 1. Initialize a queue q to store the positions of thieves.
     * 2. Mark thieves as 0 and empty cells as -1, and push thieves to the queue.
     * 3. Perform BFS to calculate the safeness factor for each cell:
     * a. While the queue is not empty:
     * a1. Retrieve the front element curr from the queue.
     * a2. Explore neighboring cells:
     * a2a. If the neighboring cell is valid and unvisited (safeness factor = -1):
     * a2b. Update its safeness factor and push it to the queue.
     * 4. Perform a binary search for the maximum safeness factor:
     * a. Initialize start and end variables.
     * b. Initialize res to store the maximum safeness value.
     * c. Loop through the grid to find the maximum safeness factor and assign it to
     * end.
     * d. While start is less than or equal to end:
     * d1. Calculate mid.
     * d2. Check if a valid safeness exists for mid using isValidSafeness method.
     * d3. Update res if valid safeness is found.
     * d4. Update start or end based on the result of isValidSafeness.
     * 5. Return the maximum safeness factor res.
     */

    // Directions for moving to neighboring cells: right, left, down, up
    final int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] mat = new int[n][n];
        Queue<int[]> multiSourceQueue = new LinkedList<>();

        // To make modifications and navigation easier, the grid is converted into a 2-d
        // array.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    // Push thief coordinates to the queue
                    multiSourceQueue.add(new int[] { i, j });
                    // Mark thief cell with 0
                    mat[i][j] = 0;
                } else {
                    // Mark empty cell with -1
                    mat[i][j] = -1;
                }
            }
        }

        // Calculate safeness factor for each cell using BFS
        while (!multiSourceQueue.isEmpty()) {
            int size = multiSourceQueue.size();
            while (size-- > 0) {
                int[] curr = multiSourceQueue.poll();
                // Check neighboring cells
                for (int[] d : dir) {
                    int di = curr[0] + d[0];
                    int dj = curr[1] + d[1];
                    int val = mat[curr[0]][curr[1]];
                    // Check if the neighboring cell is valid and unvisited
                    if (isValidCell(mat, di, dj) && mat[di][dj] == -1) {
                        // Update safeness factor and push to the queue
                        mat[di][dj] = val + 1;
                        multiSourceQueue.add(new int[] { di, dj });
                    }
                }
            }
        }

        // Binary search for maximum safeness factor
        int start = 0;
        int end = 0;
        int res = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Set end as the maximum safeness factor possible
                end = Math.max(end, mat[i][j]);
            }
        }

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (isValidSafeness(mat, mid)) {
                // Store valid safeness and search for larger ones
                res = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return res;
    }

    // Check if a path exists with given minimum safeness value
    private boolean isValidSafeness(int[][] grid, int minSafeness) {
        int n = grid.length;

        // Check if the source and destination cells satisfy minimum safeness
        if (grid[0][0] < minSafeness || grid[n - 1][n - 1] < minSafeness) {
            return false;
        }

        Queue<int[]> traversalQueue = new LinkedList<>();
        traversalQueue.add(new int[] { 0, 0 });
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;

        // Breadth-first search to find a valid path
        while (!traversalQueue.isEmpty()) {
            int[] curr = traversalQueue.poll();
            if (curr[0] == n - 1 && curr[1] == n - 1) {
                return true; // Valid path found
            }
            // Check neighboring cells
            for (int[] d : dir) {
                int di = curr[0] + d[0];
                int dj = curr[1] + d[1];
                // Check if the neighboring cell is valid, unvisited and satisfying minimum
                // safeness
                if (isValidCell(grid, di, dj) && !visited[di][dj] && grid[di][dj] >= minSafeness) {
                    visited[di][dj] = true;
                    traversalQueue.add(new int[] { di, dj });
                }
            }
        }

        return false; // No valid path found
    }

    // Check if a given cell lies within the grid
    private boolean isValidCell(int[][] mat, int i, int j) {
        int n = mat.length;
        return i >= 0 && j >= 0 && i < n && j < n;
    }
}
