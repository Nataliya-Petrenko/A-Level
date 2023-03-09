package com.petrenko.multithreading2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Hw1Version2 {


    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            threadPool.execute(new Task());
        }

        threadPool.shutdown();
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " has iteration " + i);
            }
        }
    }

}
