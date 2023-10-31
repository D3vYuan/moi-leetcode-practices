package com.example;

import java.util.ArrayList;
import java.util.List;

public class Concurrency {
    class MyThread implements Runnable {

        public int threadId;

        public MyThread(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            System.out.println(String.format("Hello World from Thread #%d!", this.threadId));
        }
    }

    private void run(int threads) {
        List<Thread> threadService = new ArrayList<>();
        for (int threadCount = 0; threadCount < threads; threadCount++) {
            MyThread runnable = new MyThread(threadCount);
            Thread thread = new Thread(runnable);
            threadService.add(thread);
        }

        threadService.stream().forEach(thread -> thread.start());
    }

    public static void main(String[] args) {
        Concurrency concurrency = new Concurrency();
        concurrency.run(5);
    }
}
