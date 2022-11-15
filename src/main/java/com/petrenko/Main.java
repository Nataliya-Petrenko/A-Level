package com.petrenko;

//Homework 7
//Створити Enum із кількома кольорами та замінити тип поля color у класі Car.
//Створити клас com.{lastname}.model.Engine з полями int power (0-1000) і String type.
//Змінити клас Car на використання Engine.
//Викликати метод check() у класі Main для кожної створеної машин

import com.petrenko.model.Car;
import com.petrenko.model.Color;
import com.petrenko.repository.CarRepository;
import com.petrenko.service.CarService;

public class Main {
    public static void main(String[] args) {
        final CarService carService = new CarService(new CarRepository());
//----------HW6 У класі Main створити 3 машини і показати інформацію на консоль за допомогою класу CarService------
//  -----------Check printAll() method-----------
//        final Car car1 = carService.create();
//        final Car car2 = carService.create();
//        final Car car3 = carService.create();
//        carService.print(car1);
//        carService.print(car2);
//        carService.print(car3);
//        System.out.println();
//        carService.printAll();

//  -----------Check printByUuid() method-----------
//        final Car car1 = carService.create();
//        carService.printByUuid(car1.getUuidOfCar());

//  -----------Check override method create() for several cars-----------
//        final int numberOfCars = 14;
//        carService.create(numberOfCars);
//        carService.printAll();

//  -----------Check method updateColor() and updateColorRandom()-----------
//        System.out.println("Created new car:");
//        final Car car1 = carService.create();
//        carService.printByUuid(car1.getUuidOfCar());
//        System.out.println("The car with changed color:");
//        carService.updateColor(car1.getUuidOfCar(), Color.RED);
//        carService.printByUuid(car1.getUuidOfCar());
//        System.out.println("The car with color changed to random color:");
//        carService.updateColorRandom(car1.getUuidOfCar());
//        carService.printByUuid(car1.getUuidOfCar());

//  -----------Check method updatePrice()-----------
//        System.out.println("Created new car:");
//        final Car car1 = carService.create();
//        carService.printByUuid(car1.getUuidOfCar());
//        System.out.println("The car with changed price:");
//        carService.updatePrice(car1.getUuidOfCar(), 100_000);
//        carService.printByUuid(car1.getUuidOfCar());

//  -----------Check method deleteByUuid()-----------
//        System.out.println("Created new cars:");
//        final Car[] cars = carService.create(4);
//        carService.printAll();
//        carService.deleteByUuid(cars[2].getUuidOfCar());
//        System.out.println("Cars without deleted car:");
//        carService.printAll();

//  -----------Check method insert(Car car, int indexInsertCar)-----------
//        System.out.println("Created new cars:");
//        carService.create(5);
//        carService.printAll();
//        carService.insert(carService.create(), 3);
//        System.out.println("Cars with inserted car:");
//        carService.printAll();

//  -----------Check method insert(int indexInsertCar)-----------
//        System.out.println("Created new cars:");
//        carService.create(5);
//        carService.printAll();
//        carService.insert(15);
//        System.out.println("Cars with inserted car:");
//        carService.printAll();

//  -----------Check method check(Car car)-----------
        final Car[] cars = carService.create(5);
        carService.printAll();
        for (Car car : cars) {
            carService.check(car);
        }
    }
}
