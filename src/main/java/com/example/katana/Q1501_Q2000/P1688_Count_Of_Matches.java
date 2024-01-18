package com.example.katana.Q1501_Q2000;

public class P1688_Count_Of_Matches {
    public int numberOfMatches(int n) {
        int teams = n;
        int matches = 0;
        while (teams > 1) {
            if (teams % 2 == 1) {
                matches += (teams - 1) / 2;
                teams = (teams - 1) / 2 + 1;
            } else {
                matches += teams / 2;
                teams = teams / 2;
            }
        }

        return matches;
    }
}
