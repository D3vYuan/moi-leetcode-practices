package com.example.solution.Q001_Q500;

import java.util.Arrays;

public class P452_Minimum_Number_Of_Arrows_Burst_Balloons {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Sort the balloons by end coordinate x_end.
     * 2. Initiate the end coordinate of a balloon which ends first: first_end =
     * points[0][1].
     * 3. Initiate the number of arrows: arrows = 1.
     * 4. Iterate over all balloons:
     * a. If the balloon starts after first_end:
     * a1. Increase the number of arrows by one.
     * a2. Set first_end to be equal to the end of the current balloon.
     * 5. Return arrows.
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0)
            return 0;

        // sort by x_end
        Arrays.sort(points, (o1, o2) -> {
            // We can't simply use the o1[1] - o2[1] trick, as this will cause an
            // integer overflow for very large or small values.
            if (o1[1] == o2[1])
                return 0;
            if (o1[1] < o2[1])
                return -1;
            return 1;
        });

        int arrows = 1;
        int xStart, xEnd, firstEnd = points[0][1];
        for (int[] p : points) {
            xStart = p[0];
            xEnd = p[1];

            // If the current balloon starts after the end of another one,
            // one needs one more arrow
            if (firstEnd < xStart) {
                arrows++;
                firstEnd = xEnd;
            }
        }

        return arrows;
    }
}
