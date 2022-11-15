package com.petrenko.service;

import com.petrenko.model.Car;
import com.petrenko.model.Color;
import com.petrenko.repository.CarRepository;

import java.util.Random;
import java.util.UUID;

public class CarService {
    private final Random random = new Random();
    public CarService(final CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    CarRepository carRepository = new CarRepository();
    public Car create() {
        final Car car = new Car(randomText(5), randomText(5), Color.randomColor());
        carRepository.save(car);
        return car;
    }
    public Car[] create(final int numberOfCars) {
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
        System.out.printf("Manufacturer: %s. Engine: %s. Color: %s. Count: %d. Price: %d. UUID: %s.%n",
                car.getManufacturer(), car.getEngine(), car.getColor(), car.getCount(), car.getPrice(),
                car.getUuidOfCar());
    }
    public void printByUuid(UUID uuidOfCar) {
        final Car car = carRepository.getByUudi(uuidOfCar);
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
    public void updateColor(final UUID uuidOfCar, final Color color) {
        carRepository.updateColor(uuidOfCar, color);
    }
    public void updateColorRandom(final UUID uuidOfCar) {
        carRepository.updateColorRandom(uuidOfCar);
    }
    public void updatePrice(final UUID uuidOfCar, final int price) {
        carRepository.updatePrice(uuidOfCar, price);
    }
    public void deleteByUuid(final UUID uuidOfCar) {
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
