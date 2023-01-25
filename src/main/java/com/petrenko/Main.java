package com.petrenko;

import com.petrenko.model.Car;
import com.petrenko.service.CarService;

public class Main {
    public static void main(String[] args) {

        CarService carService = CarService.getInstance();

        System.out.println();

        Car car1 = carService.carFromFile("car.xml");
        carService.print(car1);
        Car car2 = carService.carFromFile("car.json");
        carService.print(car2);
        Car truck1 = carService.carFromFile("truck.xml");
        carService.print(truck1);
        Car truck2 = carService.carFromFile("truck.json");
        carService.print(truck2);

        carService.create(10);
        carService.printAll();
        carService.printManufacturerAndCount(null);
        carService.printEngineInfo(carService.create());

    }


}
