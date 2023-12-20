package com.example.solution;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P1436_Destination_City {
    /**
     * Reference:
     * https://leetcode.com/problems/destination-city/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy:
     * 1. Initialize a hash set hasOutgoing.
     * 2. Iterate i over the indices of paths:
     * a. Add paths[i][0] to hasOutgoing.
     * 3. Iterate i over the indices of paths:
     * a. Set candidate = paths[i][1].
     * b. If candidate is not in hasOutgoing, return candidate.
     * 4. The code should never reach this point. Return anything.
     */
    public String destCity(List<List<String>> paths) {
        Set<String> hasOutgoing = new HashSet<>();
        for (int i = 0; i < paths.size(); i++) {
            hasOutgoing.add(paths.get(i).get(0));
        }

        for (int i = 0; i < paths.size(); i++) {
            String candidate = paths.get(i).get(1);
            if (!hasOutgoing.contains(candidate)) {
                return candidate;
            }
        }

        return "";
    }
}
