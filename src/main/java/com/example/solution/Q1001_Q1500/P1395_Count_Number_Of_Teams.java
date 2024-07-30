package com.example.solution.Q1001_Q1500;

public class P1395_Count_Number_Of_Teams {

    /**
     * Reference:
     * https://leetcode.com/problems/count-number-of-teams/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Main method numTeams:
     * a. Initialize:
     * a1. n as the length of rating.
     * a2. teams to store the total number of possible teams.
     * a3. two arrays increasingCache and decreasingCache of size n×4 to serve as
     * cache for the memoization.
     * b. Loop over the array rating. For each index startIndex:
     * b1. Call countIncreasingTeams and countDecreasingTeams with startIndex. Add
     * their results to teams.
     * c. Return teams.
     * 
     * 2. Helper method countIncreasingTeams:
     * a. Define a method countIncreasingTeams with parameters: rating,
     * currentIndex, teamSize and the cache increasingCache.
     * b. Initialize n as the length of rating.
     * c. If currentIndex is equal to n, return 0.
     * d. If teamSize is equal to 3, return 1.
     * e. If increasingCache already contains an entry with the current state,
     * return it.
     * f. Initialize a variable validTeams to 0.
     * g. Loop over all indices from currentIndex + 1 to the end of the array. For
     * each index nextIndex:
     * g1. If rating[nextIndex] is greater than rating[currentIndex], call
     * countIncreasingTeams with nextIndex and teamSize incremented by 1.
     * h. Cache validTeams with the current state in increasingCache and return it.
     * 
     * 3. Helper method countDecreasingTeams:
     * a. Define a method countDecreasingTeams with parameters: rating,
     * currentIndex, teamSize and the cache decreasingCache.
     * b. This method is exactly the same as countIncreasingTeams except for majorly
     * one thing:
     * b1. We check whether rating[nextIndex] is less than rating[currentIndex] to
     * call countDecreasingTeams.
     */

    public int numTeams(int[] rating) {
        int n = rating.length;
        int teams = 0;
        Integer[][] increasingCache = new Integer[n][4];
        Integer[][] decreasingCache = new Integer[n][4];

        // Calculate total teams by considering each soldier as a starting point
        for (int startIndex = 0; startIndex < n; startIndex++) {
            teams += countIncreasingTeams(rating, startIndex, 1, increasingCache) +
                    countDecreasingTeams(rating, startIndex, 1, decreasingCache);
        }

        return teams;
    }

    private int countIncreasingTeams(
            int[] rating,
            int currentIndex,
            int teamSize,
            Integer[][] increasingCache) {
        int n = rating.length;

        // Base case: reached end of array
        if (currentIndex == n)
            return 0;

        // Base case: found a valid team of size 3
        if (teamSize == 3)
            return 1;

        // Return cached result if available
        if (increasingCache[currentIndex][teamSize] != null) {
            return increasingCache[currentIndex][teamSize];
        }

        int validTeams = 0;

        // Recursively count teams with increasing ratings
        for (int nextIndex = currentIndex + 1; nextIndex < n; nextIndex++) {
            if (rating[nextIndex] > rating[currentIndex]) {
                validTeams += countIncreasingTeams(
                        rating,
                        nextIndex,
                        teamSize + 1,
                        increasingCache);
            }
        }

        // Cache and return the result
        return increasingCache[currentIndex][teamSize] = validTeams;
    }

    private int countDecreasingTeams(
            int[] rating,
            int currentIndex,
            int teamSize,
            Integer[][] decreasingCache) {
        int n = rating.length;

        // Base case: reached end of array
        if (currentIndex == n)
            return 0;

        // Base case: found a valid team of size 3
        if (teamSize == 3)
            return 1;

        // Return cached result if available
        if (decreasingCache[currentIndex][teamSize] != null) {
            return decreasingCache[currentIndex][teamSize];
        }

        int validTeams = 0;

        // Recursively count teams with decreasing ratings
        for (int nextIndex = currentIndex + 1; nextIndex < n; nextIndex++) {
            if (rating[nextIndex] < rating[currentIndex]) {
                validTeams += countDecreasingTeams(
                        rating,
                        nextIndex,
                        teamSize + 1,
                        decreasingCache);
            }
        }

        // Cache and return the result
        return decreasingCache[currentIndex][teamSize] = validTeams;
    }
}
