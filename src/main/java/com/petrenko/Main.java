package com.petrenko;

import com.petrenko.action.Actions;
import com.petrenko.container.CarComparator;
import com.petrenko.container.CarList;
import com.petrenko.container.CarTree;
import com.petrenko.container.GenericContainer;
import com.petrenko.model.Car;
import com.petrenko.service.CarService;


public class Main {
    public static void main(String[] args) {

        CarService carService = CarService.getInstance();

        Car[] cars = carService.create(10);
        for (int i = 0; i < cars.length; i++) {
            cars[i].restore();
        }
        System.out.println("All cars from CarRepository:");
        carService.printAll();
        System.out.println();

        CarTree carTree = new CarTree();

        for (int i = 0; i < cars.length; i++) {
            carTree.add(cars[i]);
        }

        System.out.println();
        System.out.println(carService.mapManufacturerCount(cars));
        System.out.println();
        System.out.println(carService.mapPowerCar(cars));

    }


}
