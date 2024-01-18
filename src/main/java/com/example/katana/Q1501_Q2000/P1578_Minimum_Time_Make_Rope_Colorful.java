package com.example.katana.Q1501_Q2000;

public class P1578_Minimum_Time_Make_Rope_Colorful {
    public int minCost(String colors, int[] neededTime) {
        int removalTime = 0;
        int repeatedTotal = 0;
        int repeatedMax = 0;

        for (int i = 1; i < colors.length(); i++) {
            Character currentColor = colors.charAt(i);
            Character prevColor = colors.charAt(i - 1);

            System.out.println(String.format("Comparing - Current: %s | Previous: %s", currentColor, prevColor));
            if (currentColor == prevColor) {
                if (repeatedTotal == 0) {
                    repeatedTotal += neededTime[i] + neededTime[i - 1];
                    repeatedMax = Math.max(neededTime[i - 1], neededTime[i]);
                    System.out.println(String.format("Computing (First Occurrence) - Total: %d | Max: %d",
                            repeatedTotal, repeatedMax));
                } else {
                    repeatedTotal += neededTime[i];
                    repeatedMax = Math.max(repeatedMax, neededTime[i]);
                    System.out.println(String.format("Computing (Subsequent Occurrence) - Total: %d | Max: %d",
                            repeatedTotal, repeatedMax));
                }
            } else {
                if (repeatedTotal > 0) {
                    System.out.println(
                            String.format("Computing (Previous Similar Color) - Total: %d | Max: %d | RemovalTime: %d",
                                    repeatedTotal, repeatedMax, repeatedTotal - repeatedMax));
                    removalTime += repeatedTotal - repeatedMax;
                    repeatedTotal = 0;
                    repeatedMax = 0;
                }
            }
        }

        if (repeatedTotal > 0) {
            removalTime += repeatedTotal - repeatedMax;
        }

        return removalTime;
    }
}
