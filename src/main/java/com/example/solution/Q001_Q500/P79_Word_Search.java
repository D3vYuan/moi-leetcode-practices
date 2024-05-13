package com.example.solution.Q001_Q500;

public class P79_Word_Search {
    /**
     * Reference:
     * https://leetcode.com/problems/word-search/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. At the beginning, first we check if we reach the bottom case of the
     * recursion, where the word to be matched is empty, i.e. we have already found
     * the match for each prefix of the word.
     * 2. We then check if the current state is invalid, either the position of the
     * cell is out of the boundary of the board or the letter in the current cell
     * does not match with the first letter of the word.
     * 3. If the current step is valid, we then start the exploration of
     * backtracking with the strategy of DFS. First, we mark the current cell as
     * visited, e.g. any non-alphabetic letter will do. Then we iterate through the
     * four possible directions, namely up, right, down and left. The order of the
     * directions can be altered, to one's preference.
     * 4. At the end of the exploration, we revert the cell back to its original
     * state. Finally we return the result of the exploration.
     */
    private char[][] board;
    private int ROWS;
    private int COLS;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.ROWS = board.length;
        this.COLS = board[0].length;

        for (int row = 0; row < this.ROWS; ++row)
            for (int col = 0; col < this.COLS; ++col)
                if (this.backtrack(row, col, word, 0))
                    return true;
        return false;
    }

    protected boolean backtrack(int row, int col, String word, int index) {
        /* Step 1). check the bottom case. */
        if (index >= word.length())
            return true;

        /* Step 2). Check the boundaries. */
        if (row < 0 ||
                row == this.ROWS ||
                col < 0 ||
                col == this.COLS ||
                this.board[row][col] != word.charAt(index))
            return false;

        /* Step 3). explore the neighbors in DFS */
        boolean ret = false;
        // mark the path before the next exploration
        this.board[row][col] = '#';

        int[] rowOffsets = { 0, 1, 0, -1 };
        int[] colOffsets = { 1, 0, -1, 0 };
        for (int d = 0; d < 4; ++d) {
            ret = this.backtrack(
                    row + rowOffsets[d],
                    col + colOffsets[d],
                    word,
                    index + 1);
            if (ret)
                break;
        }

        /* Step 4). clean up and return the result. */
        this.board[row][col] = word.charAt(index);
        return ret;
    }
}
