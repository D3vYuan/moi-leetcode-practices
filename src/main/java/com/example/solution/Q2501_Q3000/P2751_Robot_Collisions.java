package com.example.solution.Q2501_Q3000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class P2751_Robot_Collisions {
    /**
     * Reference:
     * https://leetcode.com/problems/robot-collisions/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialization:
     * a. Determine the number of robots and store it in n.
     * b. Create an array indices to keep track of the original indices of the
     * robots.
     * c. Create a list result to store the health of the surviving robots.
     * d. Initialize an empty stack to manage right-moving robots.
     * 
     * 2. Sort Robots by Position:
     * a. Sort the indices array based on the positions of the robots to ensure they
     * are processed from left to right.
     * 
     * 3. Process Each Robot:
     * a. Iterate through each current_index in the sorted indices array:
     * a1. If the robot is moving to the right ('R'):
     * Push current_index onto the stack.
     * a2. If the robot is moving to the left ('L'):
     * While the stack is not empty and the current robot's health is greater than
     * 0:
     * Pop the top robot from the stack (this is the most recent right-moving
     * robot).
     * Compare the health of the current left-moving robot and the top right-moving
     * robot:
     * If the top right-moving robot has more health:
     * Decrease its health by 1 and push it back onto the stack.
     * Set the current left-moving robot's health to 0.
     * If the current left-moving robot has more health:
     * Decrease its health by 1.
     * Set the top right-moving robot's health to 0.
     * a3. If both robots have the same health:
     * Set both robots' health to 0.
     * 
     * 4. Collect Surviving Robots:
     * a. Iterate through each robot index from 0 to n - 1:
     * If the robot's health is greater than 0:
     * Append the robot's health to the result list.
     * 
     * 5. Return the result list, which contains the health of the surviving robots.
     */

    public List<Integer> survivedRobotsHealths(
            int[] positions,
            int[] healths,
            String directions) {
        int n = positions.length;
        Integer[] indices = new Integer[n];
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (int index = 0; index < n; ++index) {
            indices[index] = index;
        }

        Arrays.sort(
                indices,
                (lhs, rhs) -> Integer.compare(positions[lhs], positions[rhs]));

        for (int currentIndex : indices) {
            // Add right-moving robots to the stack
            if (directions.charAt(currentIndex) == 'R') {
                stack.push(currentIndex);
            } else {
                while (!stack.isEmpty() && healths[currentIndex] > 0) {
                    // Pop the top robot from the stack for collision check
                    int topIndex = stack.pop();

                    // Top robot survives, current robot is destroyed
                    if (healths[topIndex] > healths[currentIndex]) {
                        healths[topIndex] -= 1;
                        healths[currentIndex] = 0;
                        stack.push(topIndex);
                    } else if (healths[topIndex] < healths[currentIndex]) {
                        // Current robot survives, top robot is destroyed
                        healths[currentIndex] -= 1;
                        healths[topIndex] = 0;
                    } else {
                        // Both robots are destroyed
                        healths[currentIndex] = 0;
                        healths[topIndex] = 0;
                    }
                }
            }
        }

        // Collect surviving robots
        for (int index = 0; index < n; ++index) {
            if (healths[index] > 0) {
                result.add(healths[index]);
            }
        }
        return result;
    }
}
