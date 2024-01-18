package com.example.solution.Q501_Q1000;

public class P661_Image_Smoother {
    /**
     * Reference:
     * https://leetcode.com/problems/image-smoother/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation
     * 1. Thus, to compute the smooth_img[i][j], we may need to read the following
     * cells from the img matrix.
     * a. img[i][j], the cell itself.
     * b. img[i - 1][j - 1], the cell that shares the top-left corner with this
     * cell.
     * c. img[i - 1][j], the cell that shares the top edge with this cell.
     * d. img[i - 1][j + 1], the cell that shares the top-right corner with this
     * cell.
     * e. img[i][j - 1], the cell that shares the left edge with this cell.
     * f. img[i][j + 1], the cell that shares the right edge with this cell.
     * g. img[i + 1][j - 1], the cell that shares the bottom-left corner with this
     * cell.
     * h. img[i + 1][j], the cell that shares the bottom edge with this cell.
     * i. img[i + 1][j + 1], the cell that shares the bottom-right corner with this
     * cell.
     * 
     * Strategy
     * 1. Save the dimensions of the image. Store the number of rows in m, and the
     * number of columns in n, as convention used in the problem statement as well.
     * 2. Create a new image of the same dimension as the input image. Let's call
     * this new image smooth_img. Initialize all the cells of the smooth_img with 0.
     * 3. Iterate over the cells of the image. Let's call the current cell
     * img[i][j].
     * a. Initialize two integer variables sum and count to 0.
     * b. Iterate over all plausible nine indices (x, y). The (x, y) are
     * b1. (i - 1, j - 1)
     * b2. (i - 1, j)
     * b3. (i - 1, j + 1)
     * b4. (i, j - 1)
     * b5. (i, j)
     * b6. (i, j + 1)
     * b7. (i + 1, j - 1)
     * b8. (i + 1, j)
     * b9. (i + 1, j + 1)
     * c. If index (x, y) is valid, then add the value of img[x][y] to sum, and
     * increment count by 1.
     * d. In smooth_img[i][j], store the rounded down value of sum / count.
     * 4. Return the smooth_img.
     */

    public int[][] imageSmoother(int[][] img) {
        // Save the dimensions of the image.
        int m = img.length;
        int n = img[0].length;

        // Create a new image of the same dimension as the input image.
        int[][] smoothImg = new int[m][n];

        // Iterate over the cells of the image.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Initialize the sum and count
                int sum = 0;
                int count = 0;

                // Iterate over all plausible nine indices.
                for (int x = i - 1; x <= i + 1; x++) {
                    for (int y = j - 1; y <= j + 1; y++) {
                        // If the indices form valid neighbor
                        if (0 <= x && x < m && 0 <= y && y < n) {
                            sum += img[x][y];
                            count += 1;
                        }
                    }
                }

                // Store the rounded down value in smoothImg[i][j].
                smoothImg[i][j] = sum / count;
            }
        }

        // Return the smooth image.
        return smoothImg;
    }
}
