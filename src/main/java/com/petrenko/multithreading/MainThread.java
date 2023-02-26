package com.petrenko.multithreading;

import java.util.Arrays;

public class MainThread {

    public static void main(String[] args) throws InterruptedException {

        final int[] array = new int[100];

        MyThreadForFillArray.randomFillArray(array);

        System.out.println("Elements of the array: ");
        Arrays.stream(array).forEach(System.out::println);

        MyThreadForSumArray myThreadForSumArray = new MyThreadForSumArray(array);

        System.out.println("Results of each thread for calculating the sum of the array: ");
        myThreadForSumArray.getPartsOfSum().forEach(System.out::println);

        System.out.println("The sum of elements of the array: " + myThreadForSumArray.getSum());

    }


}

