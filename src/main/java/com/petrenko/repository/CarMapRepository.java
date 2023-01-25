package com.petrenko.repository;

import com.petrenko.annotations.Singleton;
import com.petrenko.model.Car;
import com.petrenko.util.AnnotationProcessor;

import java.util.*;
import java.util.function.BiPredicate;

@Singleton
public class CarMapRepository implements Crud<Car> {
    private static CarMapRepository instance;
    private static final Map<String, Car> CARS = new HashMap<>();
    private static final BiPredicate<Car, String> CHECK_ID = (car, id) -> car.getUuidOfCar().equals(id);

    private CarMapRepository() {
    }

    public static CarMapRepository getInstance() {
        if (instance == null) {
            instance = new CarMapRepository();
        }
        return instance;
    }

    @Override
    public void save(Car car) {
        if (car == null) {
            return;
        }
        CARS.entrySet()
                .stream()
                .filter(carFromCars -> CHECK_ID.test(carFromCars.getValue(), car.getUuidOfCar()))
                .findAny()
                .ifPresentOrElse(
                        carFromCars -> carFromCars.getValue().setCount(carFromCars.getValue().getCount() + car.getCount()),
                        () -> CARS.put(car.getUuidOfCar(), car)
                );
    }

    @Override
    public Car[] getAll() {
        return CARS
                .values()
                .toArray(new Car[0]);
    }

    @Override
    public Optional<Car> getByUuid(String uuidOfCar) {
        return CARS.values()
                .stream()
                .filter(car -> CHECK_ID.test(car, uuidOfCar))
                .findAny();
    }

    @Override
    public void deleteByUuid(String uuidOfCar) {
        CARS.remove(uuidOfCar);
    }
}
