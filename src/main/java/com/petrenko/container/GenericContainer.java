package com.petrenko.container;

import com.petrenko.model.Car;
import com.petrenko.service.CarService;

import java.util.Optional;
import java.util.Random;

public class GenericContainer<C extends Car> {
    private CarService carService = CarService.getInstance();
    private final Random random = new Random();
    private C childCar;
    public GenericContainer(C childCar) {
        this.childCar = childCar;
    }

    public void print() {
        carService.print(childCar);
    }

    public void increaseCount() {
        final int newCount = childCar.getCount() + random.nextInt(201) + 100;
        childCar.setCount(newCount);
    }

    public <N extends Number> void increaseCount(N additionalCount) {
        Optional.ofNullable(additionalCount).ifPresent(v -> childCar.setCount(childCar.getCount() + v.intValue()));
    }
}
