package com.example.solution.Q1001_Q1500;

public class P1266_Minimum_Time_Visiting_All_Points {

    /**
     * Reference:
     * https://leetcode.com/problems/minimum-time-visiting-all-points/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation
     * 1. we have three options:
     * a. Move 1 unit vertically toward our target.
     * b. Move 1 unit horizontally toward our target.
     * c. Move diagonally toward our target, which is 1 unit vertically and then 1
     * unit horizontally.
     * Notice that the 3rd option of moving diagonally is actually the combination
     * of the first two options. Because all three options take the same amount of
     * time, moving diagonally is the most efficient option in terms of distance per
     * time.
     * 2. aim to move diagonally as much as possible.
     * 3. When does moving diagonally stop saving time? If either currX = targetX or
     * currY = targetY, it means we are already lined up to our target in one of the
     * directions. Thus, moving diagonally will not provide any benefit over moving
     * horizontally or vertically. Some of the sqrt{2} distance from moving
     * diagonally will be wasted.
     * 4. Thus, we should only move diagonally until we are lined up in one
     * direction, then make up the remaining distance with vertical or horizontal
     * movements.
     * 
     * Strategy
     * 1. Initialize the answer ans = 0.
     * 2. Iterate i from 0 until points.length - 1:
     * a. Set currX to points[i][0] and currY to points[i][1]
     * b. Set targetX to points[i + 1][0] and targetY to points[i + 1][1]
     * c. Add the maximum of abs(targetX - currX) and abs(targetY - currY) to ans.
     * 3. Return ans.
     */

    public int minTimeToVisitAllPoints(int[][] points) {
        int ans = 0;
        for (int i = 0; i < points.length - 1; i++) {
            int point1x = points[i][0];
            int point1y = points[i][1];

            int point2x = points[i + 1][0];
            int point2y = points[i + 1][1];

            ans += Math.max(Math.abs(point1x - point2x), Math.abs(point1y - point2y));
        }
        return ans;
    }

}
