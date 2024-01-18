package com.example.solution.Q1501_Q2000;

public class P1578_Minimum_Time_Make_Rope_Colorful {
    /**
     * Reference:
     * https://leetcode.com/problems/minimum-time-to-make-rope-colorful/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation:
     * 1. we group the balloons on the rope by their colors. Notice that there
     * shouldn't be two consecutive balloons having the same color, thus we can only
     * keep at most one balloon from each group.
     * 2. Since we are looking for the minimum removal time, it means that we should
     * keep the balloon with the largest removal time among each group, and remove
     * the rest balloons of the same colors but with a smaller removal time
     * 3. Among each group, we calculate the total removal time currTotal and the
     * removal time currMax of the balloon that has the maximum removal time.
     * Therefore, we can get the minimum removal time of this group by keeping the
     * balloon with the largest removal time and removing the rest, that is: t =
     * currTotal - currMax
     * 4. We will calculate the removal time t for all of the groups and add them up
     * to make the minimum removal time totalTime
     * 
     * Strategy:
     * 1. Initalize totalTime, left and right as 0.
     * 2. Iterate over balloons, for each group of balloons, we record the total
     * removal time as currTotal and the maximum removal time as currMax.
     * 3. While the balloon indexed at right has the same color as the balloon
     * indexed at left, we update currTotal and currMax, and increment right by 1.
     * 4. Otherwise, it means that we have finished iterating this group, we should
     * add the removal time for this group currTotal - currMax to totalTime, and
     * reset left as right.
     */
    public int minCost(String colors, int[] neededTime) {
        // Initalize two pointers i, j.
        int totalTime = 0;
        int i = 0, j = 0;

        while (i < neededTime.length && j < neededTime.length) {
            int currentTotal = 0, currentMax = 0;

            // Find all the balloons having the same color as the
            // balloon indexed at i, record the total removal time
            // and the maximum removal time.
            while (j < neededTime.length && colors.charAt(i) == colors.charAt(j)) {
                currentTotal += neededTime[j];
                currentMax = Math.max(currentMax, neededTime[j]);
                j++;
            }

            // Once we reach the end of the current group, add the cost of
            // this group to total_time, and reset two pointers.
            totalTime += currentTotal - currentMax;
            i = j;
        }
        return totalTime;
    }
}
