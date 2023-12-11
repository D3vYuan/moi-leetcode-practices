package com.example.katana;

public class P1287_Element_Appearing_25_In_Array {
    class Solution {
        public int findSpecialInteger(int[] arr) {
            int arrSize = arr.length;
            int minimumOccurrences = arrSize * 25 / 100;
            System.out.println(String.format("Size: %d | Occurrences: %d", arrSize, minimumOccurrences));
            int currentNumber = arr[0];
            int currentOccurrences = 1;

            if (arrSize == 1) {
                return currentNumber;
            }

            for (int i = 1; i < arr.length; i++) {
                if (arr[i] == currentNumber) {
                    currentOccurrences += 1;
                } else {
                    currentNumber = arr[i];
                    currentOccurrences = 1;
                }

                if (currentOccurrences > minimumOccurrences) {
                    return currentNumber;
                }
            }
            return -1;
        }
    }
}
