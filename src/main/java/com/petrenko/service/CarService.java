package com.petrenko.service;

import com.petrenko.model.Car;
import com.petrenko.model.Color;
import com.petrenko.model.Engine;
import com.petrenko.repository.CarRepository;
import com.petrenko.util.RandomGenerator;

import java.util.Random;

public class CarService {
    public static void check(final Car car) {
        if (car == null) {
            System.out.println("Car is null");
            return;
        }
        final boolean checkCount = car.getCount() > 0;
        final boolean checkPower = car.getEngine().getPower() > 200;
        if (checkCount && checkPower) {
            System.out.println("Car is fully ready for sell.");
            return;
        }
        if (!checkCount) {
            System.out.print("Count <= 0. ");
        }
        if (!checkPower) {
            System.out.println("Power <= 200.");
        }
    }
    private final Random random = new Random();
    private final CarRepository carRepository;
    public CarService(final CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public int create(RandomGenerator randomGenerator) {
        final int numberCars = randomGenerator.randomGenerator();
        if (numberCars <= 0 || numberCars > 10) {
            return -1;
        }
        final Car[] cars = create(numberCars);
        printAll(cars);
        System.out.println("Number of created cars: " + numberCars);
        return numberCars;
    }
    public Car create() {
        final Car car = new Car(randomText(5), new Engine(randomText(5)), Color.randomColor());
        carRepository.save(car);
        return car;
    }
    public Car[] create(final int numberOfCars) {
        if (numberOfCars < 0) {
            return new Car[0];
        }
        Car[] cars = new Car[numberOfCars];
        for (int i = 0; i < numberOfCars; i++) {
            cars[i] = create();
        }
        return cars;
    }
    public void insert(Car car, int indexInsertCar) {
        carRepository.insert(car, indexInsertCar);
    }
    public void insert(int indexInsertCar) {
        carRepository.insert(create(), indexInsertCar);
    }
    public void print(final Car car) {
        if (car == null) {
            return;
        }
        if (car.getManufacturer() != null) {
            System.out.print("Manufacturer: " + car.getManufacturer() + ". ");
        }
        if (car.getEngine() != null) {
            System.out.print("Engine: type - " + car.getEngine().getType() +
                    ", power - " + car.getEngine().getPower() + ". ");
        }
        if (car.getColor() != null) {
            System.out.print("Color: " + car.getColor() + ". ");
        }
        if (car.getCount() >= 0) {
            System.out.print("Count: " + car.getCount() + ". ");
        }
        if (car.getPrice() >= 0) {
            System.out.print("Price: " + car.getPrice() + ". ");
        }
        if (car.getUuidOfCar() != null) {
            System.out.print("UUID: " + car.getUuidOfCar() + ". ");
        }
        System.out.println();
    }
    public void printByUuid(String uuidOfCar) {
        final Car car = carRepository.getByUuid(uuidOfCar);
        if (car == null) {
            return;
        }
        print(car);
    }
    public void printAll() {
        for (Car car : carRepository.getAll()) {
            print(car);
        }
    }
    public void printAll(Car[] cars) {
        if (cars == null) {
            return;
        }
        for (Car car : cars) {
            print(car);
        }
    }
    public void updateColor(final String uuidOfCar, final Color color) {
        carRepository.updateColor(uuidOfCar, color);
    }
    public void updateColorRandom(final String uuidOfCar) {
        carRepository.updateColorRandom(uuidOfCar);
    }
    public void updatePrice(final String uuidOfCar, final int price) {
        if (price < 0) {
            return;
        }
        carRepository.updatePrice(uuidOfCar, price);
    }
    public void deleteByUuid(final String uuidOfCar) {
        carRepository.deleteByUuid(uuidOfCar);
    }
    private String randomText ( final int lengthRandomText){
        String abc = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < lengthRandomText; i++) {
            sb.append(abc.charAt(random.nextInt(abc.length() - 1)));
        }
        return sb.toString();
    }
}
