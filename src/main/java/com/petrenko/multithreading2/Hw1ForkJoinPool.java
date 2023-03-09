package com.petrenko.multithreading2;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Hw1ForkJoinPool {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);

        int[] numbers = new int[100];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }

        Long result = forkJoinPool.invoke(new Hw1ForkJoinPool.Sum(numbers, 0, numbers.length));
        System.out.println("result " + result);

    }

    static class Sum extends RecursiveTask<Long> {
        int low;
        int high;
        int[] array;

        public Sum(int[] array, int low, int high) {
            this.low = low;
            this.high = high;
            this.array = array;
        }

        @Override
        protected Long compute() {

            if (high - low <= 10) {
                long sum = 0;
                for (int i = low; i < high; i++) {
                    sum += array[i];
                    System.out.println(Thread.currentThread().getName() + " has iteration " + i);
                }
                return sum;
            } else {
                int mid = low + (high - low) / 2;
                Sum left = new Sum(array, low, mid);
                Sum right = new Sum(array, mid, high);
                left.fork();
                long rightResult = right.compute();
                long leftResult = left.join();
                return leftResult + rightResult;
            }
        }
    }

}
