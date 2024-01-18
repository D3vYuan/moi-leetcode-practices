package com.example.solution.Q1001_Q1500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class P1235_Maximum_Profit_Job_Scheduling {

    /**
     * Reference:
     * https://leetcode.com/problems/maximum-profit-in-job-scheduling/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation:
     * 1. Two jobs A and B represented as (startTime, endTime) by (startA, endA) and
     * (startB, endB) are non-conflicting:
     * a. if either job A starts after job B ends which can be represented by
     * condition startA >= endB,
     * b. or if job B starts after job A ends which can be represented by startB >=
     * endA
     * 2. If we schedule the job at index i that ends at endTime[i], then all the
     * jobs which have a startTime before endTime[i] can be discarded.
     * 3. The next job to schedule at index j should have a start time,
     * startTime[j], such that startTime[j] >= endTime[i]
     * 4. There are two key characteristics of this problem that we should take note
     * of at this time.
     * a. First, a job cannot be scheduled if a conflicting job has already been
     * scheduled
     * b. Second, the problem asks us to maximize the profit by scheduling
     * non-conflicting jobs.
     * 
     * Strategy:
     * 1. Store the startTime, endTime and profit of each job in jobs.
     * 2. Sort the jobs according to their starting time.
     * 3. Iterate over jobs from left to right, where position is the index of the
     * current job. For each job, we must compare two options:
     * a. Skip the current job (earn 0 profit) and move on to consider the job at
     * the index position + 1.
     * b. Schedule the current job (earn profit for the current job) and move on to
     * the next non-conflicting job whose index is nextIndex. nextIndex is
     * determined by using binary search in the startTime array.
     * 4. Return the maximum profit of the two choices and record this profit in the
     * array memo (memoization).
     */

    // maximum number of jobs are 50000
    int[] memo = new int[50001];

    private int findNextJob(int[] startTime, int lastEndingTime) {
        int start = 0;
        int end = startTime.length - 1;
        int nextIndex = startTime.length;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (startTime[mid] >= lastEndingTime) {
                nextIndex = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return nextIndex;
    }

    private int findMaxProfit(List<List<Integer>> jobs, int[] startTime, int n, int position) {
        // 0 profit if we have already iterated over all the jobs
        if (position == n) {
            return 0;
        }

        // return result directly if it's calculated
        if (memo[position] != -1) {
            return memo[position];
        }

        // nextIndex is the index of next non-conflicting job
        int nextIndex = findNextJob(startTime, jobs.get(position).get(1));

        // find the maximum profit of our two options: skipping or scheduling the
        // current job
        int skipping = findMaxProfit(jobs, startTime, n, position + 1);
        int scheduling = jobs.get(position).get(2) + findMaxProfit(jobs, startTime, n, nextIndex);

        int maxProfit = Math.max(skipping, scheduling);

        // return maximum profit and also store it for future reference (memoization)
        return memo[position] = maxProfit;
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<List<Integer>> jobs = new ArrayList<>();

        // marking all values to -1 so that we can differentiate
        // if we have already calculated the answer or not
        Arrays.fill(memo, -1);

        // storing job's details into one list
        // this will help in sorting the jobs while maintaining the other parameters
        int length = profit.length;
        for (int i = 0; i < length; i++) {
            List<Integer> currentJob = new ArrayList<>();
            currentJob.add(startTime[i]);
            currentJob.add(endTime[i]);
            currentJob.add(profit[i]);
            jobs.add(currentJob);
        }

        jobs.sort((Comparator.comparingInt(a -> a.get(0))));

        // binary search will be used in startTime so store it as separate list
        for (int i = 0; i < length; i++) {
            startTime[i] = jobs.get(i).get(0);
        }

        return findMaxProfit(jobs, startTime, length, 0);
    }
}
