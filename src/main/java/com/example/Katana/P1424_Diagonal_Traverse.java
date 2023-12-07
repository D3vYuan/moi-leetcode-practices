package com.example.katana;

import java.util.ArrayList;
import java.util.List;

public class P1424_Diagonal_Traverse {
    private void showArray(List<List<Integer>> nums) {
        for (int row = 0; row < nums.size(); row++) {
            for (int col = 0; col < nums.get(row).size(); col++) {
                System.out.print(String.format("(%d, %d) %s", row, col, nums.get(row).get(col)));
            }
            System.out.println();
        }
        System.out.println(String.format("--"));
    }

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int[] diagonalUp = { -1, 1 };
        int[] rowDown = { 1, 0 };
        int[] rowRight = { 0, 1 };

        int[] cell = { 0, 0 };
        List<Integer> traverse = new ArrayList<>();
        int longestArray = Integer.MIN_VALUE;
        // for (int i = 0; i < nums.size(); i++) {
        // if (nums.get(i).size() > longestArray){
        // longestArray = nums.get(i).size();
        // }
        // }
        // System.out.println(String.format("Longest Array: %d", longestArray));
        // showArray(nums);

        // // Move Down
        while (cell[0] < nums.size()) {
            // System.out.println(String.format("Starting: (%d, %d)", cell[0], cell[1]));
            int currentCellRow = cell[0];
            int currentCellCol = cell[1];

            // Navigate Diagonal
            while (currentCellRow >= 0) {
                // System.out.println(String.format("Navigating: (%d, %d)", currentCellRow,
                // currentCellCol));
                List<Integer> currentRow = nums.get(currentCellRow);
                if (currentRow.size() > longestArray) {
                    longestArray = currentRow.size();
                }
                if (currentCellCol < currentRow.size()) {
                    int value = currentRow.get(currentCellCol);
                    traverse.add(value);
                    // System.out.println(String.format("Adding: (%d, %d) - %d", currentCellRow,
                    // currentCellCol, value));
                }
                currentCellRow += diagonalUp[0];
                currentCellCol += diagonalUp[1];
            }

            // Move to Next Row
            cell[0] += rowDown[0];
            cell[1] += rowDown[1];
            // System.out.println(String.format("**"));
        }

        // Revert to previous row and right
        cell[0] = cell[0] - rowDown[0] + rowRight[0];
        cell[1] = cell[1] - rowDown[1] + rowRight[1];

        // Move Right
        while (cell[1] < longestArray) {
            // System.out.println(String.format("Starting: (%d, %d)", cell[0], cell[1]));
            int currentCellRow = cell[0];
            int currentCellCol = cell[1];

            // Navigate Diagonal
            while (currentCellRow >= 0) {
                // System.out.println(String.format("Navigating: (%d, %d)", currentCellRow,
                // currentCellCol));
                List<Integer> currentRow = nums.get(currentCellRow);
                if (currentCellCol < currentRow.size()) {
                    int value = currentRow.get(currentCellCol);
                    // System.out.println(String.format("Adding: (%d, %d) - %d", currentCellRow,
                    // currentCellCol, value));
                    traverse.add(value);
                }
                currentCellRow += diagonalUp[0];
                currentCellCol += diagonalUp[1];
            }

            // Move to Next Row
            cell[0] += rowRight[0];
            cell[1] += rowRight[1];
            // System.out.println(String.format("**"));
        }

        return traverse.stream().mapToInt(Integer::intValue).toArray();
    }
}

/***
 * 
 */
