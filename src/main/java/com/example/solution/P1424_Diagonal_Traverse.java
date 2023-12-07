package com.example.solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class P1424_Diagonal_Traverse {
    /**
     * Reference:
     * https://leetcode.com/problems/diagonal-traverse-ii/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation
     * Each square is a node, and we can imagine each node having an edge to the
     * squares below and to the right
     * 
     * A node with identifier x has edges to nodes with identifier x + 1. If we
     * consider the top-left square 0, 0 as a "source" node, then each square's
     * identifier is exactly equal to its distance from the source. This allows us
     * to visit the diagonals in order using BFS!
     * 
     * The level-wise nature of BFS will ensure that we visit all squares in a
     * diagonal with identifier x before we visit any square in a diagonal with
     * identifier x + 1. This means we will visit the diagonals in the correct
     * order. Because we add the square row + 1, col before row, col + 1, we will
     * also traverse over each diagonal in the correct order as well. This means our
     * entire BFS will traverse the input in the same order as the answer, allowing
     * us to solve the problem in one pass!
     * 
     * Strategy
     * 1. Initialize a queue with (0, 0) and the answer list ans.
     * 2. While queue is not empty:
     * a. Remove (row, col) from queue.
     * b. Add nums[row][col] to ans.
     * c. If col == 0 and row + 1 is in bounds, add (row + 1, col) to queue.
     * d. If col + 1 is in bounds for the current row, add (row, col + 1) to queue.
     * 3. Return ans.
     */
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.offer(new ImmutablePair(0, 0));
        List<Integer> ans = new ArrayList<>();

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> p = queue.poll();
            int row = p.getKey();
            int col = p.getValue();
            ans.add(nums.get(row).get(col));

            if (col == 0 && row + 1 < nums.size()) {
                queue.offer((new ImmutablePair<Integer, Integer>(row + 1, col)));
            }

            if (col + 1 < nums.get(row).size()) {
                queue.offer(new ImmutablePair<Integer, Integer>(row, col + 1));
            }
        }

        int[] result = new int[ans.size()];
        int i = 0;
        for (int num : ans) {
            result[i] = num;
            i ++;
        }

        return result;
    }
}
