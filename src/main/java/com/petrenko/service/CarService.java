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
    public Car create(){
        Car car = new Car();
        car.setManufacturer(randomText(5));
        car.setEngine(randomText(5));
        car.setColor(randomColor());
        car.setCount(randomPositiveNumber());
        car.setPrice(randomPositiveNumber());
        return car;
    }
    public void print(Car car) {
        System.out.printf("Manufacturer: %s. Engine: %s. Color: %s. Count: %d.%n",
                car.getManufacturer(), car.getEngine(), car.getColor(), car.getCount());
    }
    private String randomText(int lengthRandomText) {
        String abc = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < lengthRandomText; i++) {
            sb.append(abc.charAt(random.nextInt(abc.length() - 1)));
        }
        return sb.toString();
    }
    private String randomColor(){
        String[] colors = {"red", "orange", "yellow", "green", "blue", "violet"};
        Random random = new Random();
        return colors[random.nextInt(colors.length)];
    }
    private int randomPositiveNumber() {
        Random random = new Random();
        return Math.abs(random.nextInt());
    }
}
