package com.petrenko.repository;

import com.petrenko.model.Car;
import com.petrenko.model.Color;

import java.util.List;
import java.util.Optional;

public interface Crud<T> {
    void save(final T car);
    List<T> getAll();
    Optional<T> getByUuid(final String uuidOfCar);
    void deleteByUuid(final String uuidOfCar);
}
