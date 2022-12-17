package com.petrenko.util;

import com.petrenko.model.Car;

public class AlgorithmUtil {

    public static int binarySearch(final Car[] cars, final String carUuid) {
        if (cars == null || carUuid == null ||
                (carUuid.compareTo(cars[0].getUuidOfCar()) < 0) ||
                (carUuid.compareTo(cars[cars.length - 1].getUuidOfCar()) > 0)) {
            return -1;
        }
        int startIndex = 0;
        int endIndex = cars.length;
        int index;
        do {
            index = (startIndex + endIndex)/2;
            if (carUuid.compareTo(cars[index].getUuidOfCar()) < 0) {
                endIndex = index - 1;
            } else {
                startIndex = index + 1;
            }
        } while (carUuid.compareTo(cars[index].getUuidOfCar()) != 0);
        return index;
    }

    public static int recursiveBinarySearch(final Car[] cars, final int startIndex, final int endIndex, final String carUuid) {
        if (cars == null || carUuid == null || startIndex > endIndex) {
            return -1;
        }
        int index = (startIndex + endIndex)/2;
        if (carUuid.compareTo(cars[index].getUuidOfCar()) == 0) {
            return index;
        }
        if (carUuid.compareTo(cars[index].getUuidOfCar()) < 0) {
            return recursiveBinarySearch(cars, startIndex, index - 1, carUuid);
        }
        return recursiveBinarySearch(cars, index + 1, endIndex, carUuid);
    }

    public static Car[] bubbleSort(Car[] cars) {
        int count;
        int j = 0;
        do {
            count = 0;
            j++;
            for (int i = 0; i < cars.length - j; i++) {
                int order = cars[i].getUuidOfCar().compareTo(cars[i+1].getUuidOfCar());
                if (order > 0) {
                    Car temp = cars[i];
                    cars[i] = cars[i+1];
                    cars[i+1] = temp;
                    count++;
                }
            }
        } while (count > 0);
        return cars;
    }
}
