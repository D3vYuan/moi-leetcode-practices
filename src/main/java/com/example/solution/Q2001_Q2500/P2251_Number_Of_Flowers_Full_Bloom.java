package com.example.solution.Q2001_Q2500;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import javafx.scene.layout.Priority;

public class P2251_Number_Of_Flowers_Full_Bloom {
    /**
     * Reference:
     * https://leetcode.com/problems/number-of-flowers-in-full-bloom/editorial/?envType=daily-question&envId=2024-01-17
     * 
     * Explanation:
     * 1. we use a pointer i to iterate along flowers. For a given person, we find
     * all the flowers that started blooming before person arrives. We push the end
     * time of these flowers onto a heap. We can then remove all the flowers that
     * finished blooming by popping from the heap, since a min heap efficiently
     * gives us the minimum (earliest) times.
     * 
     * Strategy:
     * 1. Sort flowers. Create a sorted version of people called sortedPeople.
     * 2. Initialize a hash map dic, a min heap, and an integer i = 0.
     * 3. Iterate over sortedPeople. For each person:
     * a. While flowers[i][0] < person (the flower at i already started blooming),
     * push flowers[i][1] (when the flower finishes blooming) to heap and increment
     * i.
     * b. While the top of heap (minimum element) is less than person, pop from
     * heap.
     * c. Set dic[person] to the size of heap.
     * 4. Initialize an array ans. Iterate over people and populate ans using dic.
     */
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int[] sortedPeople = Arrays.copyOf(people, people.length);
        Arrays.sort(sortedPeople);

        Arrays.sort(flowers, (a, b) -> a[0] - b[0]);
        Map<Integer, Integer> dic = new HashMap<>();

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        int i = 0;
        for (int person : sortedPeople) {
            while (i < flowers.length && flowers[i][0] <= person){
                heap.add(flowers[i][1]);
                i ++;
            }

            while (!heap.isEmpty() && heap.peek() < person){
                heap.remove();
            }

            dic.put(person, heap.size());
        }

        int[] ans = new int[people.length];
        for (int j = 0; j < ans.length; j++) {
            ans[j] = dic.get(people[j]);
        }

        return ans;
    }
}
