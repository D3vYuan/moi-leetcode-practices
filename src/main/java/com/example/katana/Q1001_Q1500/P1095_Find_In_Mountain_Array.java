package com.example.katana.Q1001_Q1500;

import com.example.utility.MountainArray;

public class P1095_Find_In_Mountain_Array {

    private int findMinimumIndex(int start, int end, int target, MountainArray mountainArr) {
        int startBoundary = start;
        int endBoundary = end;

        while (start <= end) {
            int midPoint = (int) Math.round((end + start) / 2.0);
            System.out.println(String.format("Processing:  Start @%d | Middle @%d | End @%d", start, midPoint, end));

            int currentValue = mountainArr.get(midPoint);
            if (target == currentValue) {
                return midPoint;
            }

            // Already at the most leftside - Target NOT Found
            if (midPoint - 1 < startBoundary) {
                if (start != end) {
                    start++;
                    continue;
                }
                return -1;
            }

            // Already at the right mostside - Target NOT Found
            if (midPoint + 1 > endBoundary) {
                if (start != end) {
                    end--;
                    continue;
                }
                return -1;
            }

            int leftValue = mountainArr.get(midPoint - 1);
            int rightValue = mountainArr.get(midPoint + 1);

            System.out
                    .println(String.format("Processing: Left [%d] | Middle [%d] | Right [%d]", midPoint,
                            leftValue, currentValue, rightValue));

            // Few Scenario:
            // 1. Currently at the peak
            // If at peak, the smaller index should be at the left side, move both pointer
            // towards each other
            if (currentValue > leftValue && currentValue > rightValue) {
                start = leftValue == target ? start : start + 1;
                end = leftValue != target && rightValue == target ? end : end - 1;
            }

            // 2. Currently at the ascending side
            // If target is bigger move start to pointer (target is right of pointer);
            // Otherwise move end to pointer (target is left of pointer)
            else if (currentValue > leftValue && currentValue < rightValue) {
                // System.out
                // .println(String.format("Asc B4: Current [%d] | Target [%d] | Start [%d] | Mid
                // [%d] | End [%d]",
                // currentValue, target, start, midPoint, end));
                start = currentValue < target ? midPoint + 1 : start;
                end = currentValue < target ? end : midPoint - 1;
                // System.out
                // .println(String.format("Asc AFT: Current [%d] | Target [%d] | Start [%d] |
                // Mid [%d] | End [%d]",
                // currentValue, target, start, midPoint, end));
            }

            // 3. Currently at the descending side
            // Minimum index of target should be left side, move end to pointer to shift
            // towards the left side
            else if (currentValue < leftValue && currentValue > rightValue) {
                start = currentValue < target ? start : midPoint + 1;
                end = currentValue < target ? midPoint - 1 : end;
            }

            System.out.println(String.format("--"));
        }

        return -1;
    }

    public int findInMountainArray(int target, MountainArray mountainArr) {
        // Check left half then check right half
        System.out.println(String.format("== Left =="));
        int index = findMinimumIndex(0, mountainArr.length() / 2, target, mountainArr);
        if (index == -1) {
            System.out.println(String.format("== Right =="));
            index = findMinimumIndex(mountainArr.length() / 2 + 1, mountainArr.length() - 1, target, mountainArr);
        }
        return index;
    }
}
