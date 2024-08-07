package com.example.solution.Q1001_Q1500;

public class P1051_Heigh_Checker {
    /**
     * Reference:
     * https://leetcode.com/problems/height-checker/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Explanation:
     * 1. Divide the data set into two equal parts: The first step in the Merge Sort
     * algorithm is to divide the data set into two equal halves. This is done by
     * finding the middle point of the data set and splitting the data into two
     * parts.
     * 2. Recursively sort each half: Once the data set is divided into two halves,
     * the Merge Sort function is called recursively on each half. The recursive
     * calls continue until each half of the data is sorted into single-element
     * arrays.
     * 3. Merge the sorted halves: Once each half of the data is sorted, the two
     * halves are merged back into one final sorted array. The merging process
     * involves comparing the first elements of each half and inserting the smaller
     * element into the final array. This process continues until one of the halves
     * is empty. The remaining elements of the other half are then inserted into the
     * final array.
     * 4. Repeat the process until the entire data is sorted: The Merge Sort
     * function is called recursively until the entire data set is sorted.
     * 
     * Strategy:
     * 1. Create a helper function called merge which takes in the original array
     * arr, indices left, mid, right, and a temporary array tempArr as parameters.
     * a. Calculate the start indices and sizes of the two halves of the array. The
     * first half starts from the left index and the second half starts from mid +
     * 1.
     * b. Copy elements of both halves into the temporary array.
     * c. Merge the sub-arrays from the temporary array tempArr back into the array
     * arr in a sorted order using a while loop. The loop runs until either the
     * first half or second half is completely merged. In each iteration, the
     * smaller of the two elements from the first and second half is copied into the
     * array arr.
     * d. Copy any remaining elements from the first half or second half into the
     * array arr.
     * 2. Create a recursive function called mergeSort, which takes in the original
     * array arr, indices left and right, and a temporary array tempArr as
     * parameters.
     * a. Check if the left index is greater than or equal to the right index. If it
     * is, we return from the function.
     * b. Calculate the mid index.
     * c. Sort the first and second halves of the array recursively by calling the
     * mergeSort function.
     * d. Merge the sorted halves by calling the merge function.
     * 3. Create a temporary array temporaryArray with the same size as the heights
     * array.
     * 4. Create a new array, sortedHeights, with the same elements as the heights
     * array.
     * 5. Sort the sortedHeights array using the mergeSort function.
     * 6. Iterate through all indices of the heights array, comparing each element
     * with the corresponding element in the sortedHeights array. Count the number
     * of indices where the elements differ.
     * 7. Return the total count of indices with differing elements.
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

        // Merge the sub-arrays in 'tempArray' back into the original array 'arr' in
        // sorted order.
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

    // Recursive function to sort an array using merge sort.
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

    public int heightChecker(int[] heights) {
        // Sort the array using merge sort.
        int[] sortedHeights = heights.clone();
        int[] tempArray = new int[heights.length];
        mergeSort(sortedHeights, 0, sortedHeights.length - 1, tempArray);

        int count = 0;
        // Loop through the original and sorted arrays.
        for (int i = 0; i < sortedHeights.length; ++i) {
            // Increment count if elements at the same index differ.
            if (heights[i] != sortedHeights[i]) {
                count += 1;
            }
        }
        // Return the total count of differing elements.
        return count;
    }
}
