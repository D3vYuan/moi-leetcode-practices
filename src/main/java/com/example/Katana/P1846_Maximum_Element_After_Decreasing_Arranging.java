package com.example.katana;

import java.util.Arrays;

public class P1846_Maximum_Element_After_Decreasing_Arranging {
    private void check_first_element_is_1(int[] arr) {
        int firstElement = arr[0];
        if (firstElement == 1) {
            return;
        }

        int count = 1;
        while (count < arr.length) {
            if (arr[count] == 1) {
                // swap first element with this element
                int temp = arr[0];
                arr[0] = arr[count];
                arr[count] = temp;
                break;
            }
            count++;
        }
    }

    private int rearrange_elements(int[] arr) {
        Arrays.sort(arr);
        int maxNumber = 1;

        if (arr[0] != 1) {
            arr[0] = 1;
        }

        for (int i = 1; i < arr.length; i++) {
            System.out.println(String.format("%d (%d) | %d (%d)", arr[i - 1], i - 1, arr[i], i));
            if (Math.abs(arr[i] - arr[i - 1]) > 1) {
                arr[i] = arr[i - 1] + 1;
            }

            if (arr[i] > maxNumber) {
                maxNumber = arr[i];
            }
        }

        return maxNumber;
    }

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        if (arr.length <= 0) {
            return 0;
        }

        // check_first_element_is_1(arr);
        int maxNumber = rearrange_elements(arr);

        return maxNumber;
    }
}
