package com.petrenko.repository;

import com.petrenko.annotations.Autowired;
import com.petrenko.annotations.Singleton;
import com.petrenko.model.Car;
import com.petrenko.service.CarService;
import com.petrenko.util.AnnotationProcessor;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;

@Singleton
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
    public List<Car> getAll() {
        return CARS;
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
