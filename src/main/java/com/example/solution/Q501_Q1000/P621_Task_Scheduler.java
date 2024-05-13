package com.example.solution.Q501_Q1000;

public class P621_Task_Scheduler {
    /**
     * Reference:
     * https://leetcode.com/problems/task-scheduler/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a counter array of size 26 to store the frequency of each task
     * and variables maximum and maxCount to track the maximum frequency and the
     * number of tasks with that frequency.
     * 2. Traverse through the tasks and update the counter array. If the frequency
     * of a task is equal to the current maximum frequency, increment maxCount. If
     * the frequency is greater than the current maximum frequency, update maximum
     * and set maxCount to 1.
     * 3. Calculate the number of emptySlots by multiplying partCount (maximum
     * frequency - 1) and partLength (cooldown period - (number of tasks with
     * maximum frequency - 1)).
     * 4. Calculate the number of availableTasks by subtracting the product of
     * maximum and maxCount from the total number of tasks.
     * 5. Calculate the number of idle periods needed by taking the maximum of 0 and
     * the difference between the number of emptySlots and the number of
     * availableTasks.
     * 6. Return the total time required by adding the number of tasks to the number
     * of idle periods.
     */
    public int leastInterval(char[] tasks, int n) {
        // Counter array to store the frequency of each task
        int[] counter = new int[26];
        int maximum = 0;
        int maxCount = 0;

        // Traverse through tasks to calculate task frequencies
        for (char task : tasks) {
            counter[task - 'A']++;
            if (maximum == counter[task - 'A']) {
                maxCount++;
            } else if (maximum < counter[task - 'A']) {
                maximum = counter[task - 'A'];
                maxCount = 1;
            }
        }

        // Calculate idle slots, available tasks, and idles needed
        int partCount = maximum - 1;
        int partLength = n - (maxCount - 1);
        int emptySlots = partCount * partLength;
        int availableTasks = tasks.length - maximum * maxCount;
        int idles = Math.max(0, emptySlots - availableTasks);

        return tasks.length + idles;
    }
}
