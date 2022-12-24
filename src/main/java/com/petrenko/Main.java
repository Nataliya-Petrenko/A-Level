package com.petrenko;

import com.petrenko.action.Actions;
import com.petrenko.container.CarList;
import com.petrenko.container.GenericContainer;
import com.petrenko.model.Car;
import com.petrenko.service.CarService;


public class Main {
    public static void main(String[] args) {

        CarService carService = CarService.getInstance();

        Car[] cars = carService.create(6);

        System.out.println("Print all cars from CarRepository:");
        carService.printAll();
        System.out.println();

        CarList carList = new CarList();
        carList.addFirst(cars[0]);
        carList.addFirst(cars[1]);
        carList.addLast(cars[2]);
        carList.addLast(cars[3]);
        carList.addFirst(cars[4]);
        carList.addFirst(null);

        System.out.println("First element of CarList:");
        carList.printElementCarList(carList.getFirstElementCarList());
        System.out.println("Last element of CarList:");
        carList.printElementCarList(carList.getLastElementCarList());
        System.out.println();

        System.out.println("Print all CarList:");
        carList.printList();
        System.out.println("Size of CarList: " + carList.size());

        System.out.println();

        System.out.println("Position of car: " + cars[2].getUuidOfCar() + " - " +
                carList.positionOf(cars[2].getUuidOfCar()));

        System.out.println();

        int position = 5;
        System.out.println("Car from position: " + position + " - " +
                carList.getCarByPosition(position));

        System.out.println();

        Car car = cars[5];
        position =3;
        System.out.println("Car: " + car.getUuidOfCar() + " added to position " + position);
        carList.addByPosition(car, position);
        carList.printList();
        System.out.println("Size of CarList: " + carList.size());

        System.out.println();

        position = 0;
        System.out.println("Car for delete: " + position + " - " +
                carList.getCarByPosition(position));
        carList.deleteByPosition(position);
        carList.printList();
        System.out.println("Size of CarList: " + carList.size());

        System.out.println();

        for (Car c : carList.carlistToArray()) {
            System.out.println(c);
        }

    }


}
