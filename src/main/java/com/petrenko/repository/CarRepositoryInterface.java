package com.petrenko.repository;

import com.petrenko.model.Car;
import com.petrenko.model.Color;

public interface CarRepositoryInterface<C> {

    void save(final C car);
    void insert(final C car, int indexInsertCar);
    C[] getAll();
    C getByUuid(final String uuidOfCar);
    void updateColor(final String uuidOfCar, final Color color);
    void updateColorRandom(final String uuidOfCar);
    void updatePrice(final String uuidOfCar, final int price);
    void deleteByUuid(final String uuidOfCar);
}
