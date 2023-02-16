package com.petrenko.factory;

import com.petrenko.builder.PassengerCarBuilder;
import com.petrenko.builder.TruckBuilder;
import com.petrenko.model.*;

import java.util.Optional;
import java.util.UUID;

public class CarFactory {
    public Optional<Car> create(final Type type) {
        Car car;
        if (type == null) {
            return Optional.empty();
        }
        if (type.equals(Type.CAR)) {
            PassengerCarBuilder builder = new PassengerCarBuilder();
            car = builder
                    .withUuidOfCar(UUID.randomUUID().toString())
                    .withCount(2)
                    .withPrice(999)
                    .withType(type)
                    .withPassengerCount(3)
                    .build();
        } else if (type.equals(Type.TRUCK)) {
            TruckBuilder builder = new TruckBuilder();
            car = builder
                    .withUuidOfCar(UUID.randomUUID().toString())
                    .withCount(2)
                    .withPrice(999)
                    .withType(type)
                    .withLoadCapacity(10000)
                    .build();
        } else {
            car = null;
        }
        return Optional.ofNullable(car);
    }
}
