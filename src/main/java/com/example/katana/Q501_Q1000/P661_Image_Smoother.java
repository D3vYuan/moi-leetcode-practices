package com.example.katana.Q501_Q1000;

public class P661_Image_Smoother {
    public int computeAverage(int[][] img, int row, int col) {
        int[][] directions = new int[][] { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 },
                { -1, -1 } }; // up, upper-right, right, bottom-right, down, bottom-left, left, upper-left

        int matRow = img.length;
        int matCol = img[0].length;

        int totalSum = 0;
        int totalCount = 0;
        for (int directionIdx = 0; directionIdx < directions.length; directionIdx++) {
            int[] currentDirection = directions[directionIdx];
            int currentRowOffset = currentDirection[0];
            int currentColOffset = currentDirection[1];

            // ensure no out of bound
            int neighbouringRow = row + currentRowOffset;
            int neighbouringCol = col + currentColOffset;

            if (neighbouringRow < 0 || neighbouringRow >= matRow || neighbouringCol < 0 || neighbouringCol >= matCol) {
                continue;
            }

            // System.out.println(String.format("Adding (%d, %d): Neighbour (%d, %d) - %d",
            // row, col, neighbouringRow,
            // neighbouringCol, img[neighbouringRow][neighbouringCol]));
            totalSum += img[neighbouringRow][neighbouringCol];
            totalCount++;
        }

        totalSum += img[row][col];
        totalCount++;
        // System.out.println(String.format("Average (%d, %d): Sum (%d) | Count (%d) |
        // Average (%d)", row, col, totalSum,
        // totalCount, (int) Math.floor(totalSum / totalCount)));
        return (int) Math.floor(totalSum / totalCount);

    }

    public int[][] imageSmoother(int[][] img) {
        int[][] smootherImage = new int[img.length][img[0].length];
        for (int row = 0; row < img.length; row++) {
            for (int col = 0; col < img[row].length; col++) {
                int average = computeAverage(img, row, col);
                smootherImage[row][col] = average;
                // System.out.println(String.format("--"));
            }
        }

        return smootherImage;
    }
}
