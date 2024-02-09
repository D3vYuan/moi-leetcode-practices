package com.example.solution.Q001_Q500;

import java.util.Stack;

/**
 * Reference:
 * https://leetcode.com/problems/implement-queue-using-stacks/editorial/?envType=daily-question&envId=2024-01-19
 * 
 */
class MyQueue {
    private Stack<Integer> s1;
    private Stack<Integer> s2;
    private int front;

    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();

    }

    public void push(int x) {
        if (s1.empty())
            front = x;
        s1.push(x);
    }

    public int pop() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty())
                s2.push(s1.pop());
        }
        return s2.pop();
    }

    public int peek() {
        if (!s2.isEmpty()) {
            return s2.peek();
        }
        return front;
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}

public class P232_Implement_Queue {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        System.out.println(String.format("Peek: %d", myQueue.peek())); // return 1
        System.out.println(String.format("Pop: %d", myQueue.pop())); // return 1, queue is [2]
        System.out.println(String.format("Empty: %s", myQueue.empty())); // return false
    }
}
