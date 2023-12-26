package com.example.solution;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class P1496_Path_Crossing {

    /**
     * Reference:
     * https://leetcode.com/problems/path-crossing/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy
     * 1. Create a hash map moves that maps the characters N, S, W, E to the
     * corresponding values from above.
     * 2. Initialize a hash set visited with (0, 0).
     * 3. Initialize x = 0 and y = 0.
     * 4. For each c in path:
     * a. Get (dx, dy) from moves[c].
     * b. Add dx to x and dy to y.
     * c. Check if (x, y) is in visited. If it is, return true.
     * d. Add (x, y) to visited.
     * 5. Return false.
     * 
     */
    public boolean isPathCrossing(String path) {
        Map<Character, Pair<Integer, Integer>> moves = new HashMap();
        moves.put('N', new ImmutablePair(0, 1));
        moves.put('S', new ImmutablePair(0, -1));
        moves.put('W', new ImmutablePair(-1, 0));
        moves.put('E', new ImmutablePair(1, 0));

        Set<Pair<Integer, Integer>> visited = new HashSet();
        visited.add(new ImmutablePair(0, 0));

        int x = 0;
        int y = 0;

        for (Character c : path.toCharArray()) {
            Pair<Integer, Integer> curr = moves.get(c);
            int dx = curr.getKey();
            int dy = curr.getValue();
            x += dx;
            y += dy;

            Pair<Integer, Integer> pair = new ImmutablePair(x, y);
            if (visited.contains(pair)) {
                return true;
            }

            visited.add(pair);
        }

        return false;
    }
}
