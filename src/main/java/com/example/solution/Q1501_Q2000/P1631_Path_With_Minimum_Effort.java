package com.example.solution.Q1501_Q2000;

import java.util.Arrays;
import java.util.PriorityQueue;

class Cell {
    int x;
    int y;
    Integer difference;

    Cell(int x, int y, Integer difference) {
        this.x = x;
        this.y = y;
        this.difference = difference;
    }
}

public class P1631_Path_With_Minimum_Effort {
    /**
     * Reference:
     * https://leetcode.com/problems/path-with-minimum-effort/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. We use a differenceMatrix of size row⋅col where each cell represents the
     * minimum effort required to reach that cell from all the possible paths.
     * Also, initialize we all the cells in the differenceMatrix to infinity
     * (MAX_INT) since none of the cells are reachable initially.
     * 2. As we start visiting each cell, all the adjacent cells are now reachable.
     * We update the absolute difference between the current cell and adjacent cells
     * in the differenceMatrix. At the same time, we also push all the adjacent
     * cells in a priority queue. The priority queue holds all the reachable cells
     * sorted by its value in differenceMatrix, i.e the cell with minimum absolute
     * difference with its adjacent cells would be at the top of the queue.
     * 3. We begin by adding the source cell (x=0, y=0) in the queue. Now, until we
     * have visited the destination cell or the queue is not empty, we visit each
     * cell in the queue sorted in the order of priority. The less is the difference
     * value(absolute difference with adjacent cell) of a cell, the higher is its
     * priority.
     * a. Get the cell from the top of the queue curr and visit the current cell.
     * b. For each of the 4 cells adjacent to the current cell, calculate the
     * maxDifference which is the maximum absolute difference to reach the adjacent
     * cell (adjacentX, adjacentY) from current cell (curr.x, curr.y).
     * 4. At the end, the value at differenceMatrix[row - 1][col - 1] is the minimum
     * effort required to reach the destination cell (row-1,col-1).
     */

    int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // right, down, left, up

    public int minimumEffortPath(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        int[][] differenceMatrix = new int[row][col];
        for (int[] diffferenceRow : differenceMatrix) {
            Arrays.fill(diffferenceRow, Integer.MAX_VALUE);
        }
        differenceMatrix[0][0] = 0;
        PriorityQueue<Cell> queue = new PriorityQueue<>((a, b) -> a.difference.compareTo(b.difference));

        boolean[][] visited = new boolean[row][col];
        queue.add(new Cell(0, 0, differenceMatrix[0][0]));

        while (!queue.isEmpty()) {
            Cell currentCell = queue.poll();
            visited[currentCell.x][currentCell.y] = true;
            if (currentCell.x == row - 1 && currentCell.y == col - 1) {
                return currentCell.difference;
            }
            for (int[] direction : directions) {
                int adjacentX = currentCell.x + direction[0];
                int adjacentY = currentCell.y + direction[1];
                if (isValidCell(adjacentX, adjacentY, row, col) && !visited[adjacentX][adjacentY]) {
                    int currentDifference = Math
                            .abs(heights[adjacentX][adjacentY] - heights[currentCell.x][currentCell.y]);
                    int maxDifference = Math.max(currentDifference, differenceMatrix[currentCell.x][currentCell.y]);
                    if (differenceMatrix[adjacentX][adjacentY] > maxDifference) {
                        differenceMatrix[adjacentX][adjacentY] = maxDifference;
                        queue.add(new Cell(adjacentX, adjacentY, maxDifference));
                    }
                }
            }
        }
        return differenceMatrix[row - 1][col - 1];
    }

    private boolean isValidCell(int x, int y, int row, int col) {
        return x >= 0 && x <= row - 1 && y >= 0 && y <= col - 1;
    }
}
