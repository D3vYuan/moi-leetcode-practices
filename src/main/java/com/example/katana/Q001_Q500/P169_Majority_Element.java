package com.example.katana.Q001_Q500;

import java.util.HashMap;
import java.util.Map;

public class P169_Majority_Element {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> elementOccurrences = new HashMap<>();
        int majorityFrequency = nums.length / 2;
        for (int i = 0; i < nums.length; i++) {
            int currentNumber = nums[i];
            elementOccurrences.put(currentNumber, elementOccurrences.getOrDefault(currentNumber, 0) + 1);
            if (elementOccurrences.get(currentNumber) > majorityFrequency){
                return currentNumber;
            }
        }
        return -1;
    }
}
