package com.petrenko;

import com.petrenko.model.Car;
import com.petrenko.model.Color;
import com.petrenko.model.Type;
import com.petrenko.service.CarService;

import java.util.*;


public class Main {
    public static void main(String[] args) {

        CarService carService = CarService.getInstance();

        Car[] cars = carService.create(10);

        System.out.println("All cars from CarRepository:");
        carService.printAll();
        System.out.println();

        int price = 500_000_000;
        carService.findManufacturerByPrice(cars, price);
        System.out.println();

        System.out.println("Number of all cars:");
        System.out.println(carService.countSum(cars));
        System.out.println();

        System.out.println("Manufacturers and types of cars:");
        System.out.println(carService.mapToMap(cars));
        System.out.println();

        System.out.println("Car price statistics:");
        System.out.println(carService.statistic(cars));

        price = 10;
        System.out.println("Prices for all cars are higher than " + price + ":");
        System.out.println(carService.priceCheck(cars, price));
        price = 1000000000;
        System.out.println("Prices for all cars are higher than " + price + ":");
        System.out.println(carService.priceCheck(cars, price));

        System.out.println();

        Map<String, Object> map = new HashMap<>();
        map.put("type", Type.CAR);
        map.put("manufacturer", "manufacturer");
        map.put("color", Color.RED);
        map.put("count", 123);
        map.put("price", 1_000_000);

        System.out.println("New car by Map:");
        Car newCarByMap = carService.mapToObject(map);
        carService.print(newCarByMap);

        System.out.println();

        List<Car> cars1 = Arrays.stream(carService.create(5)).toList();
        List<Car> cars2 = Arrays.stream(carService.create(5)).toList();
        List<Car> cars3 = Arrays.stream(carService.create(5)).toList();

        List<List<Car>> listListCars = new LinkedList<>();
        listListCars.add(cars1);
        listListCars.add(cars2);
        listListCars.add(cars3);

        price = 500_000_000;
        System.out.println(carService.innerList(listListCars, price));

    }


}
