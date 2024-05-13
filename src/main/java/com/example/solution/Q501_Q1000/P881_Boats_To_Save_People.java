package com.example.solution.Q501_Q1000;

import java.util.Arrays;

public class P881_Boats_To_Save_People {
    /**
     * Reference:
     * https://leetcode.com/problems/boats-to-save-people/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. If the heaviest person can share a boat with the lightest person, then do
     * so. Otherwise, the heaviest person can't pair with anyone, so they get their
     * own boat.
     * 2. The reason this works is because if the lightest person can pair with
     * anyone, they might as well pair with the heaviest person.
     */
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0, j = people.length - 1;
        int ans = 0;

        while (i <= j) {
            ans++;
            if (people[i] + people[j] <= limit)
                i++;
            j--;
        }

        return ans;
    }
}
