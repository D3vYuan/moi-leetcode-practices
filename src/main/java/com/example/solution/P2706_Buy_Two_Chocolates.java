package com.example.solution;

import java.util.Arrays;

public class P2706_Buy_Two_Chocolates {
    /**
     * References:
     * https://leetcode.com/problems/buy-two-chocolates/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Strategy:
     * 1. Sort the array prices in increasing order. This can be done using the
     * inbuilt sorting function in the language of choice. Make sure that the sorted
     * array is assigned the variable name prices itself.
     * 2. In a variable min_cost, save the sum of the first two elements of the
     * array prices. These are the minimum and the second minimum prices in the
     * array prices.
     * a. In code, this can be done as min_cost = prices[0] + prices[1].
     * 3. If the minimum cost is less than or equal to the amount of money we have,
     * then we can buy two chocolates. In this case, we will return the amount of
     * money left after buying two chocolates. It will be equal to money - min_cost.
     * This we will return if min_cost <= money.
     * 
     */
    public int buyChoco(int[] prices, int money) {
        Arrays.sort(prices);
        int minCost = prices[0] + prices[1];

        if (minCost <= money) {
            return money - minCost;
        }
        return money;
    }
}
