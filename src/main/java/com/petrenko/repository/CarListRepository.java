package com.petrenko.repository;

import com.petrenko.model.Car;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;

public class CarListRepository implements Crud<Car>{
    private static CarListRepository instance;
    private static final List<Car> CARS = new LinkedList<>();
    private static final BiPredicate<Car, String> CHECK_ID = (car, id) -> car.getUuidOfCar().equals(id);

    private CarListRepository() {
    }
    public static CarListRepository getInstance() {
        if (instance == null) {
            instance = new CarListRepository();
        }
        return instance;
    }

    @Override
    public void save(Car car) {
        if (car == null) {
            return;
        }
        CARS.stream()
                .filter(carFromCars -> CHECK_ID.test(carFromCars, car.getUuidOfCar()))
                .findAny()
                .ifPresentOrElse(
                        carFromCars -> carFromCars.setCount(carFromCars.getCount() + car.getCount()),
                        () -> CARS.add(car)
                );
    }

    @Override
    public Car[] getAll() {
        return CARS.toArray(new Car[0]);
    }

    @Override
    public Optional<Car> getByUuid(String uuidOfCar) {
        return CARS.stream()
                .filter(car -> CHECK_ID.test(car, uuidOfCar))
                .findAny();
    }

    @Override
    public void deleteByUuid(String uuidOfCar) {
        CARS.removeIf(car -> CHECK_ID.test(car, uuidOfCar));

    }
}
