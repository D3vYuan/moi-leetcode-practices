package com.example.katana.Q001_Q500;

import java.util.Arrays;

public class P135_Candy {
    public int candy(int[] ratings) {
        int candiesCount = ratings.length;
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);

        boolean hasChange = true;
        while (hasChange) {
            hasChange = false;
            for (int i = 0; i < ratings.length; i++) {
                int previousRating = i == 0 ? ratings[0] : ratings[i - 1];
                int currentRating = ratings[i];
                int nextRating = i == ratings.length - 1 ? currentRating : ratings[i + 1];

                if (currentRating > previousRating || currentRating > nextRating) {
                    int previousCandies = i == 0 ? 1 : candies[i - 1];
                    int currentCandies = candies[i];
                    int nextCandies = i == ratings.length - 1 ? currentCandies : candies[i + 1];
                    int baseCandies = currentRating > previousRating ? previousCandies : nextCandies;
                    if (currentRating > previousRating && currentRating > nextRating) {
                        baseCandies = Math.max(previousCandies, nextCandies);
                    }
                    int allocatedCandies = baseCandies + 1;
                    if (currentCandies != allocatedCandies) {
                        candiesCount += allocatedCandies - currentCandies;
                        candies[i] = allocatedCandies;
                        hasChange = true;
                    }
                }
            }
        }

        return candiesCount;
    }
}
