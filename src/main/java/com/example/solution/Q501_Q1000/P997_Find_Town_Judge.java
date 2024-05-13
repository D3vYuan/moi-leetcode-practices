package com.example.solution.Q501_Q1000;

public class P997_Find_Town_Judge {
    /**
     * Reference:
     * https://leetcode.com/problems/find-the-town-judge/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. The town judge has an outdegree of 0 and an indegree of N - 1 because they
     * trust nobody, and everybody trusts them (except themselves).
     * 2. Therefore, this problem simplifies to calculating the indegree and
     * outdegree for each person and then checking whether or not any of them meet
     * the criteria of the town judge.
     * 3. This is because a town judge must have N - 1 in-going edges, and so if
     * there aren't at least N - 1 edges in total, then it is impossible to meet
     * this requirement. This observation will also be very useful when we're
     * reasoning about the time complexity.
     */

    public int findJudge(int totalPerson, int[][] trust) {
        if (trust.length < totalPerson - 1) {
            return -1;
        }

        int[] indegrees = new int[totalPerson + 1];
        int[] outdegrees = new int[totalPerson + 1];

        for (int[] relation : trust) {
            outdegrees[relation[0]]++;
            indegrees[relation[1]]++;
        }

        for (int i = 1; i <= totalPerson; i++) {
            if (indegrees[i] == totalPerson - 1 && outdegrees[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
