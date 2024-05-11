package com.example.solution.Q2001_Q2500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class P2092_Find_All_People_With_Secret {
    /**
     * Reference:
     * https://leetcode.com/problems/find-all-people-with-secret/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Create a graph to store the information about meetings. For every person,
     * we store the meeting time and label of the person met.
     * We can use HashMap to store the information. The key of HashMap will be
     * person, and the value will be a list of (time, person) pairs.
     * 
     * 2. Create a queue q to store the people whom we need to process. It will
     * store (person, time of knowing the secret).
     * Initially, we will add (0, 0) and (firstPerson, 0) to the queue since both of
     * them know the secret at time t = 0.
     * 
     * 3. Create an earliest array of size n. It will store the earliest time at
     * which a person learned the secret as per the current state of knowledge. It
     * will be initialized with INT.MAX for all the people indicating that no one
     * knows the secret.
     * However, for person 0 and firstPerson, we will update the earliest array with
     * 0 since they know the secret at time t = 0.
     * 
     * 4. Do the following while the q is not empty:
     * a. Deque the front of q and store it in (person, time).
     * b. Iterate over neighbors of person using the for loop. Let's say the
     * neighbor is (t, nextPerson).
     * If t >= time and earliest[nextPerson] > t, then update earliest[nextPerson] =
     * t and add (nextPerson, t) to the queue.
     * b1. We are adding (nextPerson, t) to the queue because we have updated
     * earliest[nextPerson] and we need to process all the people whom nextPerson
     * meets after time t.
     * b2. We are checking t >= time because the nextPerson can know the secret only
     * if he/she meets person after the time at which person learned the secret.
     * b3. We are checking earliest[nextPerson] > t because we are interested in the
     * earliest time at which nextPerson learned the secret. If earliest[nextPerson]
     * <= t, then we have already processed nextPerson at an earlier time, and we
     * don't need to process it again.
     * 
     * 5. Iterate over the earliest array and return indices of all the people who
     * know the secret. They are identified by the fact that earliest[i] != INT.MAX.
     */
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        // For every person, we store the meeting time and label of the person met.
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] meeting : meetings) {
            int x = meeting[0], y = meeting[1], t = meeting[2];
            graph.computeIfAbsent(x, k -> new ArrayList<>()).add(new int[] { t, y });
            graph.computeIfAbsent(y, k -> new ArrayList<>()).add(new int[] { t, x });
        }

        // Earliest time at which a person learned the secret
        // as per current state of knowledge. If due to some new information,
        // the earliest time of knowing the secret changes, we will update it
        // and again process all the people whom he/she meets after the time
        // at which he/she learned the secret.
        int[] earliest = new int[n];
        Arrays.fill(earliest, Integer.MAX_VALUE);
        earliest[0] = 0;
        earliest[firstPerson] = 0;

        // Queue for BFS. It will store (person, time of knowing the secret)
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { 0, 0 });
        q.offer(new int[] { firstPerson, 0 });

        // Do BFS
        while (!q.isEmpty()) {
            int[] personTime = q.poll();
            int person = personTime[0], time = personTime[1];
            for (int[] nextPersonTime : graph.getOrDefault(person, new ArrayList<>())) {
                int t = nextPersonTime[0], nextPerson = nextPersonTime[1];
                if (t >= time && earliest[nextPerson] > t) {
                    earliest[nextPerson] = t;
                    q.offer(new int[] { nextPerson, t });
                }
            }
        }

        // Since we visited only those people who know the secret,
        // we need to return indices of all visited people.
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (earliest[i] != Integer.MAX_VALUE) {
                ans.add(i);
            }
        }
        return ans;
    }
}
