package com.petrenko.repository;

import com.petrenko.model.Car;
import com.petrenko.model.Color;

import java.util.Optional;

public interface Crud<T> {
    void save(final T car);
    T[] getAll();
    Optional<Car> getByUuid(final String uuidOfCar);
    void deleteByUuid(final String uuidOfCar);
}
