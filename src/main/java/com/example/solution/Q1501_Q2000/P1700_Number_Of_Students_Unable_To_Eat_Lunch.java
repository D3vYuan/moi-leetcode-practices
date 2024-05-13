package com.example.solution.Q1501_Q2000;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class P1700_Number_Of_Students_Unable_To_Eat_Lunch {
    /**
     * Reference:
     * https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * Strategy:
     * 1. Initialize a variable len to the length of students. sandwiches will be
     * the same length.
     * 2. Initialize a queue studentQueue for storing the students and a stack
     * sandwichStack for storing the sandwiches.
     * 3. Add the students and sandwiches to the queue and stack:
     * a. Use a for loop to iterate from i = 0 to len:
     * a1. Add the next student, student[i], to the back of studentQueue.
     * a2. Add the next sandwich, sandwich[len - i - 1], to the top of
     * sandwichStack, which will build the stack so it is in the same order as the
     * given sandwiches.
     * 4. Initialize a variable lastServed to 0 to store how many students ago the
     * most recent sandwich was served.
     * 5. Simulate the lunch process by serving sandwiches and sending students to
     * the back of the queue.
     * a. While the size of studentQueue is greater than 0 and greater than
     * lastServed:
     * a1. If the first student in the queue's preference matches the top sandwich
     * in the stack, remove the student from the queue and the sandwich from the
     * stack, and reset lastServed to 0.
     * a2. Otherwise, move the first student to the back of the queue and increment
     * lastServed by 1.
     * 6. Return the number of remaining students in the queue.
     */

    public int countStudents(int[] students, int[] sandwiches) {
        int len = students.length; // Sandwiches will be the same length
        Queue<Integer> studentQueue = new LinkedList<>();
        Stack<Integer> sandwichStack = new Stack<>();

        // Add students and sandwiches to the queue and stack
        for (int i = 0; i < len; i++) {
            sandwichStack.push(sandwiches[len - i - 1]);
            studentQueue.offer(students[i]);
        }

        // Simulate the lunch process by serving sandwiches
        // or sending students to the back of the queue.
        int lastServed = 0;
        while (studentQueue.size() > 0 && lastServed < studentQueue.size()) {
            if (sandwichStack.peek() == studentQueue.peek()) {
                sandwichStack.pop(); // Serve sandwich
                studentQueue.poll(); // Student leaves queue
                lastServed = 0;
            } else {
                // Student moves to back of queue
                studentQueue.offer(studentQueue.poll());
                lastServed++;
            }
        }

        // Remaining students in queue are unserved students
        return studentQueue.size();
    }
}
