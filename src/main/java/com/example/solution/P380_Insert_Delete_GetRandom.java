package com.example.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Reference:
 * https://leetcode.com/problems/insert-delete-getrandom-o1/editorial/?envType=daily-question&envId=2024-01-15
 * 
 * Explanation:
 * 1. Insert
 * a. Add value -> its index into dictionary, average O(1) time.
 * b. Append value to array list, average O(1) time as well.
 * 2. Delete
 * a. Retrieve an index of element to delete from the hashmap.
 * b. Move the last element to the place of the element to delete, O(1) time.
 * c. Pop the last element out, O(1) time.
 * 3. GetRandom could be implemented in O(1) time with the help of standard
 * random.choice in Python and Random object in Java.
 */
class RandomizedSet {
    Map<Integer, Integer> dict;
    List<Integer> list;
    Random rand = new Random();

    /** Initialize your data structure here. */
    public RandomizedSet() {
        dict = new HashMap();
        list = new ArrayList();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain
     * the specified element.
     */
    public boolean insert(int val) {
        if (dict.containsKey(val))
            return false;

        dict.put(val, list.size());
        list.add(list.size(), val);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified
     * element.
     */
    public boolean remove(int val) {
        if (!dict.containsKey(val))
            return false;

        // move the last element to the place idx of the element to delete
        int lastElement = list.get(list.size() - 1);
        int idx = dict.get(val);
        list.set(idx, lastElement);
        dict.put(lastElement, idx);
        // delete the last element
        list.remove(list.size() - 1);
        dict.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}

public class P380_Insert_Delete_GetRandom {
    public static void main(String[] args) {
        RandomizedSet rSet = new RandomizedSet();
        boolean param_1 = rSet.insert(1);
        boolean param_2 = rSet.insert(2);
        boolean param_3 = rSet.insert(3);
        int param_4 = rSet.getRandom();
        int param_5 = rSet.getRandom();
        int param_6 = rSet.getRandom();
        System.out.println(String.format("Param_1: %s", param_1));
        System.out.println(String.format("Param_2: %s", param_2));
        System.out.println(String.format("Param_3: %s", param_3));
        System.out.println(String.format("Param_4: %s", param_4));
        System.out.println(String.format("Param_5: %s", param_5));
        System.out.println(String.format("Param_6: %s", param_6));
        int count = 0;
        while (count < 30) {
            System.out.println(String.format("Random #%d: %s", count + 1, rSet.getRandom()));
            count++;
        }
    }
}
