package com.example.katana;

class FoodRatings {
    private String[] foods;
    private String[] cuisines;
    private int[] ratings;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        this.foods = foods;
        this.cuisines = cuisines;
        this.ratings = ratings;
    }

    public void changeRating(String food, int newRating) {
        int foodIdx = 0;
        while (!foods[foodIdx].equals(food)) {
            foodIdx++;
        }

        ratings[foodIdx] = newRating;
    }

    public String highestRated(String cuisine) {
        String highestRatedFood = "";
        int highestRatings = Integer.MIN_VALUE;

        for (int i = 0; i < cuisines.length; i++) {
            // System.out.println(
            //         String.format(
            //                 "#%d (%s) | Highest Food: %s | Highest Ratings: %d | Current Cuisine: %s | Current Food: %s | Current Ratings: %d",
            //                 i, cuisine,
            //                 highestRatedFood, highestRatings, cuisines[i], foods[i], ratings[i]));
            if (cuisines[i].equals(cuisine) && ratings[i] >= highestRatings) {
                if (ratings[i] == highestRatings) {
                    // System.out.println(String.format("Highest: %s | Current: %s | Compare: %d", highestRatedFood,
                    //         foods[i], highestRatedFood.compareTo(foods[i])));
                    highestRatedFood = highestRatedFood.compareTo(foods[i]) > 0 ? foods[i] : highestRatedFood;
                } else {
                    highestRatings = ratings[i];
                    highestRatedFood = foods[i];
                }
            }
        }
        // System.out.println(String.format("--"));
        return highestRatedFood;
    }
}

public class P2353_Food_Rating_System {
    public static void main(String[] args) {
        // FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
        // obj.changeRating(food, newRating);
        // String param_2 = obj.highestRated(cuisine);
    }
}
