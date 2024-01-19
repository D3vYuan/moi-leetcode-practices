package com.example.solution.Q1001_Q1500;

import com.example.utility.MountainArray;

public class P1095_Find_In_Mountain_Array {

    /**
     * Reference:
     * https://leetcode.com/problems/find-in-mountain-array/editorial/?envType=daily-question&envId=2024-01-19
     * 
     * Explanation:
     * 1. we are sure that index-0 and index-mountainArr.length() - 1 are not the
     * peak indices. Hence, the lowest possible value of peakIndex is 1, and the
     * highest possible value of peakIndex is mountainArr.length() - 2
     * 2. Element at peakIndex is greater than its neighbors. However, this would
     * require 3 calls to mountainArr.get(k). Let's compare the element at testIndex
     * with its right neighbor only
     * 
     * 
     * Strategy:
     * 1. In a variable length, save the length of the mountainArr by calling the
     * function mountainArr.length().
     * 2. Find the index of the peak element in the mountainArr.
     * a. Set low = 1 and high = length - 2.
     * b. While low != high, do the following:
     * b1. Find the middle index of the search space [low, high]. Let's call the
     * index testIndex.
     * b2. If mountainArr.get(testIndex) < mountainArr.get(testIndex + 1), then set
     * low = testIndex + 1.
     * b3. Otherwise, set high = testIndex.
     * c. After the loop, low (which is equal to high) will be the peakIndex.
     * 3. Search for the target element in the strictly increasing part of the
     * array.
     * a. Set low = 0 and high = peakIndex.
     * b. While low != high, do the following:
     * b1. Find the middle index of the search space [low, high]. Let's call the
     * index testIndex.
     * b2. If mountainArr.get(testIndex) < target, then set low = testIndex + 1.
     * b3. Otherwise, set high = testIndex.
     * c. If mountainArr.get(low) == target, then return low.
     * d. Otherwise, search for the target element in the strictly decreasing part
     * of the array.
     * 4. Search for the target element in the strictly decreasing part of the
     * array.
     * a. Set low = peakIndex + 1 and high = length - 1.
     * b. While low != high, do the following:
     * b1. Find the middle index of the search space [low, high]. Let's call the
     * index testIndex.
     * b2. If mountainArr.get(testIndex) > target, then set low = testIndex + 1.
     * b3. Otherwise, set high = testIndex.
     * c. If mountainArr.get(low) == target, then return low.
     * 5. target not found in the mountainArr. Return -1.
     */
    public int findInMountainArray(int target, MountainArray mountainArr) {
        // Save the length of the mountain array
        int length = mountainArr.length();

        // 1. Find the index of the peak element
        int low = 1;
        int high = length - 2;
        while (low != high) {
            int testIndex = (low + high) / 2;
            if (mountainArr.get(testIndex) < mountainArr.get(testIndex + 1)) {
                low = testIndex + 1;
            } else {
                high = testIndex;
            }
        }
        int peakIndex = low;

        // 2. Search in the strictly increasing part of the array
        low = 0;
        high = peakIndex;
        while (low != high) {
            int testIndex = (low + high) / 2;
            if (mountainArr.get(testIndex) < target) {
                low = testIndex + 1;
            } else {
                high = testIndex;
            }
        }

        // Check if the target is present in the strictly increasing part
        if (mountainArr.get(low) == target) {
            return low;
        }

        // 3. Otherwise, search in the strictly decreasing part
        low = peakIndex + 1;
        high = length - 1;
        while (low != high) {
            int testIndex = (low + high) / 2;
            if (mountainArr.get(testIndex) > target) {
                low = testIndex + 1;
            } else {
                high = testIndex;
            }
        }

        // Check if the target is present in the strictly decreasing part
        if (mountainArr.get(low) == target) {
            return low;
        }

        // Target is not present in the mountain array
        return -1;
    }
}
