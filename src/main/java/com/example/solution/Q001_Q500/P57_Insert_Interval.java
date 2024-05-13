package com.example.solution.Q001_Q500;

import java.util.ArrayList;
import java.util.List;

public class P57_Insert_Interval {
    /**
     * Reference:
     * https://leetcode.com/problems/insert-interval/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize variables n and i to store the size of intervals and the
     * current index, respectively, and an empty array res to store the result.
     * 2. Case 1: No Overlap Before Insertion:
     * a. Loop through intervals while i is less than n and the current interval's
     * endpoint (intervals[i][1]) is less than the new interval's start point
     * (newInterval[0]).
     * b. Add the current interval from intervals to the res array.
     * c. Increment i to move to the next interval.
     * 3. Case 2: Overlap and Merge:
     * a. Loop through intervals while i is less than n and the new interval's
     * endpoint (newInterval[1]) is greater than or equal to the current interval's
     * start point (intervals[i][0]).
     * b. Update the newInterval's start point to the minimum of its current start
     * and the current interval's start.
     * c. Update the newInterval's endpoint to the maximum of its current end and
     * the current interval's end.
     * d. This essentially merges overlapping intervals into a single larger
     * interval.
     * e. Increment i to move to the next interval.
     * 4. Add the updated newInterval to the res array, representing the merged
     * interval.
     * 5. Case 3: No overlap after insertion:
     * a. Loop through the remaining intervals (from index i) and add them to the
     * res array.
     * b. This includes intervals that occur after the new interval and those that
     * don't overlap, as they have already been correctly inserted in the previous
     * iterations (previous two cases).
     * 6. Return the res array containing all intervals with the new interval
     * inserted correctly.
     * 
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length, i = 0;
        List<int[]> res = new ArrayList<>();

        // Case 1: No overlapping before merging intervals
        while (i < n && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]);
            i++;
        }

        // Case 2: Overlapping and merging intervals
        while (i < n && newInterval[1] >= intervals[i][0]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res.add(newInterval);

        // Case 3: No overlapping after merging newInterval
        while (i < n) {
            res.add(intervals[i]);
            i++;
        }

        // Convert List to array
        return res.toArray(new int[res.size()][]);
    }
}
