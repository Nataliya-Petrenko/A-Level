package com.petrenko.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyThreadForFillArray {

    public static void randomFillArray(int[] array) throws InterruptedException {
        final Random random = new Random();

        Runnable runnableOuter = () -> {

            Runnable runnable = () -> {
//            System.out.println(Thread.currentThread().getName() + " is running");

                while (!ArrayUtil.isFull(array)) {
                    int number = random.nextInt(1001);
                    ArrayUtil.setUniqueNumber(array, number);
//                System.out.println("Number " + number + " added by " + Thread.currentThread().getName());
                }
            };

            List<Thread> threads = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                Thread thread = new Thread(runnable);
                threads.add(thread);
                thread.start();
            }

            threads.forEach(thread -> {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        };

        Thread thread = new Thread(runnableOuter);
        thread.start();
        thread.join();
    }
}