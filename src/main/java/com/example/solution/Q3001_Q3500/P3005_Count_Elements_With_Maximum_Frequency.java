package com.example.solution.Q3001_Q3500;

import java.util.HashMap;
import java.util.Map;

public class P3005_Count_Elements_With_Maximum_Frequency {
    /**
     * Reference:
     * https://leetcode.com/problems/count-elements-with-maximum-frequency/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Explanation:
     * 1. We will update maxFrequency as stated above. We can also re-set
     * totalFrequencies to the element's frequency, because when we discover a new
     * maxFrequency, there is only one element so far with that frequency, and all
     * previous elements with the previous maxFrequency are no longer relevant.
     * 2. After iterating through nums once, we will have calculated
     * totalFrequencies accurately and can return.
     * 
     * Strategy:
     * 1. Initialize a map frequencies to store the frequency of each element. The
     * key is the element, and the value is its frequency.
     * 2. Initialize a variable maxFrequency to 0.
     * 3. Initialize a variable totalFrequencies to 0.
     * 4. For each number in nums:
     * a. Increment its frequency by 1 for each occurrence.
     * b. Initialize a variable frequency storing the current element's frequency.
     * c. If frequency is greater than maxFrequency:
     * c1. Update maxFrequency with frequency.
     * c2. Set totalFrequencies to frequency. This will reset the sum to the current
     * highest frequency since any previous highest frequencies are no longer the
     * max.
     * d. Else if frequency equals maxFrequency:
     * d1. Add frequency to totalFrequencies.
     * 5. Return totalFrequencies.
     */

    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        int maxFrequency = 0;
        int totalFrequencies = 0;

        // Find the frequency of each element
        // Determine the maximum frequency
        // Calculate the total frequencies of elements with the maximum frequency
        for (int num : nums) {
            frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
            int frequency = frequencies.get(num);

            // If we discover a higher frequency element
            // Update maxFrequency
            // Re-set totalFrequencies to the new max frequency
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
                totalFrequencies = frequency;
                // If we find an element with the max frequency, add it to the total
            } else if (frequency == maxFrequency) {
                totalFrequencies += frequency;
            }
        }
        return totalFrequencies;
    }
}
