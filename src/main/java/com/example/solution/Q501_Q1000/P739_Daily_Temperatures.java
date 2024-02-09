package com.example.solution.Q501_Q1000;

import java.util.ArrayDeque;
import java.util.Deque;

public class P739_Daily_Temperatures {
    /**
     * Reference:
     * https://leetcode.com/problems/daily-temperatures/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Strategy:
     * 1. Initialize an array answer with the same length as temperatures and all
     * values initially set to 0. Also, initialize a stack as an empty array.
     * 2. Iterate through temperatures. At each index currDay:
     * a. If the stack is not empty, that means there are previous days for which we
     * have not yet seen a warmer day. While the current temperature is warmer than
     * the temperature of prevDay (the index of the day at the top of the stack):
     * a1. Set answer[prevDay] equal to the number of days that have passed between
     * prevDay and the current day, that is, answer[prevDay] = currDay - prevDay.
     * b. Push the current index currDay onto the stack.
     * 3. Return answer.
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int currDay = 0; currDay < n; currDay++) {
            int currentTemp = temperatures[currDay];
            // Pop until the current day's temperature is not
            // warmer than the temperature at the top of the stack

            while (!stack.isEmpty() && temperatures[stack.peek()] < currentTemp) {
                int prevDay = stack.pop();
                answer[prevDay] = currDay - prevDay;
            }
            stack.push(currDay);
        }

        return answer;
    }
}
