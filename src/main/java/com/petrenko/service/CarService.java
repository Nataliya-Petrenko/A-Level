package com.petrenko.service;

//Створити клас com.{lastname}.service.CarService
//Додати в клас CarService метод create() для створення машини з
//випадковими даними. Метод має повертати створену машину
//Додати в клас CarService метод print() який приймає машини і показує
//інформацію на консоль у вигляді {Manufacturer: ... Engine: ... Color: ...
//Count ...}

import com.petrenko.model.Car;

import java.util.Random;

public class CarService {
    Random random = new Random();

    public Car create() {
        Car car = new Car(randomText(5), randomText(5), randomColor());
        return car;
    }

    public void print(Car car) {
        System.out.printf("Manufacturer: %s. Engine: %s. Color: %s. Count: %d. Price: %d.%n",
                car.getManufacturer(), car.getEngine(), car.getColor(), car.getCount(), car.getPrice());
    }

    private String randomText(int lengthRandomText) {
        String abc = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < lengthRandomText; i++) {
            sb.append(abc.charAt(random.nextInt(abc.length() - 1)));
        }
        return sb.toString();
    }

    private String randomColor() {
        String[] colors = {"red", "orange", "yellow", "green", "blue", "violet", "black", "white"};
        return colors[random.nextInt(colors.length)];
    }
}
