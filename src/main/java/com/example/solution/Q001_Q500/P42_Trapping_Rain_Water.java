package com.example.solution.Q001_Q500;

public class P42_Trapping_Rain_Water {
    /**
     * Reference:
     * 
     * Strategy:
     * 1. Find maximum height of bar from the left end upto an index i in the array
     * left_max.
     * 2. Find maximum height of bar from the right end upto an index i in the array
     * right_max.
     * 3. Iterate over the height\text{height}height array and update ans:
     * a. Add min⁡(left_max[i],right_max[i])−height[i] − height[i] to ans
     */
    public int trap(int[] height) {
        // Case of empty height array
        if (height.length == 0)
            return 0;
        int ans = 0;
        int size = height.length;
        // Create left and right max arrays
        int[] left_max = new int[size];
        int[] right_max = new int[size];
        // Initialize first height into left max
        left_max[0] = height[0];
        for (int i = 1; i < size; i++) {
            // update left max with current max
            left_max[i] = Math.max(height[i], left_max[i - 1]);
        }
        // Initialize last height into right max
        right_max[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            // update right max with current max
            right_max[i] = Math.max(height[i], right_max[i + 1]);
        }
        // Calculate the trapped water
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(left_max[i], right_max[i]) - height[i];
        }
        // Return amount of trapped water
        return ans;
    }
}
