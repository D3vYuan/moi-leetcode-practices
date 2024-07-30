package com.example.solution.Q501_Q1000;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class P502_IPO {
    /**
     * Reference:
     * https://leetcode.com/problems/ipo/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Sort the projects by increasing capital. Keep a pointer ptr to the first
     * unavailable project in the sorted array.
     * 2. Maintain a priority queue for the profits of available projects.
     * Initially, the priority queue is empty.
     * 3. Do the following k times:
     * a. Add to the priority queue the profits of the newly available projects. We
     * move the pointer through the sorted array when new projects become available.
     * b. If the priority queue is empty, terminate the algorithm.
     * c. The maximum value in the priority queue is the profit of the project we
     * will start now. Increase our capital by this value. Delete it so since we can
     * not use it anymore.
     */
    class Project implements Comparable<Project> {
        int capital, profit;

        public Project(int capital, int profit) {
            this.capital = capital;
            this.profit = profit;
        }

        public int compareTo(Project project) {
            return capital - project.capital;
        }
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        Project[] projects = new Project[n];
        for (int i = 0; i < n; i++) {
            projects[i] = new Project(capital[i], profits[i]);
        }
        Arrays.sort(projects);
        // PriorityQueue is a min heap, but we need a max heap, so we use
        // Collections.reverseOrder()
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(n, Collections.reverseOrder());
        int ptr = 0;
        for (int i = 0; i < k; i++) {
            while (ptr < n && projects[ptr].capital <= w) {
                q.add(projects[ptr++].profit);
            }
            if (q.isEmpty()) {
                break;
            }
            w += q.poll();
        }
        return w;
    }
}
