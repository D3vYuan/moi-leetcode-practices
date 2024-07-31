package com.example.solution.Q1501_Q2000;

public class P1518_Water_Bottles {
    /**
     * Reference:
     * https://leetcode.com/problems/water-bottles/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize the answer variable consumedBottles to 0.
     * 2. Keep doing the following until we have more numBottles than the
     * numExchange:
     * a. Find K as numBottles / numExchange.
     * b. Consume numExchange * K number of full bottles, i.e. add numExchange * K
     * to consumedBottles.
     * c. Decrement numExchange * K from the available full bottles numBottles.
     * d. Exchange the empty bottles with K full bottle, i.e., increment numBottles
     * by K
     * 3. Return consumedBottles + numBottles
     */

    public int numWaterBottles(int numBottles, int numExchange) {
        int consumedBottles = 0;

        while (numBottles >= numExchange) {
            // Maximum number of times we can consume numExchange
            // number of bottles using numBottles.
            int K = numBottles / numExchange;

            consumedBottles += numExchange * K;
            numBottles -= numExchange * K;

            numBottles += K;
        }

        return consumedBottles + numBottles;
    }
}
