package com.petrenko.multithreading;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadForSumArray {
    private int[] array;
    private AtomicInteger sum = new AtomicInteger(0);
    private List<AtomicInteger> partsOfSum = new CopyOnWriteArrayList<>();

    public MyThreadForSumArray(int[] array) throws InterruptedException {
        this.array = array;
        Thread thread = new Thread(runnableOuter);
        thread.start();
        thread.join();
    }

    Runnable runnableOuter = () -> {

        int[] partLength = {0, array.length / 4, array.length / 4 * 2, array.length / 4 * 3, array.length};

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(runnableSumPartArray(partLength[i], partLength[i + 1]));
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

        partsOfSum.forEach(sp -> sum.addAndGet(sp.get()));

    };

    private Runnable runnableSumPartArray(int start, int end) {
        return () -> {
            AtomicInteger sumPart = new AtomicInteger(0);
            for (int i = start; i < end; i++) {
                sumPart.addAndGet(array[i]);
            }
            partsOfSum.add(sumPart);
        };
    }

    public int getSum() {
        return sum.get();
    }

    public List<Integer> getPartsOfSum() {
        List<Integer> list = new LinkedList<>();
        partsOfSum.forEach(p -> list.add(p.get()));
        return list;
    }
}