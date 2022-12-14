package com.petrenko;

import com.petrenko.model.*;
import com.petrenko.repository.CarRepository;
import com.petrenko.service.CarService;
import com.petrenko.util.AlgorithmUtil;

public class Main {
    public static void main(String[] args) {
        final CarService carService = new CarService(new CarRepository());

        Car[] cars = carService.create(5);

        System.out.println("Array of cars:");
        carService.printAll();

        String carId = cars[3].getUuidOfCar();
        System.out.println("Car which we'll look for: ");
        carService.printByUuid(carId);

        System.out.println("Sorted array of cars:");
        Car[] sortedCars = AlgorithmUtil.bubbleSort(cars);
        carService.printAll(sortedCars);

        int indexOfCar = AlgorithmUtil.binarySearch(cars, carId);
        System.out.println("Index the car (binarySearch): " + indexOfCar);

        int indexOfCar1 = AlgorithmUtil.binarySearch(cars, "hhsdjhdshj");
        System.out.println("Index the car (binarySearch): " + indexOfCar1);

        int indexOfCar2 = AlgorithmUtil.recursiveBinarySearch(cars, 0, cars.length - 1, carId);
        System.out.println("Index the car (recursiveBinarySearch): " + indexOfCar2);

        int indexOfCar3 = AlgorithmUtil.recursiveBinarySearch(cars, 0, cars.length - 1,
                "hhsdjhdshj");
        System.out.println("Index the car (recursiveBinarySearch): " + indexOfCar3);

    }
}
