package com.example.solution.Q501_Q1000;

import java.util.PriorityQueue;

public class P786_Kth_Smallest_Primce_Fraction {
    /**
     * Reference:
     * https://leetcode.com/problems/k-th-smallest-prime-fraction/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize an empty priority queue pq to store pairs of fractions and
     * their corresponding indices.
     * 2. Iterate through the input array arr using a loop variable i.
     * a. For each element arr[i], calculate the fraction formed by dividing it by
     * the largest element in the array (arr[arr.size() - 1]).
     * b. Push a pair consisting of the negative fraction value (-1.0 * arr[i] /
     * arr[arr.size() - 1]) and the corresponding indices (i for the numerator and
     * arr.size() - 1 for the denominator) into the priority queue pq.
     * 3. The priority queue pq now contains all the fractions formed by dividing
     * each element by the largest element in the array, sorted in ascending order
     * based on the fraction values.
     * 4. Repeat the following steps k - 1 times:
     * a. Remove the top element (smallest fraction) from the priority queue pq and
     * store its indices in the cur variable.
     * b. Decrement the denominator index (cur[1]--).
     * c. Calculate the new fraction formed by dividing the numerator at cur[0] by
     * the decremented denominator (arr[cur[1]]).
     * d. Push the new fraction value (-1.0 * arr[cur[0]] / arr[cur[1]]) and its
     * corresponding indices (cur[0] for the numerator and cur[1] for the
     * denominator) into the priority queue pq.
     * 5. After k - 1 iterations, the top element of the priority queue pq will be
     * the kth smallest fraction.
     * 6. Extract the numerator and denominator indices from the top element of the
     * priority queue and store them in result.
     * 7. Return a array containing the numerator (arr[result[0]]) and denominator
     * (arr[result[1]]) values corresponding to the kth smallest fraction.
     */
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        // Create a priority queue to store pairs of fractions
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));

        // Push the fractions formed by dividing each element by
        // the largest element into the priority queue
        for (int i = 0; i < arr.length; i++) {
            pq.offer(new double[] {
                    -1.0 * arr[i] / arr[arr.length - 1],
                    i,
                    arr.length - 1
            });
        }

        // Iteratively remove the top element (smallest fraction)
        // and replace it with the next smallest fraction
        while (--k > 0) {
            double[] cur = pq.poll();
            int numeratorIndex = (int) cur[1];
            int denominatorIndex = (int) cur[2] - 1;
            if (denominatorIndex > numeratorIndex) {
                pq.offer(new double[] {
                        -1.0 * arr[numeratorIndex] / arr[denominatorIndex],
                        numeratorIndex,
                        denominatorIndex
                });
            }
        }

        // Retrieve the kth smallest fraction from
        // the top of the priority queue
        double[] result = pq.poll();
        return new int[] { arr[(int) result[1]], arr[(int) result[2]] };
    }
}
