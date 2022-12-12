package com.petrenko;

import com.petrenko.model.*;
import com.petrenko.repository.CarRepository;
import com.petrenko.service.CarService;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        final CarService carService = new CarService(new CarRepository());

        Car car = carService.create(Type.randomType());
//
//        carService.printManufacturerAndCount(car);
//        carService.printColor(car);
////        carService.checkCount(car);
//        carService.printEngineInfo(car);
//        carService.printInfo(car);


        Car car2 = carService.create();
        System.out.println(carService.carEquals(car, car));


    }
}
