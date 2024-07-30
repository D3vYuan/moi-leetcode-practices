package com.example.solution.Q501_Q1000;

public class P912_Sort_An_Array {
    /**
     * Reference: https://leetcode.com/problems/sort-an-array/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Create a helper function called merge which takes in the original array arr, indices left, mid, right, and a temporary array tempArr as parameters.
     * a. Calculate the start indices and sizes of the two halves of the array. The first half starts from the left index and the second half starts from mid+1.
     * b. Copy elements of both halves into the temporary array.
     * c. Merge the sub-arrays from the temporary array tempArr back into the original array arr in a sorted order using a while loop. The loop runs until either the first half or second half is completely merged. In each iteration, the smaller of the two elements from the first and second half is copied into the original array "arr".
     * d. Copy any remaining elements from the first half or second half into the original array.
     * 2. Create a recursive function called mergeSort which takes in the original array arr, indices left, right, and a temporary array tempArr as parameters.
     * a. Check if the left index is greater than or equal to the right index. If it is, we return from the function.
     * b. Calculate the mid index.
     * c. Sort the first and second halves of the array recursively by calling the mergeSort function.
     * d. Merge the sorted halves by calling the merge function.
     * 3. Create a temporary array temporaryArray with the same size as the nums array.
     * 4. Call the mergeSort function on the nums array with boundary, 0, and nums.size()-1.
     * 5. Return the sorted array nums.
     */

    // Function to merge two sub-arrays in sorted order.
    private void merge(int[] arr, int left, int mid, int right, int[] tempArr) {
        // Calculate the start and sizes of two halves.
        int start1 = left;
        int start2 = mid + 1;
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        // Copy elements of both halves into a temporary array.
        for (int i = 0; i < n1; i++) {
            tempArr[start1 + i] = arr[start1 + i];
        }
        for (int i = 0; i < n2; i++) {
            tempArr[start2 + i] = arr[start2 + i];
        }

        // Merge the sub-arrays 'in tempArray' back into the original array 'arr' in sorted order.
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (tempArr[start1 + i] <= tempArr[start2 + j]) {
                arr[k] = tempArr[start1 + i];
                i += 1;
            } else {
                arr[k] = tempArr[start2 + j];
                j += 1;
            }
            k += 1;
        }

        // Copy remaining elements
        while (i < n1) {
            arr[k] = tempArr[start1 + i];
            i += 1;
            k += 1;
        }
        while (j < n2) {
            arr[k] = tempArr[start2 + j];
            j += 1;
            k += 1;
        }
    }

    // Recursive function to sort an array using merge sort
    private void mergeSort(int[] arr, int left, int right, int[] tempArr) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        // Sort first and second halves recursively.
        mergeSort(arr, left, mid, tempArr);
        mergeSort(arr, mid + 1, right, tempArr);
        // Merge the sorted halves.
        merge(arr, left, mid, right, tempArr);
    }
    
    public int[] sortArray(int[] nums) {
        int[] temporaryArray = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, temporaryArray);
        return nums;
    }
}
