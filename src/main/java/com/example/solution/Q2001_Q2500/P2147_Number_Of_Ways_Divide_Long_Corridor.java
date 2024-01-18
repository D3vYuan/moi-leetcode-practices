package com.example.solution.Q2001_Q2500;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class P2147_Number_Of_Ways_Divide_Long_Corridor {
    /**
     * Reference:
     * https://leetcode.com/problems/number-of-ways-to-divide-a-long-corridor/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation
     * When the task is not possible, based on the number of S and P in the
     * corridor:
     * 1. if we have 0 seat, then we can't divide the corridor, because no section
     * can contain exactly two S.
     * 2. if we have only 1 seat, then we can't divide the corridor, because no
     * section can contain exactly two S.
     * 3. if we have only 2 seats, then we can divide the corridor, but in only one
     * way, because there is only one way to divide the corridor into two sections,
     * each containing exactly two S.
     * 4. if we have only 3 seats, then again, we can't divide the corridor, because
     * no division is such that each section contains exactly two S. There will be
     * at least one section containing less than two S.
     * 5. If we have 4 seats, then we can divide the corridor. Let's focus on the
     * illustration to understand the division.
     * a. If there is 0 P between the second and third S, then we can have only one
     * way of installing the divider.
     * b. If there is 1 P between the second and third S, then we can have two ways
     * of installing the divider. Note that out of these two blue bars, we will
     * select only one in one division. Thus, there are two different ways in this
     * example, with one divider!
     * c. If there are 2 P between the second and third S, then we can have three
     * ways of installing the divider. Again, more precisely, there are three
     * different ways in this example, with one divider!
     * d. If there are k P between second and third S, then we can have k+1 ways of
     * installing the divider
     * 
     * 
     * Strategy
     * 1. Store 1000000007 in the variable MOD for convenience. It is a good
     * practice to store constants.
     * 2. Initialize a two-dimensional array cache of size n rows and 3 columns.
     * Initialize each element of the array to -1. We will use this array to cache
     * the result of each sub-problem. Alternatively, we can use a hash map to cache
     * the result of each sub-problem.
     * 3. Define a function count which takes two arguments index and seats. It can
     * have other arguments as well to access the required variables.
     * a. If index is equal to n, then the current section is valid only if seats is
     * equal to 2. Thus, return 1 if seats == 2, otherwise return 0.
     * b. If cache[index][seats] is not equal to -1, then return
     * cache[index][seats]. This implies that we have already computed the result of
     * this sub-problem, and we can return the cached result.
     * c. If the current section has seats == 2
     * c1. If corridor[index] is S, then we have to close the section and start a
     * new section from this index. Thus, return count(index + 1, 1
     * c2. If corridor[index] is P, then we have the option to close or to keep
     * growing the section. Thus, return (count(index + 1, 0) + count(index + 1, 2))
     * % MOD
     * d. If the current section has seats < 2, then we have to keep growing the
     * section.
     * d1. If corridor[index] is S, then return count(index + 1, seats + 1)
     * d2. If corridor[index] is P, then return count(index + 1, seats)
     * 4. Call the function count with index = 0 and seats = 0. Return the result of
     * the function call.
     */

    int MOD = (int) 1e9 + 7;

    // Count the number of ways to divide from "index" to the last index
    // with "seats" number of "S" in the current section
    private int count(int index, int seats, String corridor, Map<Pair<Integer, Integer>, Integer> memo) {
        // If we have reached the end of the corridor, then
        // the current section is valid only if "seats" is 2
        if (index == corridor.length()) {
            return seats == 2 ? 1 : 0;
        }

        // If we have already computed the result of this sub-problem,
        // then return the cached result
        if (memo.containsKey(new ImmutablePair<>(index, seats))) {
            return memo.get(new ImmutablePair<>(index, seats));
        }

        // Result of the sub-problem
        int result = 0;

        // If the current section has exactly 2 "S"
        if (seats == 2) {
            // If the current element is "S", then we have to close the
            // section and start a new section from this index. Next index
            // will have one "S" in the current section
            if (corridor.charAt(index) == 'S') {
                result = count(index + 1, 1, corridor, memo);
            } else {
                // If the current element is "P", then we have two options
                // 1. Close the section and start a new section from this index
                // 2. Keep growing the section
                int closeSection = count(index + 1, 0, corridor, memo);
                int growSection = count(index + 1, 2, corridor, memo);
                result = (closeSection + growSection) % MOD;
            }
        } else {
            // Keep growing the section. Increment "seats" if present
            // element is "S"
            if (corridor.charAt(index) == 'S') {
                result = count(index + 1, seats + 1, corridor, memo);
            } else {
                result = count(index + 1, seats, corridor, memo);
            }
        }

        memo.put(new ImmutablePair<>(index, seats), result);
        return result;
    }

    public int numberOfWays(String corridor) {
        Map<Pair<Integer, Integer>, Integer> memo = new HashMap<>();

        return count(0, 0, corridor, memo);
    }
}
