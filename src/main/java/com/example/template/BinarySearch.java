package com.example.template;

public class BinarySearch {
    // Binary search
    public int fn(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                // do something
                return mid;
            }
            if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // left is the insertion point
        return left;
    }

    // Binary search: duplicate elements, left-most insertion point
    public int fn_2(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // Binary search: duplicate elements, right-most insertion point
    public int fn_3(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // Binary search: for greedy problems (minimum)
    public int fn_4(int[] arr) {
        int MINIMUM_POSSIBLE_ANSWER = Integer.MIN_VALUE;
        int MAXIMUM_POSSIBLE_ANSWER = Integer.MAX_VALUE;

        int left = MINIMUM_POSSIBLE_ANSWER;
        int right = MAXIMUM_POSSIBLE_ANSWER;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // Binary search: for greedy problems (maximum)
    public int fn_5(int[] arr) {
        int MINIMUM_POSSIBLE_ANSWER = Integer.MIN_VALUE;
        int MAXIMUM_POSSIBLE_ANSWER = Integer.MAX_VALUE;

        int left = MINIMUM_POSSIBLE_ANSWER;
        int right = MAXIMUM_POSSIBLE_ANSWER;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    public boolean check(int x) {
        // this function is implemented depending on the problem
        return true;
    }
}
