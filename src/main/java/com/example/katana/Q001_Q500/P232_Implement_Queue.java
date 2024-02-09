package com.example.katana.Q001_Q500;

import java.util.Stack;

class MyQueue {
    Stack<Integer> mainStack;
    Stack<Integer> temporaryStack;

    public MyQueue() {
        mainStack = new Stack<>();
        temporaryStack = new Stack<>();
    }

    public void push(int x) {
        mainStack.push(x);
    }

    public int pop() {
        while (!mainStack.isEmpty() && mainStack.size() > 1) {
            temporaryStack.push(mainStack.pop());
        }
        int firstInteger = mainStack.pop();

        while (!temporaryStack.isEmpty()) {
            mainStack.push(temporaryStack.pop());
        }

        return firstInteger;
    }

    public int peek() {
        while (!mainStack.isEmpty() && mainStack.size() > 1) {
            temporaryStack.push(mainStack.pop());
        }
        int firstInteger = mainStack.pop();

        mainStack.push(firstInteger);
        while (!temporaryStack.isEmpty()) {
            mainStack.push(temporaryStack.pop());
        }

        return firstInteger;
    }

    public boolean empty() {
        return mainStack.isEmpty();
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
