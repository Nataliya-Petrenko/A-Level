package com.petrenko.model;

//Створити клас com.{lastname}.model.Car
//Добавить в класс Car поля, сеттеры, геттеры
//○ String manufacturer
//○ String engine
//○ String color
//○ int count
//○ int price
//Добавить конструкторы в модель
//○ Конструктор по умолчанию
//○ Конструктор принимающий 3 параметра (manufacturer, engine, color), устанавливающий
//значение count = 1, price = случайное число

import com.petrenko.*;

import java.util.Random;

public class Car {
    private String manufacturer;
    private String engine;
    private String color;
    private int count;
    private int price;

    public Car() {
    };

    Random random = new Random();
    public Car(String manufacturer, String engine, String color) {
        this.manufacturer = manufacturer;
        this.engine = engine;
        this.color = color;
        this.count = 1;
        this.price = Math.abs(random.nextInt());
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
