package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum TransverseDirection {
    UP, DOWN
}

public class MatrixZigZagTransversal {
    class CellRecord {
        private int row;
        private int col;
        private int value;

        public CellRecord(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    int[][] myMatrix;

    public MatrixZigZagTransversal() {
        // buildMatrix(4, 4);
        buildMatrix(5, 4);
    }

    private void printMatrix() {
        for (int row = 0; row < myMatrix.length; row++) {
            int[] cols = myMatrix[row];
            String colValues = String.join(",", Arrays.stream(cols).mapToObj(String::valueOf).toArray(String[]::new));
            System.out.println(String.format("Processing: Row [%d] - %s", row, colValues));
        }
    }

    private void buildMatrix(int rows, int cols) {
        myMatrix = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int arrayValue = row * cols + col + 1;
                myMatrix[row][col] = arrayValue;
            }
        }

        // printMatrix();
    }

    public void transverse() {
        // Since it a zig zag, once it hit the walls (ie. x < 0 || y < 0 || x >
        // matrix.length || y > matrix.length)
        // Tranverse Direction
        // Up Direction: x-- ; y++
        // Down Direction: x++ ; y--
        //
        // In the event of overflow (one parameters hit the wall), add 1 to the
        // counterparts
        // x overflow: add +1 to y
        // y overflow: add +1 to x

        TransverseDirection direction = TransverseDirection.DOWN;

        int initialRow = 0;
        int initialCol = 0;

        int lastRow = myMatrix.length - 1;
        int lastCol = myMatrix[lastRow].length - 1;

        int currentRow = initialRow;
        int currentCol = initialCol;

        List<CellRecord> results = new ArrayList<>();
        while (currentRow != lastRow || currentCol != lastCol) {
            String directionDescription = direction.ordinal() == 0 ? "UP" : "DOWN";

            int currentValue = myMatrix[currentRow][currentCol];
            System.out.println(
                    String.format("Processing (%d, %d): Found value [%d] while moving [%s]", currentRow, currentCol,
                            currentValue, directionDescription));
            CellRecord currentCell = new CellRecord(currentRow, currentCol, currentValue);
            results.add(currentCell);

            int nextRow = currentRow;
            int nextCol = currentCol;
            if (direction == TransverseDirection.UP) {
                nextRow = currentRow - 1;
                nextCol = currentCol + 1;
            } else if (direction == TransverseDirection.DOWN) {
                nextRow = currentRow + 1;
                nextCol = currentCol - 1;
            } else {
                System.out.println("Processing: Unknown Transverse Direction");
            }

            boolean rowOverflow = nextRow < 0 || nextRow > lastRow;
            boolean colOverflow = nextCol < 0 || nextCol > lastCol;

            if (rowOverflow && colOverflow) {
                // Both Row and Col overflows
                // If direction is UP, next direction is DOWN so increase row (add + 1 to row)
                // If direction is DOWN, next direction is UP so increase col (add + 1 to col)
                nextRow = direction == TransverseDirection.UP ? currentRow + 1 : currentRow;
                nextCol = direction == TransverseDirection.UP ? currentCol : currentCol + 1;
            } else if (rowOverflow) {
                System.out.println(
                        String.format(
                                "Processing (%d, %d): next [ROW] overflows (%d, %d) while moving [%s]...switching direction",
                                currentRow, currentCol,
                                nextRow, nextCol, directionDescription));
                nextRow = currentRow;
                nextCol = currentCol + 1;

            } else if (colOverflow) {
                System.out.println(
                        String.format(
                                "Processing (%d, %d): next [COL] overflows (%d, %d) while moving [%s]...switching direction",
                                currentRow, currentCol,
                                nextRow, nextCol, directionDescription));
                nextRow = currentRow + 1;
                nextCol = currentCol;
            }

            direction = rowOverflow || colOverflow ? TransverseDirection.values()[1 - direction.ordinal()] : direction;

            currentRow = nextRow;
            currentCol = nextCol;
            System.out.println("-- next --");
        }

        int currentValue = myMatrix[currentRow][currentCol];
        System.out.println(
                String.format("Processing (%d, %d): Adding Last Cell value [%d]", currentRow, currentCol,
                        currentValue));
        CellRecord record = new CellRecord(currentRow, currentCol, currentValue);
        results.add(record);

        showZigZagValues(results);
    }

    private void showZigZagValues(List<CellRecord> records) {
        if (records == null || records.isEmpty()) {
            return;
        }

        for (CellRecord record : records) {
            System.out.println(String.format("(%d, %d)  %d", record.row, record.col, record.value));
        }
    }

    public static void main(String[] args) {
        MatrixZigZagTransversal application = new MatrixZigZagTransversal();
        application.transverse();
    }
}
