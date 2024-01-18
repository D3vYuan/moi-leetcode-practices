package com.example.solution.Q2001_Q2500;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Food implements Comparable<Food> {
    // Store the food's rating.
    public int foodRating;
    // Store the food's name.
    public String foodName;

    public Food(int foodRating, String foodName) {
        this.foodRating = foodRating;
        this.foodName = foodName;
    }

    // Implement the compareTo method for comparison
    @Override
    public int compareTo(Food other) {
        // If food ratings are the same, sort based on their names (lexicographically
        // smaller name food will be on top)
        if (foodRating == other.foodRating) {
            return foodName.compareTo(other.foodName);
        }
        // Sort based on food rating (bigger rating food will be on top)
        return -1 * Integer.compare(foodRating, other.foodRating);
    }
}

class FoodRatings {

    // Map food with its rating.
    private Map<String, Integer> foodRatingMap;
    // Map food with the cuisine it belongs to.
    private Map<String, String> foodCuisineMap;

    // Store all food of a cuisine in a priority queue (to sort them on
    // ratings/name)
    // Priority queue element -> Food: (foodRating, foodName)
    private Map<String, PriorityQueue<Food>> cuisineFoodMap;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodRatingMap = new HashMap<>();
        foodCuisineMap = new HashMap<>();
        cuisineFoodMap = new HashMap<>();

        for (int i = 0; i < foods.length; ++i) {
            // Store 'rating' and 'cuisine' of the current 'food' in 'foodRatingMap' and
            // 'foodCuisineMap' maps.
            foodRatingMap.put(foods[i], ratings[i]);
            foodCuisineMap.put(foods[i], cuisines[i]);
            // Insert the '(rating, name)' element into the current cuisine's priority
            // queue.
            cuisineFoodMap.computeIfAbsent(cuisines[i], k -> new PriorityQueue<>()).add(new Food(ratings[i], foods[i]));
        }
    }

    public void changeRating(String food, int newRating) {
        // Update food's rating in the 'foodRating' map.
        foodRatingMap.put(food, newRating);
        // Insert the '(new food rating, food name)' element into the respective
        // cuisine's priority queue.
        String cuisineName = foodCuisineMap.get(food);
        cuisineFoodMap.get(cuisineName).add(new Food(newRating, food));
    }

    public String highestRated(String cuisine) {
        // Get the highest rated 'food' of 'cuisine'.
        Food highestRated = cuisineFoodMap.get(cuisine).peek();

        // If the latest rating of 'food' doesn't match with the 'rating' on which it
        // was sorted in the priority queue,
        // then we discard this element from the priority queue.
        while (foodRatingMap.get(highestRated.foodName) != highestRated.foodRating) {
            cuisineFoodMap.get(cuisine).poll();
            highestRated = cuisineFoodMap.get(cuisine).peek();
        }

        // Return the name of the highest-rated 'food' of 'cuisine'.
        return highestRated.foodName;
    }
}

public class P2353_Food_Rating_System {
    /**
     * References:
     * https://leetcode.com/problems/design-a-food-rating-system/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy:
     * 1. Create a class Food containing foodRating and foodName properties, and
     * overload less than operator method to keep the highest rated or
     * lexicographically smaller named element on the top in the priority queue.
     * 2. Create three hash maps:
     * a. foodRatingMap, to store ratings associated with the respective food.
     * b. foodCuisineMap, to store the cuisine name of the respective food.
     * c. cuisineFoodMap, to store Food(foodRating, foodName) elements in a priority
     * queue associated with the respective cuisine.
     * 3. Initialization. Iterate on all indices of the foods array, and for each
     * index i:
     * a. Store (foods[i], ratings[i]) and (foods[i], cuisines[i]) key-value pairs
     * in foodRatingMap and foodCuisineMap respectively.
     * b. Insert Food(ratings[i], foods[i]) element in the priority queue of
     * cuisines[i] key of cuisineFoodMap.
     * 4. Implementing changeRating(food, newRating) method:
     * a. Update new rating in foodRatingMap.
     * b. Fetch the cuisine name for food from foodRatingMap.
     * c. Insert the Food(newRating, food) element in the priority queue of the
     * cuisine name in cuisineFoodMap.
     * 5. Implementing highestRated(cuisine) method:
     * a. Get the top element (i.e. highestRated) from the priority queue of cuisine
     * in cuisineFoodMap.
     * b. If the rating of the top element and the rating of the corresponding food
     * in foodRatingMap are not the same, i.e. highestRated.foodRating !=
     * foodRatingMap[highestRated.foodName], then we discard and remove the current
     * top element and fetch the next top element from the priority queue. Repeat
     * this step until ratings are the same.
     * c. Return the food name of the top element, i.e. highestRated.foodName.
     * 
     */
    public static void main(String[] args) {

    }
}
