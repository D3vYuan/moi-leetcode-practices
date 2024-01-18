package com.example.katana.Q2501_Q3000;

import java.util.HashMap;
import java.util.Map;

public class P2870_Minimum_Operations_Make_Array_Empty {
    // Choose two elements with equal values and delete them from the array.
    // Choose three elements with equal values and delete them from the array.
    public int minOperations(int[] nums) {
        Map<Integer, Integer> mappings = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            mappings.put(nums[i], mappings.getOrDefault(nums[i], 0) + 1);
        }

        int minimumOperations = 0;
        mappings.entrySet().stream().forEach(System.out::println);
        for (Map.Entry<Integer, Integer> entry : mappings.entrySet()) {
            Integer num = entry.getKey();
            Integer occurrences = entry.getValue();

            while (occurrences >= 3) {
                if (occurrences - 3 >= 2 || occurrences % 3 == 0) {
                    occurrences -= 3;
                    minimumOperations += 1;
                } else {
                    break;
                }
            }

            while (occurrences >= 2) {
                if (occurrences - 2 >= 2 || occurrences % 2 == 0) {
                    occurrences -= 2;
                    minimumOperations += 1;
                } else {
                    break;
                }
            }

            if (occurrences == 1 || occurrences < 0) {
                return -1;
            }

            // // If odd number returns 1 is not able to make array empty
            // if (occurrences % 2 == 1 && occurrences % 3 == 1) {
            // return -1;
            // }

            // // Number Divisible By 3 and 2
            // if (occurrences % 3 == 0 && occurrences % 2 == 0) {
            // minimumOperations += occurrences / 3;
            // }
            // // Number Divisible By 2 Only
            // else if (occurrences % 2 == 0) {
            // minimumOperations += occurrences / 2;
            // }
            // // All Others
            // else {
            // minimumOperations += occurrences / 3;
            // minimumOperations += occurrences % 3 == 2 ? 1 : 0;
            // }
        }
        return minimumOperations;
    }
}
