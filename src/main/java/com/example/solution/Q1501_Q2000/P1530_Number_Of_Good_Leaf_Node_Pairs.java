package com.example.solution.Q1501_Q2000;

public class P1530_Number_Of_Good_Leaf_Node_Pairs {
    /**
     * Reference:
     * https://leetcode.com/problems/filling-bookcase-shelves/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a 2D array memo to cache previous computations, where
     * memo[i][remainingShelfWidth] stores the minimum height of the bookcase
     * containing all books up to the i-th book with remainingShelfWidth width
     * available on the current shelf.
     * 2. Call dpHelper(books, shelfWidth, memo, 0, shelfWidth, 0) to start the
     * dynamic programming process from the first book, with the full shelf width
     * available and initial height set to 0.
     * 3. In dpHelper function:
     * a. Extract the current book's width (currentBook[0]) and height
     * (currentBook[1]).
     * b. Update maxHeightUpdated to be the maximum of maxHeight and currentBook[1].
     * c. If i is the last book:
     * If the current book fits on the remaining space of the current shelf
     * (remainingShelfWidth >= currentBook[0]), return maxHeightUpdated as the
     * height.
     * Otherwise, return maxHeight + currentBook[1] as the height after starting a
     * new shelf.
     * d. If the result is already computed in memo:
     * Return the cached result to avoid redundant calculations.
     * e. Calculate the height for different scenarios:
     * Option 1: Place the current book on a new shelf:
     * Compute height by adding the height of the bookcase for the rest of the books
     * starting from i + 1 with a new shelf width (shelfWidth - currentBook[0]) and
     * updated height (currentBook[1]).
     * Option 2: Place the current book on the current shelf:
     * Compute height by adding the height of the bookcase for the rest of the books
     * starting from i + 1 with updated remaining shelf width (remainingShelfWidth -
     * currentBook[0]) and updated maximum height (maxHeightUpdated).
     * Store the minimum height between the two options in
     * memo[i][remainingShelfWidth] to use it for future computations.
     * f. Return the cached result from memo[i][remainingShelfWidth].
     * 4. Return the result from dpHelper for the initial call to get the minimum
     * height of the bookcase to accommodate all books on the shelves.
     */

    public int minHeightShelves(int[][] books, int shelfWidth) {
        // Cache to store previous computations
        int[][] memo = new int[books.length][shelfWidth + 1];
        return dpHelper(books, shelfWidth, memo, 0, shelfWidth, 0);
    }

    private int dpHelper(
            int[][] books,
            int shelfWidth,
            int[][] memo,
            int i,
            int remainingShelfWidth,
            int maxHeight) {
        int[] currentBook = books[i];
        int maxHeightUpdated = Math.max(maxHeight, currentBook[1]);
        if (i == books.length - 1) {
            // For the last book, store it in the current shelf if possible,
            // or start a new shelf with it
            if (remainingShelfWidth >= currentBook[0])
                return maxHeightUpdated;
            return maxHeight + currentBook[1];
        }
        // Return answer if already computed
        if (memo[i][remainingShelfWidth] != 0) {
            return memo[i][remainingShelfWidth];
        } else {
            // Calculate the height of the bookcase if we put the current book on the new
            // shelf
            int option1Height = maxHeight +
                    dpHelper(
                            books,
                            shelfWidth,
                            memo,
                            i + 1,
                            shelfWidth - currentBook[0],
                            currentBook[1]);
            if (remainingShelfWidth >= currentBook[0]) {
                // Calculate height of the bookcase if we put the current book on the current
                // shelf
                int option2Height = dpHelper(
                        books,
                        shelfWidth,
                        memo,
                        i + 1,
                        remainingShelfWidth - currentBook[0],
                        maxHeightUpdated);
                // Store result in cache
                memo[i][remainingShelfWidth] = Math.min(
                        option1Height,
                        option2Height);
                return memo[i][remainingShelfWidth];
            }
            // Store result in cache
            memo[i][remainingShelfWidth] = option1Height;
            return memo[i][remainingShelfWidth];
        }
    }
}