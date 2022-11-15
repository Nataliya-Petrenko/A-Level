package com.petrenko.repository;

import com.petrenko.model.Car;
import com.petrenko.model.Color;
import java.util.UUID;

public class CarRepository {
    private Car[] cars = new Car[10];

    public void save(final Car car) {
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] == null) {
                cars[i] = car;
                break;
            }
            if (i == cars.length - 1) {
                increaseArray();
            }
        }
    }
    public void insert(final Car car, int indexInsertCar) {
        if (car == null) {
            return;
        }
        deleteByUuid(car.getUuidOfCar());
        if (indexInsertCar > indexOfLastCar()) {
            indexInsertCar = indexOfLastCar() + 1;
        }
        System.arraycopy(cars, indexInsertCar, cars, indexInsertCar + 1,
                indexOfLastCar() - indexInsertCar + 1);
        cars[indexInsertCar] = car;
    }
    public Car[] getAll() {
        final Car[] carsArray = new Car[indexOfLastCar() + 1];
        System.arraycopy(cars, 0, carsArray, 0, indexOfLastCar() + 1);
        return carsArray;
    }
    public Car getByUudi(final UUID uuidOfCar) {
        final int indexCar = indexCarByUuid(uuidOfCar);
        if (indexCar == -1) {
            return null;
        }
        return cars[indexCar];
    }
    public void updateColor(final UUID uuidOfCar, final Color color) {
        final Car car = getByUudi(uuidOfCar);
        if (car == null) {
            return;
        }
        car.setColor(color);
    }
    public void updateColorRandom(final UUID uuidOfCar) {
        final Car car = getByUudi(uuidOfCar);
        if (car == null) {
            return;
        }
        Color newColor;
        do {
            newColor = Color.randomColor();
        } while (car.getColor().equals(newColor));
        updateColor(uuidOfCar, newColor);
    }
    public void updatePrice(final UUID uuidOfCar, final int price) {
        final Car car = getByUudi(uuidOfCar);
        if (car == null) {
            return;
        }
        getByUudi(uuidOfCar).setPrice(price);
    }
    public void deleteByUuid(final UUID uuidOfCar) {
        int indexOfDeletedCar = indexCarByUuid(uuidOfCar);
        if (indexCarByUuid(uuidOfCar) == -1) {
            return;
        }
        System.arraycopy(cars, indexOfDeletedCar + 1, cars, indexOfDeletedCar,
                indexOfLastCar() - indexOfDeletedCar);
        cars[indexOfLastCar()] = null;

    }
    private int indexCarByUuid(final UUID uuidOfCar) {
        int indexCarByUuid = -1;
        for (int i = 0; i < cars.length; i++) {
            if (cars[i].getUuidOfCar().equals(uuidOfCar)) {
                indexCarByUuid = i;
                break;
            }
        }
        return indexCarByUuid;
    }
    private int indexOfLastCar() {
        int indexOfLastCar = -1;
        for (Car car : cars) {
            if (car != null) {
                indexOfLastCar++;
            } else {
                break;
            }
        }
        return indexOfLastCar;
    }
    private void increaseArray() {
        Car[] carsNewArray = new Car[cars.length * 2];
        System.arraycopy(cars, 0, carsNewArray, 0, cars.length);
        cars = carsNewArray;
    }
}
